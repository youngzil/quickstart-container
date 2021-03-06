## 容器的介绍


### 容器的概念

- Docker
- LXC
- KVM
- Kubernetes（K8S）
- Marathon马拉松
- 微服务/Micro Service
- 服务网格/Service Mesh
- Istio
- YARN
- Mesos
- 云原生/Cloud Native
- 无服务器架构/Serverless


容器编排框架


资源协调/调度
Mesos/资源调度、资源协调
YARN/资源协调者



江湖传言，软件正在吃掉世界，而容器正在吃掉整个软件世界。虽然有些夸张，但也充分证明了容器在当今软件研发领域的地位。顺势而为，是否掌握容器技术也成为很多企业在招聘时的一个重要选项。
应用程序越来越多的使用微服务，一个应用程序被分为若干个微服务，运行许多微服务可能很复杂，因此管理它们需要一个协调器（有时也称为调度程序），用于跨数据中心发布分布式服务。调度器有很多选择。市面上流行的调度程序有：Mesos（Mesosphere）、Kubernetes（Google）、Docker Swarm（Docker）等。

相比于传统作业编排调度管理项目（比如 Mesos 和 Yarn 等）关注的资源效能问题，Kubernetes 的核心竞争力则一直放在工作负载描述、服务发现、容器设计模式等更高纬度的应用基础设施构建上。

Kubernetes 的规模性问题又可以细分为：以 etcd 为代表的“数据面”、以 kube-apiserver 为代表“管控面”和以 kubelet 及各种 Controller 组成的“生产 / 消费面”三个问题域。在场景的推动下，Kubernetes 以及 etcd 社区在过去的一年里，正是围绕这三个问题域进行了大量的优化，例如：

数据面：通过优化 etcd 底层数据库的数据结构和算法，将 etcd 百万键值对随机写性能提升 24 倍；

管控面：为 kube-apiserver 添加 Bookmark 机制，将 APIServer 重启时需要重新同步的事件降低为原来的 3%，性能提高了数十倍；

生产 / 消费面：将 Kubernetes 节点向 APIServer 的心跳机制从“定时发送”修改为“按需发送”，从而大大减少了规模化场景下 kubelet 对 APIServer 带来的巨大压力，大幅提高了 Kubernetes 所能支持的节点数目上限。

问题：比如：如何让多个 kube-apiserver 更均衡的处理生产 / 消费请求、避免性能热点；如何通过合理的设置主备 Controller，让升级 Controller 时无需大量重新同步数据，从而降低 controller 恢复时对 API Server 的性能冲击等等。

参考
https://www.infoq.cn/article/3NAAfSt2O57xZVrL7eWO






容器平台：
Docker 2019年占据了容器平台市场的大部分份额，占比为 79%
containerd 是从 Docker 中剥离出来的容器虚拟化技术
CRI-O 是 Kubernetes 的轻量级运行时，最初是由 Red Hat 启动，目前由 CNCF 托管。
OpenShift 作为本地容器编排平台



容器编排平台：
开源编排工具包括 Docker Swarm、Kubernetes、Marathon 和 Nomad
Kubernetes
OpenShift 和 Rancher 其实也是基于 Kubernetes 构建的
Docker Swarm
Mesos
Marathon

https://www.infoq.cn/article/2017%2F02%2Fcompare-container-orchestration



自定义监控解决方案：
比较主流的三种解决方案分别是 ：Prometheus、JMX、StatsD
作为 CNCF 中最成功的开源项目之一，Prometheus 已经成为了云原生监控的代名词，被广泛应用在 Kubernetes、OpenShift 和 Istio 等项目中



架构演进两大方向，一个是Serverless，另一个是Service Mesh
Service Mesh帮助应用程序在海量服务、复杂的架构和网络中建立稳定的通信机制，业务所有的流量都转发到Service Mesh的代理服务中。 不仅如此，Service Mesh还承担了微服务框架所有的功能，包括服务注册发现、负载均衡、熔断限流、认证鉴权、缓存加速等。
不同的是，Service Mesh强调的是通过独立的进程代理的方式，除此之外，还承担了上报日志、监控的责任。
Service Mesh的出现是由微服务架构推动的，随着一个应用被拆分为几百个甚至几万个应用，服务治理面临巨大的挑战。这个时候，微服务框架出现，例如，Finagle、Dubbo、SpringCloud、Netflix OSS等。这些框架都是基于客户端负载均衡直连的方式，此方案的优势是性能高、应用性好，如Spring Cloud。
归根结底，在微服务架构中，我们要解决的问题是，让开发人员感觉不到微服务之间的通信。当服务数量越来越多，升级微服务框架变得越来越复杂的时候，你不可能要求微服务框架一直不变，而且是没有bug的。在技术更新如此之快的年代，就更不可能了。 因此，微服务框架的部分功能开始逐步向服务端移动，希望客户端可以尽量“薄”，但是客户端不可能无限制的“薄”，剩余部分仍然比较“厚”。
因为使用客户端更像一种交付的模式，不容易变更，控制力较差，而Service Mesh则从业务进程集成客户端的方式演进为独立进程的方式，也就是说，原本的客户端变成了一个独立进程。对这个独立进程升级、运维要比绑在一起强得多。
微服务架构更强调去中心化、独立自治、跨语言，但是通常微服务框架限制了这一点，不可能为每种语言都实现一种框架，要么都用一种语言，要么实现多种语言的框架。而Service Mesh通过独立进程的方式进行了隔离，可以低成本实现跨语言。 随着Docker及Kubernetes的崛起，微服务的部署模式开始发生转变，越来越趋向于轻量级，越来越强调隔离自治。每个服务独立占用一个容器，将服务、依赖包、操作系统、监控运维所需的代理打包成一个镜像。这种模式促成了Service Mesh的发展，让Service Mesh实现起来更容易。否则开发人员需要额外维护Service Mesh进程，就非常麻烦了。



