Kubernetes基本概念和术语
Kubernetes的几个核心组件
K8s的安装：kubectl、minikube
minikube的使用：命令行，部署k8s集群
kubectl使用：命令行，运行Docker容器等，Kubernetes API是系统描述性配置的基础。 Kubectl 命令行工具被用于创建、更新、删除、获取API对象。
Kubernetes的yaml配置文件：Service和Deployment





1、Master：运行四个组件等，常用的Controller，运行的组件etcd、API Server、Controller Manager和Scheduler四个组件

Deployment Controller会持续监视Deployment的这些实例
有Node Controller，每个Node由 Master管理，Kubernetes Master会自动处理群集中Node的pod调度，同时Master的自动调度会考虑每个Node上的可用资源。
Replication Controller（RC）是Kubernetes系统中的核心概念，用于定义Pod副本的数量

2、Node节点：本身3组件+Pod，运行Kubelet、Proxy和Docker Daemon三个组件，Node上可以有多个pod，是Kubernetes中的工作节点，可以是虚拟机或物理机。

3、Pod：存储、网络、容器等，由一个或多个容器组合，Pod中的容器共享IP地址和端口、存储Volumes卷、网络等

4、Service：包含一组提供相同服务的Pod，Service作用于哪些Pod是通过Label Selector来定义的，Service的四种type

5、Deployment负责创建和更新应用：容器版本，运行副本数等，后续的运行pod实例有Deployment Controller监控和自我修复

6、Volume是Pod中能够被多个容器访问的共享目录。与容器的生命周期不相关。当容器终止或者重启时，Volume中的数据也不会丢失。并且一个Pod可以同时使用任意多个Volume。

7、Namespace（命名空间）

8、Label和Annotation：都是使用key/value键值对的形式进行定义。Label具有严格的命名规则，它定义的是Kubernetes对象的元数据（Metadata），并且用于Label Selector。Annotation则是用户任意定义的“附加”信息，以便于外部工具进行查找。

创建了一个Deployment，然后通过Service暴露，Deployment创建的Pod来运行应用



Kubernetes基本概念和术语：

Kubernetes 1.11.0 发布了，Kubernetes 是一个开源的，用于管理云平台中多个主机上的容器化的应用，Kubernetes 的目标是让部署容器化的应用简单并且高效（powerful），Kubernetes 提供了应用部署、规划、更新、维护的一种机制。

在Kubernetes中，Node、Pod、Replication Controller、Service等概念都可以看作一种资源对象，通过Kubernetes提供的Kubectl工具或者API调用进行操作，并保存在etcd中。
另外在所有节点上都可以运行Kubectl命令行工具，它提供了Kubernetes的集群管理工具集。

Kubernetes集群由两类节点组成：Master和Node
1、Master节点：协调和管理的节点，负责pod的调度，在Master上运行etcd、API Server、Controller Manager和Scheduler四个组件，其中后三个组件构成了Kubernetes的总控中心，负责对集群中所有资源进行管理和调度。
Kubernetes Master会自动处理群集中Node的pod调度，同时Master的自动调度会考虑每个Node上的可用资源。

Node Controller对Node进行管理
Node Controller是Kubernetes Master中的一个组件，用于管理Node对象。

Replication Controller（RC）是Kubernetes系统中的核心概念，用于定义Pod副本的数量
  Replication Controller（RC）是Kubernetes系统中的核心概念，用于定义Pod副本的数量。在Master内，Controller Manager进程通过RC的定义来完成Pod的创建、监控、启停等操作。
  Kubernetes API中负责来重新启动，迁移等行为的部分叫做“replication controller”

Deployment Controller会持续监视这些实例。如果管理实例的节点被关闭或删除，那么 Deployment Controller将会替换它们，实现自我修复能力。


