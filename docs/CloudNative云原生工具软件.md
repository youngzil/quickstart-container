


https://www.infoq.com/news/2020/01/servicemeshcon-2019/?from=timeline


[Google Cloud Container Registry](https://cloud.google.com/container-registry/) ：存储、管理和保护您的 Docker 容器映像。  
[阿里云开放云原生应用中心 - Cloud Native App Hub](https://developer.aliyun.com/hub) ：搜索、体验与分享云原生应用  
[Helm Hub](https://hub.helm.sh/) ：Discover & launch great Kubernetes-ready apps  




Mesos是一个分布式系统内核，类似YARN，OMEGA，支持1w＋节点管理，两级调度，插件化。 Mesos收集资源CPU，MEM，NET，GPU等，根据DRF（Domain Resource Fainess）算法吧资源非配给业务业务框架Framework，业务层的调度在Mesos称为Framework，在这里提到的k8s,swarm,marathon,saprk都可与Mesos结合作为Framework，用于容器的调度与大数据的处理。

K8s，swarm也可单独使用，都主要用户容器服务的编排。

K8s是Google的一个开源项目，结合了Google的borg系统的大规模服务管理经验，功能比较全，参数众多，使用灵活，但学习成本相比swarm高多了。

Swarm是Docker官方实现的容器集群管理工具，使用了标准的Docker API。相比K8s，容器编排支持差些，简单易上手。

Zookeeper也是Google派， 分布式开源系统，主要提供一致性服务，k8s,swarm,marathon,saprk,mesos等都可与zookeeper协作，实现服务的高可用性。类似的还有etcd

Marathon可以理解为Mesos的一个通用调度框架，出自于一家初创公司 Mesosphere之手。他结合Mesos可实现容器管理，执行命令行长任务等，提供webui，api等。Mesosphere这家公司在致力利用Mesos打造一个数据中心，即DCOS。


MapReduce是一个编程模型，是hadoop的组成部分，Hadoop包括Yarn和HDFS以及MapReduce，Hadoop是解决了大数据的可靠存储和处理，那么MapReduce就是并发地，分布式地处理大量的数据集的部分。

Spark新一代快速，通用的大数据处理引擎，与Hadoop结合，可替代Hadoop MapReduce。



容器：

mesos+marathon+zookeeper:即所谓的DCOS解决方案，mesos管理集群资源如CPU、内存，marathon为mesos支持的框架的一种，用来调度docker容器。zookeeper作为一个分布式服务框架在很多方案中有使用，在此保障mesos master服务的高可用性。

k8s、swarm、yarn、mesos：swarm由docker官方支持，对docker原生命令和API支持较好，但目前的生态发展最为劣势；yarn用于调度大数据任务（mesos+yarn共同开发一个叫myraid项目），hadoop 2.6.0以后支持DCE（docker container executor） 故也可以调度docker；如果仅调度docker，k8s和mesos各有优劣，选择一个合适的即可。但如果有多种调度框架，mesos支持hadoop、spark等，是最佳方案。


大数据：

map-reduce：一个计算模型，hadoop使用。简单、暴力好用、笨重。

spark：二代计算模型，是让Map/Reduce模型更通用，Map和Reduce之间的界限更模糊。但spark目前和hadoop仍是共存，且不一定会取代haoop。




通过自研引入Prometheus、Skywalking等开源监控工具适配改造, 提供云原生（K8S、容器、技术栈）支持，以实现微服务架构下节点、pod、容器、应用服务及调用关系的动态发现、监控点的自动配置及智能告警，提升了云原生监控管理能力及海量数据处理性能。

主要改造如下：
1) 利用Promtheus的采集及资源动态发现能力，实现BOMC监控点的自动配置及各监控对象的健康度、运行状态、资源使用情况等监控。
2) 利用Skywalking采集容器化应用的Tracing、Metrics等数据持久化到ES数据库中，并完成可用性监控、服务调用追踪、应用性能分析、应用拓扑等重点监控功能的建设。
3）全景监控大盘改造，通过数据自动化收集、数据可视化展示，能够及时、全面地掌控各个实例及依赖的基础资源、中间件及应用服务调用的性能情况，辅助定位、分析应用性能瓶颈。
4）通过对智能告警模块的改造，适配统一时序数据库进行数据训练，实现动态的告警收敛，故障根因分析，一方面解决微服务监控的告警风暴问题，另一方面辅助故障快速定位。





Antrea

基于Open vSwitch的Kubernetes网络

Kubernetes networking based on Open vSwitch

https://antrea.io/  
https://github.com/vmware-tanzu/antrea/

Antrea是一个Kubernetes网络解决方案，旨在成为Kubernetes的本机。它使用Open vSwitch作为网络数据平面，在Layer3 / 4上运行，为Kubernetes集群提供网络和安全服务 。

Open vSwitch是一种广泛采用的高性能可编程虚拟交换机。Antrea利用它来实现Pod网络和安全功能。例如，Open vSwitch使Antrea能够以非常有效的方式实施Kubernetes网络策略。



etcdhosts 是一个 CoreDNS 插件，通过将 hosts 配置存储在 etcd 中实现分布式一致性查询  
https://github.com/Gozap/gdns  




