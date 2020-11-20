# MESH 测试开发环境搭建手册



## 前置工作
**以某地项目为例**
<p>主机列表</p>

ip|主机名|用户|密码
---|:--:|---:|---:|
127.0.0.221|csvz-fwwg01|root
127.0.0.222|csvz-fwwg02|root
127.0.0.226|csvz-fwwg06|root
127.0.0.227|csvz-fwwg07|root

**项目的镜像仓库编码为registry.yw.zj.test.com测试环境继续使用该域名，方便docker相关操作**
修改主机hosts文件（所有主机）
<p> vi /etc/hosts</p>

```
127.0.0.221 csvz-fwwg01 registry.yw.zj.test.com
127.0.0.222 csvz-fwwg02
127.0.0.226 csvz-fwwg06
127.0.0.227 csvz-fwwg07
```
<p>检查防火墙（测试环境可直接选择关闭）（所有主机）

```
service iptables status
service firewalld status

service iptables stop
service firewalld stop
```


## DOCKER篇
**安装docker（集群并带私有仓库）（所有主机）**

docker版本尽量使用 18.06版本(某项目生产版本)

```
tar -zxvf docker-ce.tar.gz
cd docker

#安装本地rpm包并无视依赖
yum  localinstall *.rpm --nodeps --force 

#启动docker
systemctl start docker && systemctl enable docker 

#安装完成查看版本
docker version

#如果上述方法不行，可以自己组织配置文件安装，安装方式
#Docker安装 方式二
#1、解压
tar -xvzf docker-18.06.3-ce.tgz

#2、将解压出来的docker文件内容移动到 /usr/bin/ 目录下
cp docker/* /usr/bin/

#3、将docker注册为service
vim /etc/systemd/system/docker.service

#文件 内容如下

[Unit]
Description=Docker Application Container Engine
Documentation=https://docs.docker.com
After=network-online.target firewalld.service
Wants=network-online.target
 
[Service]
Type=notify
# the default is not to use systemd for cgroups because the delegate issues still
# exists and systemd currently does not support the cgroup feature set required
# for containers run by docker
ExecStart=/usr/bin/dockerd
ExecReload=/bin/kill -s HUP $MAINPID
# Having non-zero Limit*s causes performance problems due to accounting overhead
# in the kernel. We recommend using cgroups to do container-local accounting.
LimitNOFILE=infinity
LimitNPROC=infinity
LimitCORE=infinity
# Uncomment TasksMax if your systemd version supports it.
# Only systemd 226 and above support this version.
#TasksMax=infinity
TimeoutStartSec=0
# set delegate yes so that systemd does not reset the cgroups of docker containers
Delegate=yes
# kill only the docker process, not all processes in the cgroup
KillMode=process
# restart the docker process if it exits prematurely
Restart=on-failure
StartLimitBurst=3
StartLimitInterval=60s
 
[Install]
WantedBy=multi-user.target


#4、启动
#添加文件权限并启动docker

chmod +x /etc/systemd/system/docker.service

systemctl daemon-reload
systemctl start docker			#启动Docker
systemctl enable docker.service			#设置开机自启

#5、验证
systemctl status docker			#查看Docker状态
docker -v			#查看Docker版本

``` 



**设置docker私有仓库（仅仓库主机，127.0.0.221，不带私钥）**

```
#下载仓库注册用容器
docker load -i registry.tar

#或者直接从docker.io下载
docker pull registry:latest

#生成密钥
#在 mesh/build/registry 文件夹下，有makecrt.sh
#输入相关信息后生成新的domain.crt,将该文件复制到 /etc/docker/registry.yw.zj.test.com
cp certs/domain.crt  /etc/docker/certs.d/registry.yw.zj.test.com/ca.crt
#并将该文件复制到slave主机上
scp /etc/docker/certs.d/registry.yw.zj.test.com/ca.crt  root@127.0.0.222:/etc/docker/certs.d/registry.yw.zj.test.com/

#在kubernetes安装好之后，再去启动该镜像，最后再重启docker，才能完成docker仓库的安装
kubectl create namespace middleware
kubectl apply -f registry.yaml

systemctl restart docker

#使用docker push  或者docker  pull 测试镜像仓库
```
**是从主机docker服务识别私有仓库（所有主机）**

