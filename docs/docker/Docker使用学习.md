1、Docker 简介，什么是 Docker？为什么使用Docker（优缺点）？
2、Docker 和虚拟机的区别与特点
Docker的核心能力：docker-compose、docker-machine、docker-swarm
3、核心概念：镜像、容器与仓库
4、Docker 安装、运行与加速：Docker安装.md
Docker命令
Docker-compose配置、部署docker容器
5、镜像的获取与使用： commit 命令 和 Dockerfile 定制镜像
Docker基本架构： C/S架构，包括客户端和服务端
ocker 的基础是 Linux 容器（LXC）等虚拟化技术
Docker 这种虚拟化技术的出现有哪些核心技术：命名空间 (namespaces) 、控制组CGroups（Control Groups）、UnionFS（Union Filesystem）





什么是 Docker
Docker 的基础是 Linux 容器（LXC）等虚拟化技术
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





Docker 这种虚拟化技术的出现有哪些核心技术：命名空间 (namespaces) 、控制组CGroups（Control Groups）、UnionFS（Union Filesystem）

1、通过 Linux 的命名空间为新创建的进程隔离了文件系统、网络并与宿主机器之间的进程相互隔离
2、通过 Linux 的CGroups（Control Groups）能够隔离宿主机器上的物理资源，例如 CPU、内存、磁盘 I/O 和网络带宽。
3、通过UnionFS等存储驱动管理镜像层和容器层的存储（在最新的 Docker 中，overlay2 取代了 aufs 成为了推荐的存储驱动）


Linux 的命名空间和控制组分别解决了不同资源隔离的问题，前者解决了进程、网络以及文件系统的隔离，后者实现了 CPU、内存等资源的隔离


1、命名空间 (namespaces) 是 Linux 为我们提供的用于分离进程树、网络接口、挂载点以及进程间通信等资源的方法。
Linux 的命名空间机制提供了以下七种不同的命名空间，包括 CLONE_NEWCGROUP、CLONE_NEWIPC、CLONE_NEWNET、CLONE_NEWNS、CLONE_NEWPID、CLONE_NEWUSER 和 CLONE_NEWUTS，通过这七个选项我们能在创建新的进程时设置新进程应该在哪些资源上与宿主机器进行隔离。

2、进程：进程是 Linux 以及现在操作系统中非常重要的概念，它表示一个正在执行的程序，也是在现代分时系统中的一个任务单元。
一个是 pid 为 1 的 /sbin/init 进程，另一个是 pid 为 2 的 kthreadd 进程，这两个进程都是被 Linux 中的上帝进程 idle 创建出来的，其中前者负责执行内核的一部分初始化工作和系统配置，也会创建一些类似 getty 的注册进程，而后者负责管理和调度其他的内核进程。
idle-0、init-1、kthreadd-2，1和2的ppid都是0

init-1 -----》 dockerd -----》 docker-containerd  -----》 docker-containerd-shim  -----》/bin/bash
                                                                                 -----》/bin/bash
这就是在使用 clone(2) 创建新进程时传入 CLONE_NEWPID 实现的，也就是使用 Linux 的命名空间实现进程的隔离，Docker 容器内部的任意进程都对宿主机器的进程一无所知。
Docker 通过命名空间成功完成了与宿主机进程和网络的隔离。

3、网络：每一个使用 docker run 启动的容器其实都具有单独的网络命名空间，Docker 为我们提供了四种不同的网络模式，Host、Container、None 和 Bridge 模式。

当 Docker 服务器在主机上启动之后会创建新的虚拟网桥 docker0，随后在该主机上启动的全部服务在默认情况下都与该网桥相连。
在默认情况下，每一个容器在创建时都会创建一对虚拟网卡，两个虚拟网卡组成了数据的通道，其中一个会放在创建的容器中，会加入到名为 docker0 网桥中。
docker0 会为每一个容器分配一个新的 IP 地址并将 docker0 的 IP 地址设置为默认的网关。网桥 docker0 通过 iptables 中的配置与宿主机器上的网卡相连，所有符合条件的请求都会通过 iptables 转发到 docker0 并由网桥分发给对应的机器。