Service Mesh项目：
先驱者如Linkerd
最值得注意的是Istio，因为它的背后是Google和IBM
Envoy，它在性能和资源消耗上表现得非常出色，被Istio收编之后， 专注于数据平面。
当然业内还有其他一些框架，由于暂时没有投入到生产环境，进展有限，可以关注一下，比如Conduit 、nginMesh等。




Docker
Kubernetes、docker-compose、Mesos、docker-swarm、docker-machine
Istio
DevOps




Docker：Linux 容器的一种封装，属于 操作系统层面的虚拟化技术。
Docker 属于 Linux 容器的一种封装，提供简单易用的容器使用接口。它是目前最流行的 Linux 容器解决方案。
Docker 将应用程序与该程序的依赖，打包在一个文件里面。运行这个文件，就会生成一个虚拟容器。程序在这个虚拟容器里运行，就好像在真实的物理机上运行一样。有了 Docker，就不用担心环境问题。
Docker 使用 Google 公司推出的 Go 语言 进行开发实现，基于 Linux 内核的 cgroup，namespace，以及 AUFS 类的 Union FS 等技术，对进程进行封装隔离，属于 操作系统层面的虚拟化技术。由于隔离的进程独立于宿主和其它的隔离的进程，因此也称其为容器。最初实现是基于 LXC，从 0.7 版本以后开始去除 LXC，转而使用自行开发的 libcontainer，从 1.11 开始，则进一步演进为使用 runC 和 containerd。
Docker 在容器的基础上，进行了进一步的封装，从文件系统、网络互联到进程隔离等等，极大的简化了容器的创建和维护。使得 Docker 技术比虚拟机技术更为轻便、快捷。
随着Docker的不断流行与发展，docker公司（或称为组织）也开启了商业化之路，Docker 从 17.03版本之后分为 CE（Community Edition） 和 EE（Enterprise Edition）。




Kubernetes：容器编排部署
Kubernetes（常简称为K8s）是用于自动部署、扩展和管理容器化（containerized）应用程序的开源系统。
Kubernetes 是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes 的目标是让部署容器化的应用简单并且高效（powerful），Kubernetes 提供了应用部署、规划、更新、维护的一种机制。



Istio：微服务管控、开源的连接，管理和安全的微服务。
Istio 提供一种简单的方式来为已部署的服务建立网络，该网络具有负载均衡、服务间认证、监控等功能，只需要对服务的代码进行一点或不需要做任何改动。

Istio：开源的连接，管理和安全的微服务。Istio提供了一种简单方式，让发布的服务创建连接并实现负载均衡，服务间的认证，监控，还有更多，而在服务中不需要改变任何代码。接入Istio，支持服务由特定的代理发布，在服务之间会拦截网络通信并贯穿你的环境，使用Istio可以配置和管理整个面的够功能特性。

Service Mesh 提供了一种透明的、与编程语言无关的方式，使网络配置、安全配置以及遥测等操作能够灵活而简便地实现自动化。从本质上说，它解耦了服务的开发与运维工作。
处于服务与底层网络之间的这一层基础设施通常被称为 Service Mesh。



DevOps（Development和Operations的组合词）是一种重视“软件开发人员（Dev）”和“IT运维技术人员（Ops）”之间沟通合作的文化、运动或惯例。透过自动化“软件交付”和“架构变更”的流程，来使得构建、测试、发布软件能够更加地快捷、频繁和可靠。
它的出现是由于软件行业日益清晰地认识到：为了按时交付软件产品和服务，开发和运营工作必须紧密合作。
可以把DevOps看作开发（软件工程）、技术运营和质量保障（QA）三者的交集。




https://github.com/yeasy/docker_practice


基于容器的微服务架构技术选型与设计
https://www.infoq.cn/article/oT88vWzjaI2RVjBXcASc



容器的好处
轻便
容器占用的服务器空间比虚拟机少，通常只需几秒钟即可启动。
弹性
容器具有高弹性，不需要分配给定数量的资源。这意味着容器能够更有效地动态使用服务器中的资源。当一个容器上的需求减少时，释放额外的资源供其他容器使用。
密度
密度是指一次可以运行单个物理服务器的对象数。容器化允许创建密集的环境，其中主机服务器的资源被充分利用但不被过度利用。与传统虚拟化相比，容器化允许更密集的环境容器不需要托管自己的操作系统。
性能
当资源压力很大时，应用程序的性能远远高于使用虚拟机管理程序的容器。因为使用传统的虚拟化，客户操作系统还必须满足其自身的内存需求，从主机上获取宝贵的RAM。
维护效率
只有一个操作系统内核，操作系统级别的更新或补丁只需要执行一次，以使更改在所有容器中生效。这使得服务器的操作和维护更加高效。



Antrea

基于Open vSwitch的Kubernetes网络

Kubernetes networking based on Open vSwitch

https://antrea.io/  
https://github.com/vmware-tanzu/antrea/

Antrea是一个Kubernetes网络解决方案，旨在成为Kubernetes的本机。它使用Open vSwitch作为网络数据平面，在Layer3 / 4上运行，为Kubernetes集群提供网络和安全服务 。

Open vSwitch是一种广泛采用的高性能可编程虚拟交换机。Antrea利用它来实现Pod网络和安全功能。例如，Open vSwitch使Antrea能够以非常有效的方式实施Kubernetes网络策略。



etcdhosts 是一个 CoreDNS 插件，通过将 hosts 配置存储在 etcd 中实现分布式一致性查询  
https://github.com/Gozap/gdns  