2、Node（节点）：实际工作的节点，在每个Node上运行Kubelet、Proxy和Docker Daemon三个组件，负责对本节点上的Pod的生命周期进行管理，以及实现服务代理的功能。
当Kubelet的--register-node参数被设置为true（默认值即为true）时，Kubelet会向apiserver注册自己
一个Pod总是在一个（Node）节点上运行，Node是Kubernetes中的工作节点，可以是虚拟机或物理机。每个Node由 Master管理，Node上可以有多个pod，Kubernetes Master会自动处理群集中Node的pod调度，同时Master的自动调度会考虑每个Node上的可用资源。

每个Kubernetes Node上至少运行着：
Kubelet，管理Kubernetes Master和Node之间的通信; 管理机器上运行的Pods和containers容器。
container runtime（如Docker，rkt）。

Node节点：在Node上运行的服务进行包括Kubelet、kube-proxy和docker daemon。
  Node通常是物理机、虚拟机或者云服务商提供的资源，并不是由Kubernetes创建的。我们说Kubernetes创建一个Node，仅仅表示Kubernetes在系统内部创建了一个Node对象
  

  
3、Pod：包含多个容器，容器之上再封装一层
Pod是Kubernetes中一个抽象化概念，由一个或多个容器组合在一起得共享资源。
这些资源包括：
共享存储，如 Volumes 卷
网络，唯一的集群IP地址
每个容器运行的信息，例如:容器镜像版本

Pod模型是特定应用程序的“逻辑主机”，并且包含紧密耦合的不同应用容器。
  Pod中的容器共享IP地址和端口。
  Pod是Kubernetes的最基本操作单元，包含一个或者多个紧密相关的容器
  Pod是Kubernetes中的最小单位，当在Kubernetes上创建Deployment时，该Deployment将会创建具有容器的Pods（而不会直接创建容器），每个Pod将被绑定调度到Node节点上，并一直保持在那里直到被终止（根据配置策略）或删除。在节点出现故障的情况下，群集中的其他可用节点上将会调度之前相同的Pod。

Pod是有生命周期的。当一个工作节点(Node)销毁时，节点上运行的Pod也会销毁，然后通过ReplicationController动态创建新的Pods来保持应用的运行。
Pod的IP地址是Docker Daemon根据docker0网桥的IP地址段进行分配的，Pod在Kubernetes集群中生命周期较短，可能被ReplicationContrller销毁、再次创建，新创建的Pod将会分配一个新的IP地址。

  
4、Service：包含一组提供相同服务的Pod，在Pod正常启动后，系统将会根据Service的定义创建出与Pod对应的Endpoint（端点）对象，以建立起Service与后端Pod的对应关系。
  一个Service可以看作一组提供相同服务的Pod的对外访问接口。Service作用于哪些Pod是通过Label Selector来定义的。
  一个Service包含一组提供相同服务的Pod，一个Pod包含一个或者多个紧密相关的容器
  外部访问Service：Kubernetes支持两种对外提供服务的Service的type定义：NodePort和LoadBalancer。
  Service的Cluster IP地址是Kubernetes系统中的虚拟IP地址，由系统动态分配。Service的Cluster IP地址相对于Pod的IP地址来说相对稳定，Service被创建时即被分配一个IP地址，在销毁该Service之前，这个IP地址都不会再变化了。
  
  Kubernetes Service 是一个抽象层，它定义了一组逻辑的Pods，借助Service，应用可以方便的实现服务发现与负载均衡。
  Kubernetes中的Service 是一个抽象的概念，它定义了Pod的逻辑分组和一种可以访问它们的策略，这组Pod能被Service访问，使用YAML （优先）或JSON 来定义Service，Service所针对的一组Pod通常由LabelSelector实现
  Service集成了负载均衡器，可以将网络流量分配到Deployment暴露的所有Pod中。Service将使用Endpoints持续监控运行的Pod，以确保仅将流量分配到可用的Pod。
  
  可以通过type在ServiceSpec中指定一个需要的类型的 Service，Service的四种type:
  1、ClusterIP（默认） - 在集群中内部IP上暴露服务。此类型使Service只能从群集中访问。
  2、NodePort - 通过每个 Node 上的 IP 和静态端口（NodePort）暴露服务。NodePort 服务会路由到 ClusterIP 服务，这个 ClusterIP 服务会自动创建。通过请求 <NodeIP>:<NodePort>，可以从集群的外部访问一个 NodePort 服务。
  3、LoadBalancer - 使用云提供商的负载均衡器（如果支持），可以向外部暴露服务。外部的负载均衡器可以路由到 NodePort 服务和 ClusterIP 服务。
  4、ExternalName - 通过返回 CNAME 和它的值，可以将服务映射到 externalName 字段的内容，没有任何类型代理被创建。这种类型需要v1.7版本或更高版本kube-dnsc才支持。


  

