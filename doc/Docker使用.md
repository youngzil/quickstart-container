1、Docker 简介，什么是 Docker？
2、Docker 和虚拟机的区别与特点
3、核心概念：镜像、容器与仓库
4、Docker 安装、运行与加速
5、镜像的获取与使用： commit 命令 和 Dockerfile 定制镜像




环境一次创建，多端一致性运行

Docker 利用 Linux 核心中的资源分脱机制，例如 cgroups，以及 Linux 核心名字空间（name space），来创建独立的软件容器（containers），属于操作系统层面的虚拟化技术。由于隔离的进程独立于宿主和其它的隔离的进程，因此也称其为容器。Docker 在容器的基础上进行了进一步的封装，从文件系统、网络互联到进程隔离等等，极大的简化了容器的创建和维护，使得其比虚拟机技术更为轻便、快捷。Docker 可以在单一 Linux 实体下运作，避免因为创建一个虚拟机而造成的额外负担


对于虚拟机技术来说，传统的虚拟机需要模拟整台机器包括硬件，每台虚拟机都需要有自己的操作系统，虚拟机一旦被开启，预分配给他的资源将全部被占用。每一个虚拟机包括应用 + 必要的二进制和库 + 以及一个完整的用户操作系统。

容器技术和我们的宿主机共享硬件资源及操作系统，可以实现资源的动态分配。容器包含应用和其所有的依赖包，但是与其他容器共享内核。容器在宿主机操作系统中，在用户空间以分离的进程运行。容器内没有自己的内核，也没有进行硬件虚拟。


docker-compose：多个复杂服务的部署
docker-machine：用于在各种平台上快速创建具有 docker 服务的虚拟机的技术
docker-swarm：swarm 是基于 docker 平台实现的集群技术，他可以通过几条简单的指令快速的创建一个 docker 集群，接着在集群的共享网络上部署应用，最终实现分布式的服务。




docker教程
https://yeasy.gitbooks.io/docker_practice/image/build.html




http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html














Docker命令行：


docker本身
docker --version
docker info



docker镜像
docker image ls
docker search image名字





docker容器
docker ps -a
docker ps -n 6   相当于docker ps -a 并且只显示前6行
docker run --name webserver -d -p 4000:80 nginx
docker run --name docker-demo -d -p 8080:80 docker-demo:0.1
docker exec -it webserver bash
exit
docker diff container名字
docker history nginx:v2
docker container ls
dpcker container stop webserver
docker stop d2af2dc2157b
docker start d2af2dc2157b
docker build -t mynginx:0.1 .
docker container ps
docker container rm docker-demo
















