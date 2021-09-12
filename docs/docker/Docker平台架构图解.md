





## Docker平台架构图解

Docker Desktop 是一个 GUI 工具，用于管理各种 Docker 组件和功能，包括容器、图像、卷（附加到容器的存储）、本地 Kubernetes、容器内的开发环境等。

Docker 是一个容器平台，其用于构建,保护和管理从开发到生产在内部和云端的应用程序。

总体来说，Docker 平台有很多组件，除了 Docker Desktop 外，还包括 Docker 镜像，Docker 容器，Docker 守护进程，Docker 客户端，Docker 注册表和 Docker Hub。

他们的之前互相联系，形成如今的 Docker 平台：
- Docker 镜像定义了容器的内容。
- Docker 容器是可运行的镜像实例。
- Docker 守护进程是一个后台应用程序，用于管理和运行 Docker 镜像和容器。
- Docker 客户端是一个命令行实用程序，它调用 Docker 守护进程的 API。
- Docker 注册表包含镜像，
- Docker Hub 是一个广泛使用的公共注册表。



而从其架构和运行流程来看，Docker 是一个 C/S 模式的架构，后端是一个松耦合架构，众多模块各司其职。




参考  
[曾两次被断言死亡，容器领导者 Docker 开启新的商业化之路](https://www.infoq.cn/article/8sDYJS1Z17udZA8VrUlO)






