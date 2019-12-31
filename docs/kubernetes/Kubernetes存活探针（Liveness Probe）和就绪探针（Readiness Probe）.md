```aidl

Kubernetes提供了两种探针来检查容器的状态，Liveness 和 Readiness，根据官方文档，Liveness探针是为了查看容器是否正在运行，翻译为存活探针，Readiness探针是为了查看容器是否准备好接受HTTP请求，翻译为就绪探针。

在Kubernets中，pods是Kubernetes创建及管理的最小的可部署的计算单元，一个pod由一个或者多个容器（Docker，rocket等等）组成，这些容器共享内存，网络以及运行容器的方式。


参考
https://jimmysong.io/kubernetes-handbook/guide/configure-liveness-readiness-probes.html
https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/
https://blog.51cto.com/3842834/2317986
https://juejin.im/entry/5bf65a48f265da616916e020
```

