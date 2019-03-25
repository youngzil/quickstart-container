Kubernetes 1.11.0 发布了，Kubernetes 是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes 的目标是让部署容器化的应用简单并且高效（powerful），Kubernetes 提供了应用部署、规划、更新、维护的一种机制。



Kubernetes基本概念和术语：
在Kubernetes中，Node、Pod、Replication Controller、Service等概念都可以看作一种资源对象，通过Kubernetes提供的Kubectl工具或者API调用进行操作，并保存在etcd中。

Kubernetes集群由两类节点组成：Master和Node
1、Master节点：协调和管理的节点，在Master上运行etcd、API Server、Controller Manager和Scheduler四个组件，其中后三个组件构成了Kubernetes的总控中心，负责对集群中所有资源进行管理和调度。
2、Node（节点）或者slave节点：实际工作的节点，在每个Node上运行Kubelet、Proxy和Docker Daemon三个组件，负责对本节点上的Pod的生命周期进行管理，以及实现服务代理的功能。
当Kubelet的--register-node参数被设置为true（默认值即为true）时，Kubelet会向apiserver注册自己

另外在所有节点上都可以运行Kubectl命令行工具，它提供了Kubernetes的集群管理工具集。

1、Node节点：在Node上运行的服务进行包括Kubelet、kube-proxy和docker daemon。
  Node通常是物理机、虚拟机或者云服务商提供的资源，并不是由Kubernetes创建的。我们说Kubernetes创建一个Node，仅仅表示Kubernetes在系统内部创建了一个Node对象
2、Node Controller对Node进行管理
  Node Controller是Kubernetes Master中的一个组件，用于管理Node对象。
3、Pod：包含多个容器，容器之上再封装一层
  Pod是Kubernetes的最基本操作单元，包含一个或者多个紧密相关的容器
  一个Service可以看作一组提供相同服务的Pod的对外访问接口。Service作用于哪些Pod是通过Label Selector来定义的。
  一个Service包含一组提供相同服务的Pod，一个Pod包含一个或者多个紧密相关的容器
  外部访问Service：Kubernetes支持两种对外提供服务的Service的type定义：NodePort和LoadBalancer。
  Service的Cluster IP地址是Kubernetes系统中的虚拟IP地址，由系统动态分配。Service的Cluster IP地址相对于Pod的IP地址来说相对稳定，Service被创建时即被分配一个IP地址，在销毁该Service之前，这个IP地址都不会再变化了。
  Pod的IP地址是Docker Daemon根据docker0网桥的IP地址段进行分配的，Pod在Kubernetes集群中生命周期较短，可能被ReplicationContrller销毁、再次创建，新创建的Pod将会分配一个新的IP地址。
4、Service：包含一组提供相同服务的Pod，在Pod正常启动后，系统将会根据Service的定义创建出与Pod对应的Endpoint（端点）对象，以建立起Service与后端Pod的对应关系。
5、Replication Controller（RC）是Kubernetes系统中的核心概念，用于定义Pod副本的数量
  Replication Controller（RC）是Kubernetes系统中的核心概念，用于定义Pod副本的数量。在Master内，Controller Manager进程通过RC的定义来完成Pod的创建、监控、启停等操作。
6、Label是Kubernetes系统中的一个核心概念。Label以key/value键值对的形式附加到各种对象上，如Pod、Service、RC（Replication Controller）、Node等
7、Volume是Pod中能够被多个容器访问的共享目录。与容器的生命周期不相关。当容器终止或者重启时，Volume中的数据也不会丢失。并且一个Pod可以同时使用任意多个Volume。
8、Namespace（命名空间）通过将系统内部的对象“分配”到不同的Namespace中，形成逻辑上分组的不同项目、小组或用户组，便于不同的分组在共享使用整个集群的资源的同时还能被分别管理。可以实现对用户的分组，即“多租户”管理。
9、Annotation与Label类似，也使用key/value键值对的形式进行定义。Label具有严格的命名规则，它定义的是Kubernetes对象的元数据（Metadata），并且用于Label Selector。Annotation则是用户任意定义的“附加”信息，以便于外部工具进行查找。


Kubernetes主要由以下几个核心组件组成：
1、etcd保存了整个集群的状态；
2、apiserver提供了资源操作的唯一入口，并提供认证、授权、访问控制、API注册和发现等机制；
3、controller manager负责维护集群的状态，比如故障检测、自动扩展、滚动更新等；
4、scheduler负责资源的调度，按照预定的调度策略将Pod调度到相应的机器上；
5、kubelet负责维护容器的生命周期，同时也负责Volume（CVI）和网络（CNI）的管理；
6、Container runtime负责镜像管理以及Pod和容器的真正运行（CRI）；
7、kube-proxy负责为Service提供cluster内部的服务发现和负载均衡；

除了核心组件，还有一些推荐的Add-ons：
1、kube-dns负责为整个集群提供DNS服务
2、Ingress Controller为服务提供外网入口
3、Heapster提供资源监控
4、Dashboard提供GUI
5、Federation提供跨可用区的集群
6、Fluentd-elasticsearch提供集群日志采集、存储与查询








Kubernetes介绍
https://www.jianshu.com/p/63ffc2214788
https://www.kubernetes.org.cn/kubernetes%E8%AE%BE%E8%AE%A1%E6%9E%B6%E6%9E%84
https://www.kubernetes.org.cn/docs

kubernetes 对比 mesos + marathon
https://www.jianshu.com/p/c734a3fe205b



容器最大的好处之一就是它能够在一个预先构建的容器中部署所有的内容（比如 Linux 发行版、JVM、应用服务器、库、配置，最后还有你的应用）。
另外，在一个容器中将所有的东西都包含进来能够更容易地将你的代码转移到生产环境中，在它无法正常运行的时候，也更容易分析其中的差异。
因为它易于执行，所以也很容易将相同的容器镜像扩展至多个副本。







CKA全称为（Certificated Kubernetes Administrator）即为官方认证的Kubernetes管理员。
CNCF云原生计算基金会：认证机构为Linux Foundation组织旗下的Cloud Native Computing Foundatin组织。

CKA网站
https://www.cncf.io/certification/cka/
https://jimmysong.io/kubernetes-handbook/appendix/about-cka-candidate.html

CNCF网站
https://www.cncf.io/
https://jimmysong.io/kubernetes-handbook/cloud-native/cncf.html


https://mp.weixin.qq.com/s?__biz=MzIzNjUxMzk2NQ==&mid=2247489859&idx=1&sn=5c15ac67959f03136bffbeebfa55d288&chksm=e8d7e681dfa06f97191195573d9f53525752739ef9393093d5c110b2ac5bd745b0e407716197&scene=27#wechat_redirect