Docker 是如何将容器的内部的端口暴露出来并对数据包进行转发的了；当有 Docker 的容器需要将服务暴露给宿主机器，就会为容器分配一个 IP 地址，同时向 iptables 中追加一条新的规则。
Docker 通过 Linux 的命名空间实现了网络的隔离，又通过 iptables 进行数据包转发，让 Docker 容器能够优雅地为宿主机器或者其他容器提供服务。

创建虚拟网桥 docker0，所有的Docker容器都有自己的IP，然后通过宿主机器的端口和Container内的端口映射，所有符合条件的请求都会通过 iptables 转发到 docker0 并由网桥分发给对应的Container机器。

libnetwork：整个网络部分的功能都是通过 Docker 拆分出来的 libnetwork 实现的，它提供了一个连接不同容器的实现，同时也能够为应用给出一个能够提供一致的编程接口和网络层抽象的容器网络模型。
libnetwork 中最重要的概念，容器网络模型由以下的几个主要组件组成，分别是 Sandbox、Endpoint 和 Network：
在容器网络模型中，每一个容器内部都包含一个 Sandbox，其中存储着当前容器的网络栈配置，包括容器的接口、路由表和 DNS 设置，Linux 使用网络命名空间实现这个 Sandbox，每一个 Sandbox 中都可能会有一个或多个 Endpoint，在 Linux 上就是一个虚拟的网卡 veth，Sandbox 通过 Endpoint 加入到对应的网络中，这里的网络可能就是我们在上面提到的 Linux 网桥或者 VLAN。

在容器内：Endpoint和Sandbox
Endpoint：在 Linux 上就是一个虚拟的网卡 veth
Sandbox：有一个或多个 Endpoint，存储着当前容器的网络栈配置，包括容器的接口、路由表和 DNS 设置
Network：这里的网络可能就是我们在上面提到的 Linux 网桥或者 VLAN。

4、挂载点：
Linux在新的进程（Docker进程，启动新的Docker容器）中创建隔离的挂载点命名空间需要在 clone 函数中传入 CLONE_NEWNS，这样子进程就能得到父进程挂载点的拷贝，如果不传入这个参数子进程对文件系统的读写都会同步回父进程以及整个主机的文件系统。

如果一个容器需要启动，那么它一定需要提供一个根文件系统（rootfs），容器需要使用这个文件系统来创建一个新的进程，所有二进制的执行都必须在这个根文件系统中。

为了保证当前的容器进程没有办法访问宿主机器上其他目录，我们在这里还需要通过 libcontainer 提供的 pivot_root 或者 chroot 函数改变进程能够访问个文件目录的根节点。

这样就可以将容器需要的目录挂载到了容器中，同时也禁止当前的容器进程访问宿主机器上的其他目录，保证了不同文件系统的隔离。

对于 Docker 使用 chroot 来确保当前的进程无法访问宿主机器的目录：
chroot（change root）：在 Linux 系统中，系统默认的目录就都是以 / 也就是根目录开头的，chroot 的使用能够改变当前的系统根目录结构，通过改变当前系统的根目录，我们能够限制用户的权利，在新的根目录下并不能够访问旧系统根目录的结构个文件，也就建立了一个与原系统完全隔离的目录结构。


5、CGroups

我们通过 Linux 的命名空间为新创建的进程隔离了文件系统、网络并与宿主机器之间的进程相互隔离，但是命名空间并不能够为我们提供物理资源上的隔离，比如 CPU 或者内存，如果在同一台机器上运行了多个对彼此以及宿主机器一无所知的『容器』，这些容器却共同占用了宿主机器的物理资源。

