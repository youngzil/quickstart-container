1、一个 function 应该只做一件事
2、不让 function 调用其他 function：调用其他 function 的 function 是一种反模式。
3、尽可能少在 function 中使用额外的库：代码越多，冷启动的速度就越慢，引入多余的代码，多余的代码不仅不会被运行，还会带来安全风险。
4、避免使用基于连接的服务：连接需要时间，而且你试想一下，当一个 function 扩展到多个，每个 function 环境都需要一个连接，这样就给 function 冷启动引入了瓶颈和 I/O 等待，但其实这些是没有必要的。如果你一定要使用 RDBMS，可以在中间放置一个连接池服务，如果是某种可以自动伸缩的容器，那就更好了。
5、一个路由对应一个 function
6、学习使用消息和队列
7、数据流，而不是数据湖
8、了解应用程序是如何伸缩的



Knative 是谷歌牵头发起的 Serverless 项目，希望通过提供一套简单易用的 Serverless 开源方案，把 Serverless 标准化。
项目地址：https://github.com/knative


https://mp.weixin.qq.com/s/xddco68iY5LMv6ksEXLQkg