```
#使docker能够识别https命令
cd /etc/docker
vi daemon.json
# 识别https 默认端口443
{ 
    "insecure-registries":["register.yw.zj.test.com"] 
}
```

## KUBERNETES篇
### 安装kubernetes Master节点

```
#安装kubelet kubeadm  kubectl服务
tar -zxvf kube114-rpm.tar.gz 
cd kube114-rpm
yum  localinstall *.rpm  进行安装，yum命令可以自动解决依赖

#启动kubelet服务
systemctl enable kubelet
systemctl start kubelet

#关闭swap
swapoff -a

#swap一行注释
vi /etc/fstab

#执行下面这段虚拟网段
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system

#导入镜像
docker load -i k8s-114-images.tar.gz
docker load -i flannel-dashboard.tar.gz 

#kuneadm安装kubernetes
kubeadm init --kubernetes-version=v1.14.1 --pod-network-cidr=10.244.0.0/16 --apiserver-advertise-address=127.0.0.22 --token-ttl 0


#重制kubernetes环境
kubeadm reset

#记下kubeadm join 方便从节点加入主节点
kubeadm join 127.0.0.221:6443 --token c8syx4.8pa80idsa9mymc5y \
    --discovery-token-ca-cert-hash sha256:8e8e564ad790b2df8fe240837bd468275db480e21c1f5c3af6e44e0556531c97

#方便使用kubectl命令
mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

#检查kube服务是否正常
kubectl get nodes

#配置KUBECONFIG变量
echo "export KUBECONFIG=/etc/kubernetes/admin.conf" >> /etc/profile
source /etc/profile
echo $KUBECONFIG    #应该返回/etc/kubernetes/admin.conf

#部署flannel网络
kubectl create -f kube-flannel.yml

```


### 安装kubernetes Slave节点

```
tar -zxvf kube114-rpm.tar.gz 
cd kube114-rpm
yum  localinstall *.rpm  进行安装，yum命令可以自动解决依赖

#如果是之前安装过了，重新安装的时候，从节点也需要使用kubeadm reset
kubeadm reset

#启动kubelet服务
systemctl enable kubelet
systemctl restart kubelet

#执行 kubeadm join
kubeadm join 127.0.0.221:6443 --token c8syx4.8pa80idsa9mymc5y \
    --discovery-token-ca-cert-hash sha256:8e8e564ad790b2df8fe240837bd468275db480e21c1f5c3af6e44e0556531c97
    
    
当你的token忘了或者过期，解决办法如下：

1.先获取token
#如果过期可先执行此命令
kubeadm token create    #重新生成token
#列出token
kubeadm token list  | awk -F" " '{print $1}' |tail -n 1

2.获取CA公钥的哈希值
openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed  's/^ .* //'

3.从节点加入集群
kubeadm join 192.168.40.8:6443 --token token填这里   --discovery-token-ca-cert-hash sha256:哈希值填这里

```



### 安装kubernetes dashboard

```
#
docker load -i dashboard.tar

kubectl apply -f kubernetes-dashboard.yaml
kubectl apply -f admin-token.yaml

kubectl get secret -n kube-system

#查找token
#admin-token-2j9bd 不固定 根据上面的命定找到admin-token- 开头的
kubectl describe secret dashboard-admin-token-2j9bd -n kube-system

#制作查看token的脚本
vi token.sh
kubectl describe secret $(kubectl get secret -nkube-system |grep admin|awk '{print $1}') -nkube-system
#给予可执行权限
chmod +x token.sh

#端口为30000  https要注意
https://127.0.0.221:30000

#浏览器要求
mac下 给浏览器均可
win下 需要使用firefox
```


## ISTIO篇

### 导入全部istio镜像

```
#istio服务相关镜像
docker load -i citadel.tar
docker load -i galley.tar
docker load -i grafana.tar
docker load -i kiali.tar
docker load -i kubectl.tar
docker load -i mixer.tar
docker load -i pilot.tar
docker load -i prometheus.tar
docker load -i proxy_init.tar
docker load -i proxyv2.tar
docker load -i sidecar_injector.tar

#测试csf-demo相关镜像
docker load -i gateway.tar
docker load -i web.tar
docker load -i mysql.tar
docker load -i mysql-init.tar
docker load -i ord.tar
docker load -i ams.tar
docker load -i res.tar
docker load -i aif-init.tar
docker load -i mesh-ui.tar
docker load -i all-in-one.tar

#制作镜像的基础镜像
docker load -i centos7-jdk8.tar
docker load -i jdk8tomcat7.tar
docker load -i alpine.tar
```