Control Groups（简称 CGroups）就是能够隔离宿主机器上的物理资源，例如 CPU、内存、磁盘 I/O 和网络带宽。

在 CGroup 中，所有的任务就是一个系统的一个进程，而 CGroup 就是一组按照某种标准划分的进程，在 CGroup 这种机制中，所有的资源控制都是以 CGroup 作为单位实现的，每一个进程都可以随时加入一个 CGroup 也可以随时退出一个 CGroup。

Linux 使用文件系统来实现 CGroup

如果我们想要创建一个新的 cgroup 只需要在想要分配或者限制资源的子系统下面创建一个新的文件夹，然后这个文件夹下就会自动出现很多的内容，如果你在 Linux 上安装了 Docker，你就会发现所有子系统的目录下都有一个名为 docker 的文件夹

Docker 在使用 CGroup 时其实也只是做了一些创建文件夹改变文件内容的文件操作，不过 CGroup 的使用也确实解决了我们限制子容器资源占用的问题，系统管理员能够为多个容器合理的分配资源并且不会出现多个容器互相抢占资源的问题。


6、UnionFS

UnionFS 其实是一种为 Linux 操作系统设计的用于把多个文件系统『联合』到同一个挂载点的文件系统服务。而 AUFS 即 Advanced UnionFS 其实就是 UnionFS 的升级版，它能够提供更优秀的性能和效率。


Docker 镜像其实本质就是一个压缩包，我们可以使用下面的命令将一个 Docker 镜像中的文件导出：
$ docker export $(docker create busybox) | tar -C rootfs -xvf -
$ ls
bin  dev  etc  home proc root sys  tmp  usr  var
你可以看到这个 busybox 镜像中的目录结构与 Linux 操作系统的根目录中的内容并没有太多的区别，可以说 Docker 镜像就是一个文件。


7、存储驱动
Docker 使用了一系列不同的存储驱动管理镜像内的文件系统并运行容器，这些存储驱动与 Docker 卷（volume）有些不同，存储引擎管理着能够在多个容器之间共享的存储。

Docker 中的每一个镜像都是由一系列只读的层组成的，Dockerfile 中的每一个命令都会在已有的只读层上创建一个新的层，容器中的每一层都只对当前容器进行了非常小的修改。

当镜像被 docker run 命令创建时就会在镜像的最上层添加一个可写的层，也就是容器层，所有对于运行时容器的修改其实都是对这个容器读写层的修改。
容器和镜像的区别就在于，所有的镜像都是只读的，而每一个容器其实等于镜像加上一个可读写的层，也就是同一个镜像可以对应多个容器。

Image是只读（OR）的，Container是+一个可读写（RW）的层


8、AUFS

UnionFS 其实是一种为 Linux 操作系统设计的用于把多个文件系统『联合』到同一个挂载点的文件系统服务。而 AUFS 即 Advanced UnionFS 其实就是 UnionFS 的升级版，它能够提供更优秀的性能和效率。

AUFS 作为联合文件系统，它能够将不同文件夹中的层联合（Union）到了同一个文件夹中，这些文件夹在 AUFS 中称作分支，整个『联合』的过程被称为联合挂载（Union Mount）：

每一个镜像层或者容器层都是 /var/lib/docker/ 目录下的一个子文件夹；
在 Docker 中，所有镜像层和容器层的内容都存储在 /var/lib/docker/aufs/diff/ 目录中
而 /var/lib/docker/aufs/layers/ 中存储着镜像层的元数据，每一个文件都保存着镜像层的元数据，
最后的 /var/lib/docker/aufs/mnt/ 包含镜像或者容器层的挂载点，
最终会被 Docker 通过联合的方式进行组装。

每一个镜像层都是建立在另一个镜像层之上的，同时所有的镜像层都是只读的，只有每个容器最顶层的容器层才可以被用户直接读写，所有的容器都建立在一些底层服务（Kernel）上，包括命名空间、控制组、rootfs 等等，这种容器的组装方式提供了非常大的灵活性，只读的镜像层通过共享也能够减少磁盘的占用。