5、Kubernetes  Deployment，
Deployment负责创建和更新应用。创建Deployment后，Kubernetes master 会将Deployment创建好的应用实例调度到集群中的各个节点。
应用实例创建完成后，Kubernetes Deployment Controller会持续监视这些实例。如果管理实例的节点被关闭或删除，那么 Deployment Controller将会替换它们，实现自我修复能力。
使用Kubernetes Kubectl（命令管理工具）创建和管理Deployment。Kubectl使用Kubernetes API与集群进行交互。
创建Deployment时，需要为应用程序指定容器镜像以及要运行的副本数，后续可以通过Deployment更新来更改该这些信息
创建Deployment时，Kubernetes会创建了一个Pod来托管应用

6、Volume是Pod中能够被多个容器访问的共享目录。与容器的生命周期不相关。当容器终止或者重启时，Volume中的数据也不会丢失。并且一个Pod可以同时使用任意多个Volume。

7、Namespace（命名空间）通过将系统内部的对象“分配”到不同的Namespace中，形成逻辑上分组的不同项目、小组或用户组，便于不同的分组在共享使用整个集群的资源的同时还能被分别管理。可以实现对用户的分组，即“多租户”管理。

8、Label是Kubernetes系统中的一个核心概念。Label以key/value键值对的形式附加到各种对象上，如Pod、Service、RC（Replication Controller）、Node等

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





Kubernetes API是系统描述性配置的基础。 Kubectl 命令行工具被用于创建、更新、删除、获取API对象。
kubectl安装：
OSX：
brew install kubernetes-cli
kubectl version
或者
curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.14.0/bin/darwin/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

Linux：
curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.14.0/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl


minikube安装
OSX:
brew cask install minikube
minikube version
或者
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.35.0/minikube-darwin-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube

Linux：
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.35.0/minikube-linux-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube


国内阿里云提供了一个修改版的Minikube，可以从阿里云的镜像地址来获取所需Docker镜像和配置。

Mac OSX
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.35.0/minikube-darwin-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

Linux
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.35.0/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

自己构建
也可以从Github上获取相应的项目自行构建。
注：需要本地已经安装配置好 Golang 开发环境和Docker引擎
git clone https://github.com/AliyunContainerService/minikube
cd minikube
git checkout aliyun-v0.25.0
make
sudo cp out/minikube /usr/local/bin/

启动
缺省Minikube使用VirtualBox驱动来创建Kubernetes本地环境
minikube start --registry-mirror=https://registry.docker-cn.com

# 安装Kubernetes v1.12.1
minikube start --registry-mirror=https://registry.docker-cn.com --kubernetes-version v1.12.1
# 安装Kubernetes v1.11.3
minikube start --registry-mirror=https://registry.docker-cn.com --kubernetes-version v1.11.3



命令
minikube version
minikube start --registry-mirror=https://registry.docker-cn.com
minikube start -p 名字  没有-p参数设置名字，就是启动默认的名字minikube
minikube dashboard 打开Kubernetes控制台
minikube stop


卸载
删除~/.minikube和/usr/local/bin/minikube





kubectl使用：
kubectl version

kubectl create -f deployment.yaml
kubectl apply -f install/kubernetes/istio-demo.yaml  安装 Istio 的自定义资源定义（CRD）
kubectl delete -f install/kubernetes/istio-demo.yaml

