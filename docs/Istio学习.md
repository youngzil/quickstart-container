```
Service Mesh服务网格定义
Istio的功能
Istio的架构：数据平面、控制平面，各有什么组件
Istio的安装
三种微服务落地的利器：Docker容器、Kubernetes、Service Mesh服务网格Istio
微服务治理框架：传统的Spring cloud、dubbo等、Service Mesh服务网格方式和Istio




https://www.servicemesher.com/istio-handbook/concepts-and-principle/sidecar-injection-deep-dive.html
https://skyao.io/post/201809-xprotocol-common-address-solution/




部署学习
https://github.com/ganity/istio-kubernetes-example




服务网格（Service Mesh）这个术语通常用于描述构成这些应用程序的微服务网络以及应用之间的交互。随着规模和复杂性的增长，服务网格越来越难以理解和管理。它的需求包括服务发现、负载均衡、故障恢复、指标收集和监控以及通常更加复杂的运维需求，例如 A/B 测试、金丝雀发布、限流、访问控制和端到端认证等。


Istio 提供一种简单的方式来为已部署的服务建立网络，该网络具有负载均衡、服务间认证、监控等功能，只需要对服务的代码进行一点或不需要做任何改动。想要让服务支持 Istio，只需要在您的环境中部署一个特殊的 sidecar 代理，使用 Istio 控制平面功能配置和管理代理，拦截微服务之间的所有网络通信：
HTTP、gRPC、WebSocket 和 TCP 流量的自动负载均衡。
通过丰富的路由规则、重试、故障转移和故障注入，可以对流量行为进行细粒度控制。
可插入的策略层和配置 API，支持访问控制、速率限制和配额。
对出入集群入口和出口中所有流量的自动度量指标、日志记录和追踪。
通过强大的基于身份的验证和授权，在集群中实现安全的服务间通信。


Istio带给你：
HTTP、gRPC、WebSocket和TCP流量的自动负载均衡。
通过丰富的路由规则、重试、故障转移和故障注入对流量行为进行细粒度控制。
支持访问控制、速率限制和配额的可拔插策略层和配置API。
自动指标、日志和集群内所有流量的跟踪，包括集群入口和出口。
通过集群中的服务之间的强身份断言来实现服务间的身份验证。



架构
Istio 服务网格逻辑上分为数据平面和控制平面。
1、数据平面由一组以 sidecar 方式部署的智能代理（Envoy）组成。这些代理可以调节和控制微服务及 Mixer 之间所有的网络通信。
2、控制平面负责管理和配置代理来路由流量。此外控制平面配置 Mixer 以实施策略和收集遥测数据。

Envoy 被部署为 sidecar，或者说Envoy使用sidecar模式部署
Mixer 是一个独立于平台的组件，负责在服务网格上执行访问控制和使用策略，并从 Envoy 代理和其他服务收集遥测数据。代理提取请求级属性，发送到 Mixer 进行评估。
Pilot 为 Envoy sidecar 提供服务发现功能，为智能路由（例如 A/B 测试、金丝雀部署等）和弹性（超时、重试、熔断器等）提供流量管理功能。它将控制流量行为的高级路由规则转换为特定于 Envoy 的配置，并在运行时将它们传播到 sidecar。
Citadel 通过内置身份和凭证管理赋能强大的服务间和最终用户身份验证。可用于升级服务网格中未加密的流量，并为运维人员提供基于服务标识而不是网络控制的强制执行策略的能力。
Galley 代表其他的 Istio 控制平面组件，用来验证用户编写的 Istio API 配置。随着时间的推移，Galley 将接管 Istio 获取配置、 处理和分配组件的顶级责任。它将负责将其他的 Istio 组件与从底层平台（例如 Kubernetes）获取用户配置的细节中隔离开来。


英语单词
Envoy：使者、全权公使
Mixer：搅拌器、混合器
Pilot：领航员、宇航员、飞行员
Citadel：城堡、大本营、避难处
Galley：船上的厨房、单层甲板的大帆船



Istio的安装
1、手动下载并且解压缩 或者 在macOS 或者 Linux 系统中，还可以运行下面的命令，进行下载和自动解压缩：curl -L https://git.io/getLatestIstio | ISTIO_VERSION=1.1.1 sh -

2、进入 Istio 包目录。cd istio-1.1.1
安装目录中包含：
在 install/ 目录中包含了 Kubernetes 安装所需的 .yaml 文件
samples/ 目录中是示例应用
istioctl 客户端文件保存在 bin/ 目录之中。istioctl 的功能是手工进行 Envoy Sidecar 的注入。
istio.VERSION 配置文件

3、把 istioctl 客户端加入 PATH 环境变量，如果是 macOS 或者 Linux，可以这样实现：
$ export PATH=$PWD/bin:$PATH


4、使用 kubectl apply 安装 Istio 的自定义资源定义（CRD），几秒钟之后，CRD 被提交给 Kubernetes 的 API-Server：
for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done

5、从下列的几个演示配置中选择一个进行安装，运行下面的命令即可完成这一模式的安装：
kubectl apply -f install/kubernetes/istio-demo.yaml

6、查看Istio服务是否部署,是否安装正常,确认下列 Kubernetes 服务已经部署并都具有各自的 CLUSTER-IP：
kubectl get svc -n istio-system

7、查看pods，确认必要的 Kubernetes Pod 都已经创建并且其 STATUS 的值是 Running：
kubectl get pods -n istio-system

8、删除 RBAC 权限、istio-system 命名空间及其所有资源。因为有些资源会被级联删除，因此会出现一些无法找到资源的提示，可以忽略。
$ kubectl delete -f install/kubernetes/istio-demo.yaml
也可以根据需要删除 CRD：
$ for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl delete -f $i; done









三种微服务落地的利器：
1、Docker容器：解决了微服务部署和隔离的问题，通过使用容器技术，每个微服务的交付物被统一成一个格式，在隔离的环境中运行，容器内部的环境和依赖交给应用开发人员来处理。所以说，容器加速了微服务的落地。
2、Kubernetes：大规模容器的管理、监控和调度功能，此外它抽象出Service的概念作为微服务，使用Kubeproxy和Ingress做为内外的负载均衡，每个Service自动注册到Etcd，通过Service 名称可以获取调用地址。
3、Service Mesh服务网格：服务管理、部署、版本控制、安全、熔断限流，以及一些复杂部署功能如A/B测试、金丝雀发布、智能路由、访问控制等。
Istio在同一个POD的应用容器旁边注入一个sidecar容器，这个sidecar起到一个类似代理的作用，将服务之间调用的治理逻辑和功能完全接管。这样的好处是处理业务逻辑的容器完全不需要考虑微服务治理的功能，代码只和业务相关。另外一个好处是在微服务治理过程中的规则和逻辑可以随时调整，由istio的控制面下发规则到sidecar容器。由于服务之间调用采用标准的网络协议，和语言无关，业务容器和sidecar容器独立存在，所以每个微服务可以根据需要选择开发语言。



微服务治理框架：
1、传统的Spring cloud、dubbo等
为了解决以上的一些分布式系统治理的问题，一些微服务的框架应运而生，比如Spring cloud、dubbo等。这些框架提供了微服务治理过程中所需要的功能和工具，比如服务的注册和发现、熔断限流、API网关、路由和负载均衡以及配置管理等。

2、Service Mesh服务网格方式和Istio
Istio提供一种简单的方式来建立已部署的服务的网络，具备负载均衡，服务到服务认证，监控等等功能，而不需要改动任何服务代码。
Istio的目标是以后脱离Kubernetes成为一个中立的service mesh框架，支持更多的微服务环境。使用istio可以满足微服务治理过程中大部分的功能需求，比如上文提到的服务注册和发现、智能路由、负载均衡、权限管理、限流断路、灰度规则等，还可以收集日志、监控和分布式追踪的信息。在这个体系架构下，开发人员可以最大限度的关注业务逻辑，而运维人员可以灵活的调整服务的部署和调用策略。

FQDN：(Fully Qualified Domain Name)全限定域名：同时带有主机名和域名的名称。（通过符号“.”），主机名加上全路径
例如：主机名是bigserver,域名是mycompany.com,那么FQDN就是bigserver.mycompany.com。 [1] 
全限定域名可以从逻辑上准确地表示出主机在什么地方，也可以说全域名是主机名的一种完全表示形式。
从全限定域名中包含的信息可以看出主机在域名树中的位置。DNS解析流程：首先查找本机HOSTS表，有的直接使用表中定义，没有查找网络连接中设置的DNS 服务器由他来解析。


Service Mesh 服务网格
Sidecar模式 边车模式

参考
https://www.jianshu.com/p/e7140103fb39
https://infoq.cn/article/pattern-service-mesh
https://www.infoq.cn/article/istio-future-service-mesh
http://ifeve.com/istio-overview/





官网
https://istio.io/
https://istio.io/zh/
https://istio.io/docs/
https://github.com/istio/
https://github.com/istio/community



服务网格中文社区
http://www.servicemesher.com/
https://www.sofastack.tech/sofa-mesh/docs/Home









