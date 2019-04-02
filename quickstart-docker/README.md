Docker 使用客户端-服务器 (C/S) 架构模式，使用远程API来管理和创建Docker容器。
Docker Client是Docker的客户端，负责发送命令。Docker服务器接受并处理命令。

Docker 采用了 C/S架构，包括客户端和服务端。 
Docker daemon 作为服务端接受来自客户的请求，并处理这些请求（创建、运行、分发容器）。 
客户端和服务端既可以运行在一个机器上，也可通过 socket 或者 RESTful API 来进行通信。

Docker daemon 一般在宿主主机后台运行，等待接收来自客户端的消息。
Docker 客户端则为用户提供一系列可执行命令，用户用这些命令实现跟 Docker daemon 交互。


Docker Client官网类库
https://docs.docker.com/develop/sdk/#unofficial-libraries


java类库
https://github.com/spotify/docker-client
https://github.com/docker-java/docker-java
https://github.com/amihaiemil/docker-java-api


Docker maven插件
https://github.com/spotify/dockerfile-maven
https://github.com/spotify/docker-maven-plugin





Docker on Mac上的Remote API 远程控制
https://my.oschina.net/u/2306127/blog/777695

使用 socat
安装socat：
brew install socat
启动socat:
$ socat -d TCP-LISTEN:2375,range=127.0.0.1/32,reuseaddr,fork UNIX:/var/run/docker.sock
开放全部端口：

$ socat -d TCP-LISTEN:2375,reuseaddr,fork UNIX:/var/run/docker.sock
测试一下：
$ curl localhost:2375/version
{"Version":"1.11.2","ApiVersion":"1.23","GitCommit":"56888bf","GoVersion":"go1.5.4",
"Os":"linux","Arch":"amd64","KernelVersion":"4.4.12-moby",
"BuildTime":"2016-06-06T23:57:32.306881674+00:00"}














