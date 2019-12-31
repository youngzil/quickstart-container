项目地址
https://github.com/youngzil/quickstart-container



http://gcr.io/
http://qury.io/
https://developer.aliyun.com/hub#/?_k=cc7hrg
https://hub.helm.sh/



个人网站
https://jimmysong.io
https://skyao.io/


Kubernetes中文网
http://www.k8smeetup.com/

云计算社区
https://www.yunforum.net/


提供PPT分享服务的平台
https://myslide.cn/
https://www.itslide.com/


云原生相关的软件、工具和教程列表汇总
https://github.com/rootsongjc/awesome-cloud-native

https://github.com/alipay/sofa-mosn

https://github.com/findsec-cn/k201

每周文章
https://kubeweekly.io/2019/11/16/kubeweekly-193/

https://github.com/vmware-tanzu/antrea/

https://github.com/Gozap/gdns






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







