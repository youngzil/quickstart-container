- [Podman介绍](#Podman介绍)
- [Podman安装](#Podman安装)
- [Podman命令](#Podman命令)


---------------------------------------------------------------------------------------------------------------------
## Podman介绍

[Podman官网](https://podman.io/)  
[Podman文档](https://podman.readthedocs.io/en/latest/index.html)  
[Podman Github](https://github.com/containers/podman)  

[Podman Compose Github](https://github.com/containers/podman-compose)  
[Buildah Github](https://github.com/containers/buildah)  
[Skopeo Github](https://github.com/containers/skopeo)  



Podman: A tool for managing OCI containers and pods



podman 虽然与 docker 有替代关系，但是在“弃用 docker”事件中，podman 并非是主角。Kubernetes 所进行的“弃用 docker”的主角是 CRI 的其他实现方式，而由 RedHat 推出的 podman 则更加前沿。在现实的应用中，docker 方兴未艾，podman 却已经异军突起。

podman（Pod Manager）是一个由 RedHat 公司推出的容器管理工具，它的定位就是 docker 的替代品，在使用上与 docker 的体验类似。podman 源于 CRI-O 项目，可以直接访问 OCI 的实现（如 runC），流程比 docker 要短。

Podman 可以管理和运行任何符合 OCI（Open Container Initiative）规范的容器和容器镜像。Podman 提供了一个与 Docker 兼容的命令行前端来管理 Docker 镜像。[3]




在容器管理的链路中，Docker Engine 的实现就是 dockerd daemon，它在 linux 中需要以 root 运行，dockerd 调用 containerd，containerd 调用 containerd-shim，然后才能调用 runC。顾名思义 shim 起的作用也就是“垫片”，避免父进程退出影响容器的运训。

podman 直接调用 OCI runtime（runC），通过 common 作为容器进程的管理工具，但不需要 dockerd 这种以 root 身份运行的守护进程。

在 podman 体系中，有个称之为 common 的守护进程，其运行路径通常是/usr/libexec/podman/conmon，它是各个容器进程的父进程，每个容器各有一个，common 的父则通常是 1 号进程。podman 中的 common 其实相当于 docker 体系中的 containerd-shim。


概念
- OCI -> CRI / CNI
- buildah 镜像构建
- skopeo 镜像管理
- podman 容器管理



podman、buildah、skopeo 组成了一个完整的容器工具体系：
- podman 项目对标的是 docker 命令的代替，官方说明是 A tool for managing OCI containers and pods
- buildah 项目实现的是 dockerfile 的脚本化执行，官方说明是 A tool that facilitates building OCI images
- skopeo 项目负责处理镜像相关的工作，比如检查、复制、签名，官方说明是 Work with remote images registries - retrieving information, images, signing content
- podman-compose 官方说明是a script to run docker-compose.yml using podman


- buildah 完成对镜像的操作，类似 docker build，也可以进行 push 等操作；
- skopeo 完成对镜像仓库的操作，包括 cp、inspect、delete 等操作。它们的功能都比较纯粹，它们都是对 podman 功能的补充。

在 Centos 8 中，已经不适用 docker 作为默认的容器化工具，替代品也就是使用 podman、buildah、skopeo。

Centos8 去除了 Docker 作为默认的容器化管理工具，使用 Podman、Buildah、Skopeo 进行了替换。



podman系列主要包含三个命令podman、buildah、skopeo，

- 其中podman本身负责运行、停止、管理容器，
- buildah负责构建容器镜像、
- skopeo负责与remote repo交互，拉取或推送镜像。
  
但我们使用时不必这么麻烦，redhat为了方便用户从docker迁移到podman，在podman上几乎实现了大多数docker的常用命令，podman会替你转调buildah和skopeo，你甚至可以直接 alias docker=podman，然后像使用docker一样使用podman。





参考  
[docker 与 podman 的故事：一个方兴未艾，一个异军突起](https://xie.infoq.cn/article/a7254c5d64fcb3be8d6822415)  





---------------------------------------------------------------------------------------------------------------------
## Podman安装

[Podman Installation Instructions](https://podman.io/getting-started/installation)

brew install podman




[下一代容器技术podman简介及安装](https://zhuanlan.zhihu.com/p/210413963)  
[]()  





---------------------------------------------------------------------------------------------------------------------
## Podman命令


运行一个容器

$ podman run -dt -p 8080:8080/tcp \
-e HTTPD_VAR_RUN=/var/run/httpd \
-e HTTPD_MAIN_CONF_D_PATH=/etc/httpd/conf.d \
-e HTTPD_MAIN_CONF_PATH=/etc/httpd/conf \
-e HTTPD_CONTAINER_SCRIPTS_PATH=/usr/share/container-scripts/httpd/ \
registry.fedoraproject.org/f27/httpd /usr/bin/run-httpd


列出运行的容器

$ podman ps -a


分析一个运行的容器

$ podman inspect -l | grep IPAddress\":
"SecondaryIPAddresses": null,
"IPAddress": "",


查看一个运行中容器的日志

$ sudo podman logs --latest



查看一个运行容器中的进程资源使用情况

$ sudo podman top <container_id>



停止一个运行中的容器

$ sudo podman stop --latest



删除一个容器

$ sudo podman rm --latest


以上这些特性基本上都和 Docker 一样，Podman 除了兼容这些特性外，还支持了一些新的特性。




给容器设置一个检查点

$ sudo podman container checkpoint <container_id>
需要 CRIU 3.11 以上版本支持，CRIU 项目地址：https://criu.org/



根据检查点位置恢复容器

$ sudo podman container restore <container_id>








[开源容器Podman的安装与使用，与Docker功能相似，多一种选择。](https://nowjava.com/article/89)  


