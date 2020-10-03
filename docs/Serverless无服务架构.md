1、Serverless最佳实践原则
2、BaaS vs. FaaS：解释两种无服务器架构










---------------------------------------------------------------------------------------------------------------------
Serverless最佳实践原则：
1、一个 function 应该只做一件事
2、不让 function 调用其他 function：调用其他 function 的 function 是一种反模式。
3、尽可能少在 function 中使用额外的库：代码越多，冷启动的速度就越慢，引入多余的代码，多余的代码不仅不会被运行，还会带来安全风险。
4、避免使用基于连接的服务：连接需要时间，而且你试想一下，当一个 function 扩展到多个，每个 function 环境都需要一个连接，这样就给 function 冷启动引入了瓶颈和 I/O 等待，但其实这些是没有必要的。如果你一定要使用 RDBMS，可以在中间放置一个连接池服务，如果是某种可以自动伸缩的容器，那就更好了。
5、一个路由对应一个 function
6、学习使用消息和队列
7、数据流，而不是数据湖
8、了解应用程序是如何伸缩的


Serverless并不意味着没有服务器，而是你不需要操心服务器相关的事情。



Knative 是谷歌牵头发起的 Serverless 项目，希望通过提供一套简单易用的 Serverless 开源方案，把 Serverless 标准化。
项目地址：https://github.com/knative


https://mp.weixin.qq.com/s/xddco68iY5LMv6ksEXLQkg


---------------------------------------------------------------------------------------------------------------------

BaaS vs. FaaS：解释两种无服务器架构
Backend as a Service
Function as a Service

Serverless=FaaS+BaaS，

因此，无服务器架构是可以使用第三方服务或在无状态计算容器中使用并运行自定义代码（分别称为BaaS（后端即服务）和FaaS（功能即服务））的执行设计。 



BaaS也称为后端即服务或mBaaS —移动后端即服务实际上是一种云模型，这是移动应用程序和网站开发的最新方法。
FaaS（函数/功能即服务）


IaaS, PaaS, SaaS, BaaS, Faas

https://blog.csdn.net/xianghongai/article/details/79572220
https://brainhub.eu/blog/cloud-architecture-saas-faas-xaas/



参考
https://zhuanlan.zhihu.com/p/31386919

https://medium.com/@george_51059/baas-vs-faas-2892a4a1c933
https://blog.back4app.com/backend-as-a-service-baas/
https://www.hitechnectar.com/blogs/baas-vs-faas-explaining-the-two-serverless-architectures/
https://www.oreilly.com/library/view/what-is-serverless/9781491984178/ch01.html


https://blog.csdn.net/chenhaifeng2016/article/details/71425173
https://blog.csdn.net/woaixiaoyu520/article/details/101027400
http://www.broadview.com.cn/article/792
https://juejin.im/post/5d523e39f265da03d42f997a
https://cloud.tencent.com/developer/article/1145319
https://www.bmc.com/blogs/serverless-faas/
https://www.jianshu.com/p/67a0b6f72a7c



Martin Fowler在2016.6.17号发表了一篇博客： 《Serverless Architectures》,引起业界广泛关注：
https://martinfowler.com/articles/serverless.html


---------------------------------------------------------------------------------------------------------------------













---------------------------------------------------------------------------------------------------------------------
















---------------------------------------------------------------------------------------------------------------------







