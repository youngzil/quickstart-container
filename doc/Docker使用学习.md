1、Docker 简介，什么是 Docker？为什么使用Docker（优缺点）？
2、Docker 和虚拟机的区别与特点
Docker的核心能力：docker-compose、docker-machine、docker-swarm
3、核心概念：镜像、容器与仓库
4、Docker 安装、运行与加速
5、镜像的获取与使用： commit 命令 和 Dockerfile 定制镜像
Docker基本架构： C/S架构，包括客户端和服务端



什么是 Docker
Docker 是一个开源项目，诞生于 2013 年初，最初是 dotCloud 公司内部的一个业余项目。它基于 Google 公司推出的 Go 语言实现。 项目后来加入了 Linux 基金会，遵从了 Apache 2.0 协议，项目代码在 GitHub 上进行维护。
Docker 自开源后受到广泛的关注和讨论，以至于 dotCloud 公司后来都改名为 Docker Inc。Redhat 已经在其 RHEL6.5 中集中支持 Docker；Google 也在其 PaaS 产品中广泛应用。
Docker 项目的目标是实现轻量级的操作系统虚拟化解决方案。 Docker 的基础是 Linux 容器（LXC）等技术。
在 LXC 的基础上 Docker 进行了进一步的封装，让用户不需要去关心容器的管理，使得操作更为简便。用户操作 Docker 的容器就像操作一个快速轻量级的虚拟机一样简单。


为什么要使用 Docker？
1、首先，Docker 容器的启动可以在秒级实现，这相比传统的虚拟机方式要快得多。 
2、其次，Docker 对系统资源的利用率很高，一台主机上可以同时运行数千个 Docker 容器。
3、容器除了运行其中应用外，基本不消耗额外的系统资源，使得应用的性能很高，同时系统的开销尽量小。传统虚拟机方式运行 10 个不同的应用就要起 10 个虚拟机，而Docker 只需要启动 10 个隔离的应用即可。
4、更快速的交付和部署：一次创建或配置，可以在任意地方正常运行。
5、更高效的虚拟化：Docker 容器的运行不需要额外的 hypervisor 支持，它是内核级的虚拟化，因此可以实现更高的性能和效率。
6、更轻松的迁移和扩展：Docker 容器几乎可以在任意的平台上运行，包括物理机、虚拟机、公有云、私有云、个人电脑、服务器等。 这种兼容性可以让用户把一个应用程序从一个平台直接迁移到另外一个。
7、更简单的管理：使用 Docker，只需要小小的修改，就可以替代以往大量的更新工作。所有的修改都以增量的方式被分发和更新，从而实现自动化并且高效的管理。


Docker和虚拟机的区别
环境一次创建，多端一致性运行
Docker 利用 Linux 核心中的资源分脱机制，例如 cgroups，以及 Linux 核心名字空间（name space），来创建独立的软件容器（containers），属于操作系统层面的虚拟化技术。由于隔离的进程独立于宿主和其它的隔离的进程，因此也称其为容器。Docker 在容器的基础上进行了进一步的封装，从文件系统、网络互联到进程隔离等等，极大的简化了容器的创建和维护，使得其比虚拟机技术更为轻便、快捷。Docker 可以在单一 Linux 实体下运作，避免因为创建一个虚拟机而造成的额外负担
对于虚拟机技术来说，传统的虚拟机需要模拟整台机器包括硬件，每台虚拟机都需要有自己的操作系统，虚拟机一旦被开启，预分配给他的资源将全部被占用。每一个虚拟机包括应用 + 必要的二进制和库 + 以及一个完整的用户操作系统。
容器技术和我们的宿主机共享硬件资源及操作系统，可以实现资源的动态分配。容器包含应用和其所有的依赖包，但是与其他容器共享内核。容器在宿主机操作系统中，在用户空间以分离的进程运行。容器内没有自己的内核，也没有进行硬件虚拟。


Docker的核心能力：docker-compose、docker-machine、docker-swarm
docker-compose：多个复杂服务的部署
docker-machine：用于在各种平台上快速创建具有 docker 服务的虚拟机的技术
docker-swarm：swarm 是基于 docker 平台实现的集群技术，他可以通过几条简单的指令快速的创建一个 docker 集群，接着在集群的共享网络上部署应用，最终实现分布式的服务。



Docker 包括三个基本概念
1、镜像（Image）：Docker 镜像就是一个只读的模板。镜像可以用来创建 Docker 容器。
2、容器（Container）：Docker 利用容器来运行应用。容器是从镜像创建的运行实例。它可以被启动、开始、停止、删除。每个容器都是相互隔离的、保证安全的平台。
可以把容器看做是一个简易版的 Linux 环境（包括root用户权限、进程空间、用户空间和网络空间等）和运行在其中的应用程序。
注：镜像是只读的，容器在启动的时候创建一层可写层作为最上层。
3、仓库（Repository）：仓库是集中存放镜像文件的场所。
仓库注册服务器上往往存放着多个仓库，每个仓库中又包含了多个镜像，每个镜像有不同的标签（tag）。
仓库分为公开仓库（Public）和私有仓库（Private）两种形式。
最大的公开仓库是 Docker Hub，存放了数量庞大的镜像供用户下载。 国内的公开仓库包括 Docker Pool 等，可以提供大陆用户更稳定快速的访问。
当然，用户也可以在本地网络内创建一个私有仓库。
当用户创建了自己的镜像之后就可以使用 push 命令将它上传到公有或者私有仓库，这样下次在另外一台机器上使用这个镜像时候，只需要从仓库上 pull 下来就可以了。
注：Docker 仓库的概念跟 Git 类似，注册服务器可以理解为 GitHub 这样的托管服务。

仓库注册服务器（Registry）、仓库（Repository）、便签tag、镜像ID（IMAGE ID）

TAG 信息用来标记来自同一个仓库的不同镜像。
其中镜像的 ID 唯一标识了镜像，注意到 ubuntu:14.04 和 ubuntu:trusty 具有相同的镜像 ID，说明它们实际上是同一镜像。



Dockerfile 由一行行命令语句组成，并且支持以 # 开头的注释行。
一般的，Dockerfile 分为四部分：基础镜像信息、维护者信息、镜像操作指令和容器启动时执行指令。



Docker 采用了 C/S架构，包括客户端和服务端。 Docker daemon 作为服务端接受来自客户的请求，并处理这些请求（创建、运行、分发容器）。 客户端和服务端既可以运行在一个机器上，也可通过 socket 或者 RESTful API 来进行通信。
Docker daemon 一般在宿主主机后台运行，等待接收来自客户端的消息。
Docker 客户端则为用户提供一系列可执行命令，用户用这些命令实现跟 Docker daemon 交互。





1、网址：
http://www.ducker.com/
https://hub.docker.com/
https://github.com/moby/moby
https://github.com/docker


docker中文社区
http://www.docker.org.cn/

kubernetes社区
https://www.kubernetes.org.cn/


https://www.daocloud.io
https://github.com/DaoCloud



docker教程学习参考
http://wiki.jikexueyuan.com/project/docker-technology-and-combat/appendix_useful.html
https://yeasy.gitbooks.io/docker_practice/image/build.html
http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html



有用资源
Docker 主站点: https://www.docker.io
Docker 注册中心API: http://docs.docker.com/reference/api/registry_api/
Docker Hub API: http://docs.docker.com/reference/api/docker-io_api/
Docker 远端应用API: http://docs.docker.com/reference/api/docker_remote_api/
Dockerfile 参考：https://docs.docker.com/reference/builder/
Dockerfile 最佳实践：https://docs.docker.com/articles/dockerfile_best-practices/













