







---------------------------------------------------------------------------------------------------------------------




CRI - Container Runtime Interface（容器运行时接口）

CRI中定义了容器和镜像的服务的接口，因为容器运行时与镜像的生命周期是彼此隔离的，因此需要定义两个服务，该接口使用Protocol Buffer，基于gRPC。

Container Runtime实现了CRI gRPC Server，包括RuntimeService和ImageService。该gRPC Server需要监听本地的Unix socket，而kubelet则作为gRPC Client运行


这其中包含了两个gRPC服务：
- RuntimeService：容器和Sandbox运行时管理
- ImageService：提供了从镜像仓库拉取、查看、和移除镜像的RPC。


---------------------------------------------------------------------------------------------------------------------


CNI - Container Network Interface(容器网络接口)

CNI(Container Network Interface) 是 google 和 CoreOS 主导制定的容器网络标准，它本身并不是实现或者代码，可以理解成一个协议。这个标准是在 rkt 网络提议的基础上发展起来的，综合考虑了灵活性、扩展性、ip 分配、多网卡等因素.

这个协议连接了两个组件：容器管理系统和网络插件。它们之间通过 JSON 格式的文件进行通信，实现容器的网络功能。具体的事情都是插件来实现的，包括：创建容器网络空间（network namespace）、把网络接口（interface）放到对应的网络空间、给网络接口分配 IP 等等

该接口只有四个方法，添加网络、删除网络、添加网络列表、删除网络列表。



---------------------------------------------------------------------------------------------------------------------

OCI - Open Container Initiative

[OCI官网](https://opencontainers.org/)


谈到OCI,会想到docker,先有docker后有OCI,说到docker就说到容器技术, docker不是最早的容器技术,比如早期的chroot Jails,Solaris Containers等,但是是docker把容器技术推向了巅峰,让更多人关注并使用了容器技术

docker自2013发布以来,github中docker的代码活跃度居高不下,更多的个人或企业使用docker,容器就是docker也逐渐被大家认可,如果docker就是容器的标准,那其他容器怎么办(比如coreOS推出的rkt),

其次容器上层的编排工具(容器集群调度,比如kubernetes,mesos等)和docker紧密耦合,docker 接口的变化导致上层编排的稳定性甚至服务异常.


如果容器以docker作为标准,那么docker接口的变化可能导致社区中所有相关工具都要更新,不然就无法使用,如果没有标准,这将导致容器实现的碎片化,出现大量的冲突和冗余,
这两种情况都是社区不愿意看到的事情，于是OCI出现了

它的核心目标围绕容器的格式和运行时制定一个开放的工业化标准,并推动这个标准,保持容器的灵活性和开放性,容器能运行在任何的硬件和系统上.
容器不应该绑定到特定的客户机或编排堆栈,不应该与任何特定的供应商紧密关联,并且可以跨多种操作系统





Linux基金会于2015年6月成立OCI（Open Container Initiative）组织，旨在围绕容器格式和运行时制定一个开放的工业化标准，目前主要有两个标准文档：容器运行时标准 （runtime spec）和 容器镜像标准（image spec）

the Runtime Specification (runtime-spec) and the Image Specification (image-spec).

制定容器格式标准的宗旨概括来说就是不受上层结构的绑定，如特定的客户端、编排栈等，同时也不受特定的供应商或项目的绑定，即不限于某种特定操作系统、硬件、CPU架构、公有云等。


这两个协议通过 OCI runtime filesytem bundle 的标准格式连接在一起，OCI 镜像可以通过工具转换成 bundle，然后 OCI 容器引擎能够识别这个 bundle 来运行容器


文档主要做了两个事情:
- 创建镜像的规则
- 运行镜像的规则



image spec（容器标准包）

OCI 容器镜像主要包括几块内容：
- 文件系统：以 layer 保存的文件系统，每个 layer 保存了和上层之间变化的部分，layer 应该保存哪些文件，怎么表示增加、修改和删除的文件等
- config 文件：保存了文件系统的层级信息（每个层级的 hash 值，以及历史信息），以及容器运行时需要的一些信息（比如环境变量、工作目录、命令参数、mount 列表），指定了镜像在某个特定平台和系统的配置。比较接近我们使用 docker inspect <image_id> 看到的内容
- manifest 文件：镜像的 config 文件索引，有哪些 layer，额外的 annotation 信息，manifest 文件中保存了很多和当前平台有关的信息
- index 文件：可选的文件，指向不同平台的 manifest 文件，这个文件能保证一个镜像可以跨平台使用，每个平台拥有不同的 manifest 文件，使用 index 作为索引





runtime spec（容器运行时和生命周期）

容器运行时

容器标准格式也要求容器把自身运行时的状态持久化到磁盘中，这样便于外部的其它工具对此信息使用和演绎。该运行时状态以JSON格式编码存储。推荐把运行时状态的JSON文件存储在临时文件系统中以便系统重启后会自动移除。




---------------------------------------------------------------------------------------------------------------------



CRI OCI区别

Open Container Initiative，也就是常说的OCI，是由多家公司共同成立的项目，并由linux基金会进行管理，致力于container runtime的标准的制定和runc的开发等工作。所谓container runtime，主要负责的是容器的生命周期的管理。oci的runtime spec标准中对于容器的状态描述，以及对于容器的创建、删除、查看等操作进行了定义。

在k8s 1.5版本之后，kubernetes推出了自己的运行时接口api–CRI(container runtime interface)。cri接口的推出，隔离了各个容器引擎之间的差异，而通过统一的接口与各个容器引擎之间进行互动。

与oci不同，cri与kubernetes的概念更加贴合，并紧密绑定。cri不仅定义了容器的生命周期的管理，还引入了k8s中pod的概念，并定义了管理pod的生命周期。在kubernetes中，pod是由一组进行了资源限制的，在隔离环境中的容器组成。而这个隔离环境，称之为PodSandbox。在cri开始之初，主要是支持docker和rkt两种。其中kubelet是通过cri接口，调用docker-shim，并进一步调用docker api实现的。

后来，docker独立出来了containerd,kubernetes也顺应潮流，孵化了cri-containerd项目，用以将containerd接入到cri的标准中。


为了进一步与oci进行兼容，kubernetes还孵化了cri-o，成为了架设在cri和oci之间的一座桥梁。通过这种方式，可以方便更多符合oci标准的容器运行时，接入kubernetes进行集成使用。可以预见到，通过cri-o，kubernetes在使用的兼容性和广泛性上将会得到进一步加强






参考  
[容器开放接口规范（CRI OCI CNI）](https://www.jianshu.com/p/62e71584d1cb)