### 创建命名空间

```
kubectl create namespace istio-system

#使可注入
kubectl label namespace default istio-injection=enabled
```

### 安装helm (可以不安装)

```
tar -xvzf helm-v2.13.1-linux-amd64.tar.gz
cd linux-amd64
mv helm  /usr/local/bin/
helm version 
#查看（tiller正不正常无所谓）
```

### 启动ISTIO服务

```
#找到 cd ～/mesh/deploy/install/kubernetes/helm/
./install.sh apply

#关闭
./install.sh delete
#启动耗时5-10分钟 在kubernetes-dashboard上检查服务状态
```

### 查看测试demo

```
#启动测试用数据库
cd mesh/build/middleware
kubectl apply -f mysql.yaml
cd mesh/deploy/samples/quickstart/platform/kube/helm/mysql-init
./install.sh apply

cd ~/mesh/deploy/samples/quickstart/platform/kube/helm/msp
#开启demo服务
sh start.sh
#关闭demo服务
sh stop.sh
#启动耗时5-10分钟 在kubernetes-dashboard上检查服务状态
```

### 不安装helm时，需要提前生成好yaml文件


```
#生成istio启动的yaml文件
cd mesh/deploy/install/kubernetes/helm
#生成 aif-mesh-istio-install.yaml
./releash.sh

kubectl apply -f aif-mesh-istio-install.yaml

#生成demo启动的yaml文件
cd mesh/deploy/samples/quickstart/kube/helm/msp/
#生成csf-demo-install.yaml
./release-demo.sh

kubectl apply -f csf-demo-install.yaml

#验证用手机号
13678129357

```
### 安装weave-scope kubernetes集群监控
获取该文件
https://cloud.weave.works/k8s/scope.yaml

修改scope.yaml 配置  默认不提供外网网络


```
#新增 type: NodePort

    spec:
      type: NodePort
      ports:
        - name: app
          port: 80
          protocol: TCP
          targetPort: 4040
          
kubectl apply -f scope.yaml
#查看端口号
kubectl get svc -n weave

```




### Kubernetes常用命令一览
```
#检查node状态
kubectl get nodes
#查看命名空间
kubectl get ns
#查看部署情况 -n 表示所属命名空间
kubectl get ds -n istio-system
#查看crd
kubectl get crd
#查看pods
kubectl get pods
#查看服务
kubectl get svc
#查看网关
kubectl get gateway
```



### 常见问题
```
# kubeadm join 执行后  node节点无法加入集群，kube describe node 发现cni报错


重置node节点kubernetes服务，重置网络。删除网络配置，link
kubeadm reset
systemctl stop kubelet
systemctl stop docker
rm -rf /var/lib/cni/
rm -rf /var/lib/kubelet/*
rm -rf /etc/cni/
ifconfig cni0 down
ifconfig flannel.1 down
ifconfig docker0 down
ip link delete cni0
ip link delete flannel.1
systemctl start docker
```

```
#删除异常停止的docker容器
docker rm `docker ps -a | grep Exited | awk '{print $1}'` 

#删除名称或标签为none的镜像
docker rmi -f `docker images | grep '<none>' | awk '{print $3}'`

```

docker 镜像仓库连接失败

```
docker push registry.yw.zj.test.com/ywdt-istio/proxyv2:1.1.10
#报错
The push refers to repository [registry.yw.zj.test.com/ywdt-istio/proxyv2]
Get https://registry.yw.zj.test.com/v2/: x509: certificate is not valid for any names, but wanted to match registry.yw.zj.test.com

#解决方法在 /etc/docker 下面添加daemon.json

vi  daemon.json

{
  "insecure-registries": ["registry.yw.zj.test.com"]
}

```

出现
```
cni.go:259] Error adding network: failed to set bridge addr: 
"cni0" already has an IP address different from 10.16.2.1/24
```
```
#解决方案
kubeadm reset
systemctl stop kubelet
systemctl stop docker
rm -rf /var/lib/cni/
rm -rf /var/lib/kubelet/*
rm -rf /etc/cni/
ifconfig cni0 down
ifconfig flannel.1 down
ifconfig docker0 down
ip link delete cni0
ip link delete flannel.1
systemctl start docker
```