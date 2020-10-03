1、MacOS安装Docker
2、CentOS安装Docker
3、docker启动命令,docker重启命令,docker关闭命令








---------------------------------------------------------------------------------------------------------------------

MacOS安装Docker

1、brew cask install docker
2、第2种：Docker Desktop for Mac安装



docker --version


参考  
https://docs.docker.com/docker-for-mac/install/  
https://docs.docker.com/docker-for-mac/docker-toolbox/  


---------------------------------------------------------------------------------------------------------------------

CentOS安装Docker


查看linux系统版本命令
cat /proc/version


1、使用 yum 安装（CentOS 7下）
2、使用脚本安装 Docker



1、使用 yum 安装（CentOS 7下）
#查看你当前的内核版本
uname -r

#安装 Docker
yum -y install docker

#启动 Docker 后台服务
service docker start

#测试运行 hello-world,由于本地没有hello-world这个镜像，所以会下载一个hello-world的镜像，并在容器内运行。
docker run hello-world



2、使用脚本安装 Docker
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

Docker中安装redis
#该方式默认下载的最新版本，如需要下载指定版本在redis后面跟:版本号
docker pull redis

查看当前下载的redis镜像
docker images

启动Docker Redis镜像

# -p 主机端口：容器端口      -v 主机目录：容器目录
docker run -it -p hostPort:containerPort -v hostDir:containerDir 






使用官方安装脚本自动安装

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

docker启动命令,docker重启命令,docker关闭命令

启动        systemctl start docker

守护进程重启   sudo systemctl daemon-reload

重启docker服务   systemctl restart  docker

重启docker服务  sudo service docker restart

关闭docker service docker stop

关闭docker systemctl stop docker



 docker version



---------------------------------------------------------------------------------------------------------------------



