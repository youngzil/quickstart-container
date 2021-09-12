- [CloudNative云原生](#CloudNative云原生)
- [什么是云原生](#什么是云原生)
  - [CNCF的定义](#CNCF的定义)
  - [Pivotal公司的定义](#Pivotal公司的定义)


---------------------------------------------------------------------------------------------------------------------
## CloudNative云原生

[CNCF官网](https://www.cncf.io/)

Cloud Native Interactive Landscape（云原生交互场景）  
[CNCF Cloud Native Interactive Landscape](https://landscape.cncf.io/)  
[Cloud Native Interactive Landscape Github地址](https://github.com/cncf/landscape)

The Cloud Native Computing Foundation (CNCF) hosts critical components of the global technology infrastructure. CNCF brings together the world’s top developers, end users, and vendors and runs the largest open source developer conferences. CNCF is part of the nonprofit Linux Foundation.

云原生计算基金会 (CNCF) 承载着全球技术基础设施的关键组件。 CNCF 汇集了世界顶级的开发者、最终用户和供应商，并举办了最大的开源开发者大会。 CNCF 是非营利性 Linux 基金会的一部分。

开源组织Cloud Native Computing Foundation（CNCF），CNCF也将隶属于Linux基金会管理

CNCF 计划为支持分布式、可扩展的应用需要的组件和组装方式提出一种规范，期望定义能够支持云原生应用和容器的整个基础设施堆栈。为了真正理解 CNCF 的既定目标，我们还需要了解它旗下的项目内容。
正如基金会目标中所描述的任务、角色以及价值观，基金会自创立以来名下已经管理了多个云端原生技术项目，包括：
- Kubernetes ：集群中管理跨多台主机容器化应用的开源系统；
- Prometheus ：专注于时间序列数据，为客户端依赖及第三方数据消费提供广泛集成支持的开源监控解决方案；
- OpenTracing：与厂商无关的分布式追踪开源标准；
- Fluentd：创建统一日志层的开源数据收集器。
- Linkerd：为微服务提供可靠性支持、自动化负载均衡、服务发现和运行时可恢复性的开源“服务网格”项目；
- gRPC：现代化高性能开源远程调用框架；
- CoreDNS：快速灵活的构建 DNS 服务器的方案；
- containerd：将容器运行时及其管理功能从 Docker Daemon 剥离的镜像管理和容器执行技术；
- rkt：帮助开发者打包应用和依赖包，简化搭环境等部署工作，提高容器安全性和易用性的容器引擎。



[云原生社区（中国）](https://cloudnative.to/)
[宋净超（Jimmy Song）的博客](https://jimmysong.io/blog/)
[云原生学习笔记](https://skyao.io/learning-cloudnative/docs.html)
[Awesome Cloud Native](https://jimmysong.io/awesome-cloud-native/)


---------------------------------------------------------------------------------------------------------------------

## 什么是云原生

云原生之所以解释不清楚，是因为云原生没有确切的定义，云原生一直在发展变化之中，解释权不归某个人或组织所有。

技术的变革，一定是思想先行，云原生是一种构建和运行应用程序的方法，是一套技术体系和方法论。

云原生（CloudNative）是一个组合词，Cloud+Native。  
Cloud表示应用程序位于云中，而不是传统的数据中心；  
Native表示应用程序从设计之初即考虑到云的环境，原生为云而设计，在云上以最佳姿势运行，充分利用和发挥云平台的弹性+分布式优势。  

云原生从字面意思上来看可以分成云和原生两个部分。

云是和本地相对的，传统的应用必须跑在本地服务器上，现在流行的应用都跑在云端，云包含了IaaS,、PaaS和SaaS。

原生就是土生土长的意思，我们在开始设计应用的时候就考虑到应用将来是运行云环境里面的，要充分利用云资源的优点，比如️云服务的弹性和分布式优势。


可见，不同的人和组织对云原生有不同的定义，相同的人和组织在不同时间点对云原生也有不同的定义，真是乱的一匹，搞得鄙人非常晕菜，我的应对很简单，选一个我最容易记住和理解的定义：DevOps+持续交付+微服务+容器。

总而言之，符合云原生架构的应用程序应该是：采用开源堆栈（K8S+Docker）进行容器化，基于微服务架构提高灵活性和可维护性，借助敏捷方法、DevOps支持持续迭代和运维自动化，利用云平台设施实现弹性伸缩、动态调度、优化资源利用率。


参考  
[什么是云原生？这回终于有人讲明白了](https://juejin.cn/post/6844904197859590151)  
[云原生（Cloud Native）的定义](https://jimmysong.io/kubernetes-handbook/cloud-native/cloud-native-definition.html)  
[云原生关乎文化，而不是容器](https://cloudnative.to/blog/cloud-native-culture-not-container/)  
[云原生架构定义](https://jimmysong.io/migrating-to-cloud-native-application-architectures/chapter1/defining-cloud-native-architectures.html)
[云原生应用之路——从 Kubernetes 到云原生](https://jimmysong.io/kubernetes-handbook/cloud-native/from-kubernetes-to-cloud-native.html)  
[什么是云原生](https://www.jianshu.com/p/a37baa7c3eff)




### CNCF的定义

2015年云原生计算基金会（CNCF）成立，CNCF掺和进来后，最初把云原生定义为包括：应用容器化封装+自动化管理+面向微服务架构；  
到了2018年，CNCF又更新了云原生的定义，把服务网格(Service Mesh)和声明式API给加了进来。


目前CNCF的定义就下面第一句话（中英对照）：

Cloud native technologies empower organizations to build and run scalable applications in modern, dynamic environments such as public, private, and hybrid clouds. Containers, service meshes, microservices, immutable infrastructure, and declarative APIs exemplify this approach.
云原生技术有利于各组织在公有云、私有云和混合云等新型动态环境中，构建和运行可弹性扩展的应用。云原生的代表技术包括容器、服务网格、微服务、不可变基础设施和声明式API。

These techniques enable loosely coupled systems that are resilient, manageable, and observable. Combined with robust automation, they allow engineers to make high-impact changes frequently and predictably with minimal toil.
这些技术能够构建容错性好、易于管理和便于观察的松耦合系统。结合可靠的自动化手段，云原生技术使工程师能够轻松地对系统作出频繁和可预测的重大变更。

The Cloud Native Computing Foundation seeks to drive adoption of this paradigm by fostering and sustaining an ecosystem of open source, vendor-neutral projects. We democratize state-of-the-art patterns to make these innovations accessible for everyone.
云原生计算基金会（CNCF）致力于培育和维护一个厂商中立的开源生态系统，来推广云原生技术。我们通过将最前沿的模式民主化，让这些创新为大众所用。


参考  
[CNCF Cloud Native Definition v1.0](https://github.com/cncf/toc/blob/main/DEFINITION.md#%E4%B8%AD%E6%96%87%E7%89%88%E6%9C%AC)





### Pivotal公司的定义

12因素、6特质、4个要点

Pivotal公司的Matt Stine于2013年首次提出云原生（CloudNative）的概念；  
2015年，云原生刚推广时，Matt Stine在《迁移到云原生架构》一书中定义了符合云原生架构的几个特征：12因素、微服务、自敏捷架构、基于API协作、扛脆弱性；  
到了2017年，Matt Stine在接受InfoQ采访时又改了口风，将云原生架构归纳为模块化、可观察、可部署、可测试、可替换、可处理6特质；  
而Pivotal最新官网对云原生概括为4个要点：DevOps+持续交付+微服务+容器。

所以你也可以简单地把云原生理解为：云原生 = 微服务 + DevOps + 持续交付 + 容器化






---------------------------------------------------------------------------------------------------------------------


https://www.docker.com/
https://hub.docker.com/
https://github.com/docker
Docker vs. CoreOS Rkt：https://www.upguard.com/articles/docker-vs-coreos


https://kubernetes.io/
https://github.com/kubernetes/kubernetes
中文社区：https://www.kubernetes.org.cn/
kubernetes介绍：https://yeasy.gitbooks.io/docker_practice/content/kubernetes/


https://grpc.io/
https://github.com/grpc/grpc
https://github.com/grpc/grpc-java


https://prometheus.io/
https://github.com/prometheus/prometheus


http://opentracing.io/
https://github.com/opentracing
中文文档
https://wu-sheng.gitbooks.io/opentracing-io/content/pages/quick-start.html
https://github.com/opentracing-contrib/opentracing-specification-zh/blob/master/specification.md


https://www.fluentd.org/
https://github.com/fluent/fluentd


https://coredns.io/
https://github.com/coredns/coredns


https://containerd.io/
https://github.com/containerd/containerd


https://coreos.com/rkt/docs/latest/
https://github.com/rkt/rkt








MOSN is a cloud native proxy for edge or service mesh.   
https://mosn.io  
https://github.com/alipay/sofa-mosn


---------------------------------------------------------------------------------------------------------------------






