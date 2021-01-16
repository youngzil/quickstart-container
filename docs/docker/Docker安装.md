- [MacOS安装Docker](#MacOS安装Docker)
- [CentOS安装Docker](#CentOS安装Docker)
    - [CentOS 8.0 安装Docker](#CentOS-8.0-安装Docker)
- [Docker启动、重启、关闭命令](#Docker启动、重启、关闭命令)





---------------------------------------------------------------------------------------------------------------------

## MacOS安装Docker

1、brew cask install docker
2、第2种：Docker Desktop for Mac安装



docker --version


参考  
https://docs.docker.com/docker-for-mac/install/  
https://docs.docker.com/docker-for-mac/docker-toolbox/  


---------------------------------------------------------------------------------------------------------------------

## CentOS安装Docker


查看linux系统版本命令
cat /proc/version



### CentOS 8.0 安装Docker


Docker安装

1. 首先更新一下：yum -y update
2. centos8默认使用podman代替docker，所以需要containerd.io，那我们就安装一下就好了  
yum install -y https://download.docker.com/linux/centos/7/x86_64/stable/Packages/containerd.io-1.4.3-3.1.el7.x86_64.rpm

3. 安装一些其他依赖  
安装docker报错Error: Unable to find a match: docker

执行下面的指令安装一些其他依赖
```
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
```

4. 安装docker  
yum install docker-ce docker-ce-cli
```
## Install Docker:
Now you can install Docker on CentOS 8 without any issue.
# yum install docker-ce docker-ce-cli
```

5. 启动Docker,并设置为开机自启
```
[root@localhost ~]# systemctl start docker
[root@localhost ~]# systemctl enable docker
Created symlink /etc/systemd/system/multi-user.target.wants/docker.service → /usr/lib/systemd/system/docker.service.
```

6. docker -v

安装时候遇到的问题
执行docker安装yum install docker-ce,得到如下报错
```
Error: 
 Problem: package docker-ce-3:20.10.1-3.el7.x86_64 requires containerd.io >= 1.4.1, but none of the providers can be installed
  - cannot install the best candidate for the job
  - package containerd.io-1.4.3-3.1.el7.x86_64 is filtered out by modular filtering
(try to add '--skip-broken' to skip uninstallable packages or '--nobest' to use not only best candidate packages)
```

You can directly install the containerd.io package without downloading it.
```
# yum install -y https://download.docker.com/linux/centos/7/x86_64/stable/Packages/containerd.io-1.4.3-3.1.el7.x86_64.rpm
```


参考  
[centos8 安装docker报错Error: Unable to find a match: docker](https://blog.csdn.net/weixin_41725792/article/details/109679971)






### 1、使用 yum 安装（CentOS 7下）

```
#查看你当前的内核版本
uname -r

#安装 Docker
yum -y install docker

#启动 Docker 后台服务
service docker start

#测试运行 hello-world,由于本地没有hello-world这个镜像，所以会下载一个hello-world的镜像，并在容器内运行。
docker run hello-world
```



### 2、使用脚本安装 Docker

```
1、使用 sudo 或 root 权限登录 Centos。
2、确保 yum 包更新到最新。


#确保 yum 包更新到最新
sudo yum update

#执行 Docker 安装脚本,执行这个脚本会添加 docker.repo 源并安装 Docker。
curl -fsSL https://get.docker.com/ | sh

缺少依赖：Problem: package docker-ce-3:19.03.12-3.el7.x86_64 requires containerd.io >= 1.2.2-3, but none of the providers can be installed
yum install -y https://download.docker.com/linux/centos/7/x86_64/stable/Packages/containerd.io-1.2.6-3.3.el7.x86_64.rpm


#启动 Docker 进程
sudo service docker start

#验证 docker 是否安装成功并在容器中执行一个测试的镜像
sudo docker run hello-world

查看Docker版本
docker version

设置开机自启动
sudo systemctl enable docker

```



### 3、使用官方安装脚本自动安装

安装命令如下：  
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun

也可以使用国内 daocloud 一键安装命令：  
curl -sSL https://get.daocloud.io/docker | sh


卸载旧版本  
较旧的 Docker 版本称为 docker 或 docker-engine 。如果已安装这些程序，请卸载它们以及相关的依赖项。  
sudo yum remove docker \
          docker-client \
          docker-client-latest \
          docker-common \
          docker-latest \
          docker-latest-logrotate \
          docker-logrotate \
          docker-engine


参考
https://www.runoob.com/docker/centos-docker-install.html
https://www.jianshu.com/p/3a4cd73e3272



---------------------------------------------------------------------------------------------------------------------


docker官网地址  https://www.docker.com/

### Docker启动、重启、关闭命令

启动        systemctl start docker

守护进程重启   sudo systemctl daemon-reload

重启docker服务   systemctl restart  docker

重启docker服务  sudo service docker restart

关闭docker service docker stop

关闭docker systemctl stop docker



 docker version



---------------------------------------------------------------------------------------------------------------------