9、其他存储驱动
AUFS 只是 Docker 使用的存储驱动的一种，除了 AUFS 之外，Docker 还支持了不同的存储驱动，包括 aufs、devicemapper、overlay2、zfs 和 vfs 等等，在最新的 Docker 中，overlay2 取代了 aufs 成为了推荐的存储驱动，但是在没有 overlay2 驱动的机器上仍然会使用 aufs 作为 Docker 的默认驱动。
  
不同的存储驱动在存储镜像和容器文件时也有着完全不同的实现



其实docker是一个内核的搬运工
所以虽然docker帮助我们准备好了rootfs地址，镜像里面的文件，以及各种资源隔离的配置，但是在启动一个容器的时候，它只是调用系统中早已内置的可以隔离资源的方法，而kernel支持这些方法，也是在创建进程的方法上做了一层资源隔离的扩展而已。

这就解释了docker两个特性：
启动速度快，因为本质来说容器和进程差别没有想象中的大，共享了很多代码，流程也差的不多
linux内核版本有最低的要求，因为linux是在某个版本后开始支持隔离特性



Docker本质上是运行在宿主机上的进程，它通过namespace实现了资源隔离，并通过cgroups实现了资源限制，同时通过写时复制（copy-on-write）实现了高效的文件操作。

Linux内核中提供了6种namespace隔离的系统调用，分别完成对文件系统、网络、进程间通信、主机名、进程号以及用户权限的隔离。


关于Docker实现原理，简单总结如下：
1、使用Namespaces实现了系统环境的隔离，Namespaces允许一个进程以及它的子进程从共享的宿主机内核资源（网络栈、进程列表、挂载点等）里获得一个仅自己可见的隔离区域，让同一个Namespace下的所有进程感知彼此变化，对外界进程一无所知，仿佛运行在一个独占的操作系统中；
2、使用CGroups限制这个环境的资源使用情况，比如一台16核32GB的机器上只让容器使用2核4GB。使用CGroups还可以为资源设置权重，计算使用量，操控任务（进程或线程）启停等；
3、使用镜像管理功能，利用Docker的镜像分层、写时复制、内容寻址、联合挂载技术实现了一套完整的容器文件系统及运行环境，再结合镜像仓库，镜像可以快速下载和共享，方便在多环境部署。









1、网址：
http://www.ducker.com/
https://hub.docker.com/
https://github.com/moby/moby
https://github.com/docker
https://docs.docker.com/config/daemon/


docker中文社区
http://www.docker.org.cn/
http://www.dockerinfo.net/


kubernetes社区
https://www.kubernetes.org.cn/


https://www.daocloud.io
https://github.com/DaoCloud



另外一个项目
https://github.com/linuxkit/linuxkit



docker教程学习参考
http://wiki.jikexueyuan.com/project/docker-technology-and-combat/appendix_useful.html
https://yeasy.gitbooks.io/docker_practice/image/build.html
http://www.ruanyifeng.com/blog/2018/02/docker-tutorial.html
https://yeasy.gitbooks.io/docker_practice/introduction/what.html


有用资源
Docker 主站点: https://www.docker.io
Docker 注册中心API: http://docs.docker.com/reference/api/registry_api/
Docker Hub API: http://docs.docker.com/reference/api/docker-io_api/
Docker 远端应用API: http://docs.docker.com/reference/api/docker_remote_api/
Dockerfile 参考：https://docs.docker.com/reference/builder/
Dockerfile 最佳实践：https://docs.docker.com/articles/dockerfile_best-practices/




Docker原理
https://draveness.me/docker
https://zhuanlan.zhihu.com/p/22382728
https://blog.csdn.net/omnispace/article/details/79778724
https://blog.csdn.net/zhaobryant/article/details/79599432
https://juejin.im/entry/5b7ae3286fb9a01a087aaa89





