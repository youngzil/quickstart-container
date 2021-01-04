Service Mesh 在百度网盘数万后端的落地实践
https://www.infoq.cn/article/vPA6aYpKizUQIIJzfLiH


Service Mesh（服务网格） 介绍

https://zhuanlan.zhihu.com/p/28794062
https://blog.csdn.net/u012211419/article/details/78963276
http://www.infoq.com/cn/news/2017/12/why-service-mesh


目前两款流行的 service mesh 开源软件 Istio 和 Linkerd 都可以直接在 kubernetes 中集成，其中 Linkerd 已经成为 CNCF 成员。



Istio：
http://istio.doczh.cn/
https://preliminary.istio.io/zh/docs/concepts/what-is-istio/
https://istio.io/docs/





当前，业界 Service Mesh 主要有以下相关产品：
1、Buoyant 的 linkerd，基于 Twitter 的 Fingle，长期的实际产线运行经验及验证，支持 Kubernetes，DC/OS 容器管理平台，CNCF 官方支持的项目之一。
2、Lyft 的 Envoy，7层代理及通信总线，支持7层 HTTP 路由、TLS、gRPC、服务发现以及健康监测等，也是 CNCF 官方支持项目之一。
3、IBM、Google、Lyft 支持的 Istio，一个开源的微服务连接、管理平台以及给微服务提供安全管理，支持 Kubernetes、Mesos 等容器管理工具，其底层依赖于 Envoy。



https://linkerd.io/
https://github.com/linkerd/linkerd
linkerd官方文档中文版：https://legacy.gitbook.com/book/doczhcn/linkerd/details



https://istio.io/docs/
https://github.com/istio/istio
Istio官方文档中文版：http://istio.doczh.cn/


https://www.envoyproxy.io/
https://github.com/envoyproxy/envoy



https://blog.csdn.net/GitChat/article/details/78872404
https://www.jianshu.com/p/e23e3e74538e
https://blog.csdn.net/wangqingjiewa/article/details/78677912
http://blog.daocloud.io/cncf-3/