kubectl get svc -n istio-system   查看服务在stio-system 命名空间
kubectl get pods -n istio-system   查看pod
kubectl get deployments


kubectl get cs（componentstatuses）
minikube status


kubectl proxy --port=8080 &


kubectl get - 列出资源
kubectl describe - 显示资源的详细信息
kubectl logs - 打印pod中的容器日志
kubectl exec - pod中容器内部执行命令


kubectl get  查看
kubectl describe  显示详情

kubectl cp 复制到容器 或者 从容器复制出，复制 files 和 directories 到 containers 和从容器中复制 files 和 directories.
kubectl run            在集群中运行一个指定的镜像
kubectl attach  Attach 到一个运行中的 container
kubectl exec  在一个 container 中执行一个命令
kubectl logs 输出日志，输出容器在 pod 中的日志

label          更新在这个资源上的 labels
annotate       更新一个资源的注解

describe       显示一个指定 resource 或者 group 的 resources 详情
  port-forward   Forward one or more local ports to a pod
  proxy          运行一个 proxy 到 Kubernetes API server
  auth           Inspect authorization
  


我使用kubectl replace deployment对象 但是pod没有更新 有人知道咋回事么
--cascade=true replace 后面加个参数解决pod不更新问题

运行一个镜像
kubectl run sonarqube --image=192.168.32.131:5000/sonarqube:5.6.5 --replicas=1 --port=9000

使用kubectl delete删除：可以删除pod或者deployment，直接删除pod触发了replicas的确保机制，也就是删除的同时会重新创建一个
kubectl delete pods sonarqube-1880671902-s3fdq   
kubectl delete deployment sonarqube


kubectl get ns
kubectl create namespace test
kubectl delete namespace test
kubectl edit namespace test



kubectl get deployments  -n istio-system
kubectl get svc -n istio-system
kubectl get pods -n istio-system






Kubernetes的yaml配置文件










https://github.com/Aliyun
https://github.com/AliyunContainerService



Kubernetes介绍
https://kubernetes.io
https://github.com/kubernetes/minikube
https://kubernetes.io/docs/setup/minikube/
https://kubernetes.io/docs/tasks/tools/install-minikube/
https://kubernetes.io/docs/tasks/tools/install-kubectl/

https://www.jianshu.com/p/63ffc2214788
https://www.kubernetes.org.cn/kubernetes%E8%AE%BE%E8%AE%A1%E6%9E%B6%E6%9E%84
https://www.bookstack.cn/read/kubernetes-handbook/SUMMARY.md


Kubernetes使用示例
https://github.com/kubernetes/examples
https://github.com/kubernetes/client-go



Kubernetes之kubectl常用命令
https://blog.csdn.net/liumiaocn/article/details/73913597

Kubernetes--命名空间（Namespace）
https://blog.csdn.net/wang725/article/details/82845070
https://blog.csdn.net/ouyangtianhan/article/details/85107967



Kubernetes中文手册
http://docs.kubernetes.org.cn/124.html
https://www.kubernetes.org.cn/docs
https://www.kubernetes.org.cn/docs
http://docs.kubernetes.org.cn/
https://kubernetes.io/zh/docs/tutorials/
https://kubernetes.io/docs/tasks/manage-gpus/scheduling-gpus/
https://kubernetes.io/zh/docs/tasks/access-application-cluster/list-all-running-container-images/




Kubernetes的yaml配置文件
https://blog.csdn.net/wucong60/article/details/81161360
https://blog.csdn.net/xuleisdjn/article/details/79023487
https://blog.csdn.net/phantom_111/article/details/79427144
https://www.qikqiak.com/post/use-yaml-create-kubernetes-deployment/
https://www.kubernetes.org.cn/1414.html
https://blog.csdn.net/zll_0405/article/details/85222443
https://www.cnblogs.com/xiaochangwei/p/9165653.html




Kubernetes的三种外部访问方式：NodePort、LoadBalancer和Ingress
https://blog.csdn.net/M2l0ZgSsVc7r69eFdTj/article/details/79988685






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




