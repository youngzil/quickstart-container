[Docker Compose Github](https://github.com/docker/compose)   
[Docker Compose官方文档](https://docs.docker.com/compose/overview/)   
[Docker Compose安装](https://docs.docker.com/compose/install/)   


[Docker Compose的概念和介绍](#Docker-Compose的概念和介绍)
[Docker compose常用命令](#Docker-compose常用命令)
[Docker compose的配置文件yml的配置项](#Docker-compose的配置文件yml的配置项)


## Docker Compose的概念和介绍

安装、查看版本、运行

Docker Compose是一个用来定义和运行复杂应用的Docker工具。一个使用Docker容器的应用，通常由多个容器组成。使用Docker Compose不再需要使用shell脚本来启动容器。  

Compose 通过一个配置文件来管理多个Docker容器，在配置文件中，所有的容器通过services来定义，然后使用docker-compose脚本来启动，停止和重启应用，和应用中的服务以及所有依赖服务的容器，非常适合组合使用多个容器进行开发的场景。


Linux下安装  

1. Run this command to download the current stable release of Docker Compose:
```
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

```

2. Apply executable permissions to the binary:
```
sudo chmod +x /usr/local/bin/docker-compose
```

3. Test the installation.
```
docker-compose --version
```

安装docker-compose
两种最新的docker安装方式
1.从github上下载docker-compose二进制文件安装
下载最新版的docker-compose文件 
$ sudo curl -L https://github.com/docker/compose/releases/download/1.16.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
添加可执行权限 
$ sudo chmod +x /usr/local/bin/docker-compose
测试安装结果 
$ docker-compose --version 
docker-compose version 1.16.1, build 1719ceb

2.pip安装
$ sudo pip install docker-compose


查看
docker-compose version


---------------------------------------------------------------------------------------------------------------------
## Docker compose的配置文件yml的配置项

```
version: 版本
services: 服务
build 可以指定包含构建上下文的路径、指定的Dockerfile文件以及args参数值：
image: 使用哪个image构建容器
container_name: 自定义容器名称
expose：暴露端口，但不映射到宿主机，只被连接的服务访问。 仅可以指定内部端口为参数
ports: 暴露的端口
volumes: 挂载的目录
command: 启动容器是执行的指令
depends_on: 依赖的服务
links：链接到另一个服务中的容器。 请指定服务名称和链接别名（SERVICE：ALIAS），或者仅指定服务名称。
external_links：链接到docker-compose.yml 外部的容器，甚至并非 Compose 管理的容器。参数格式跟 links 类似。
restart：no是默认的重启策略
environment：添加环境变量。 你可以使用数组或字典两种形式。
pid：将PID模式设置为主机PID模式。 这就打开了容器与主机操作系统之间的共享PID地址空间。
dns：配置 DNS 服务器。可以是一个值，也可以是一个列表。
```


docker-compose的配置文件yml的配置项  
https://www.jianshu.com/p/2217cfed29d7  
https://docs.docker.com/compose/compose-file/  
https://blog.csdn.net/qq_36148847/article/details/79427878  
https://www.jianshu.com/p/2217cfed29d7  
https://www.cnblogs.com/sparkdev/p/9803554.html  
https://deepzz.com/post/docker-compose-file.html  
https://yeasy.gitbooks.io/docker_practice/compose/compose_file.html  
https://beginor.github.io/2017/06/08/use-compose-instead-of-run.html  




---------------------------------------------------------------------------------------------------------------------
## Docker compose常用命令

使用 docker-compose 替代 docker run

在部署时， 通常将 docker-compose.yml 文件放到一个目录， 表示一个应用， docker 会为这个应用创建一个独立的网络， 便于和其它应用进行隔离。

要运行这个程序， 只要在这个目录下执行 docker-compose up -d 命令， 就会按照上面的配置启动两个容器的实例:

要停止上面的容器， 只需要输入 docker-compose down 命令：


$ docker-compose up
# 若是要后台运行： $ docker-compose up -d
# 若不使用默认的docker-compose.yml 文件名：
$ docker-compose -f server.yml up -d


docker-compose常用命令
完整的命令列表如下：
```
build 构建或重建服务
help 命令帮助
kill 杀掉容器
logs 显示容器的输出内容
port 打印绑定的开放端口
ps 显示容器
pull 拉取服务镜像
restart 重启服务
rm 删除停止的容器
run 运行一个一次性命令
scale 设置服务的容器数目
start 开启服务
stop 停止服务
up 创建并启动容器
```


3.常用命令

docker compose up -d nginx                     构建建启动nignx容器

docker compose exec nginx bash            登录到nginx容器中

docker-compose down                              删除所有nginx容器,镜像

docker-compose ps                                   显示所有容器

docker-compose restart nginx                   重新启动nginx容器

docker-compose run --no-deps --rm php-fpm php -v  在php-fpm中不启动关联容器，并容器执行php -v 执行完成后删除容器

docker-compose build nginx                     构建镜像 。        

docker-compose build --no-cache nginx   不带缓存的构建。

docker compose logs  nginx                     查看nginx的日志 

docker compose logs -f nginx                   查看nginx的实时日志

docker-compose config  -q                        验证（docker-compose.yml）文件配置，当配置正确时，不输出任何内容，当文件配置错误，输出错误信息。 

docker-compose events --json nginx       以json的形式输出nginx的docker日志

docker-compose pause nginx                 暂停nignx容器

docker-compose unpause nginx             恢复ningx容器

docker-compose rm nginx                       删除容器（删除前必须关闭容器）

docker-compose stop nginx                    停止nignx容器

docker-compose start nginx                    启动nignx容器


参考  
https://beginor.github.io/2017/06/08/use-compose-instead-of-run.html

[Docker-compose常用命令](https://www.cnblogs.com/moxiaoan/p/9299404.html)  


https://blog.csdn.net/pushiqiang/article/details/78682323  
https://oomusou.io/docker/dockerfile-dockercompose/  
https://linuxconfig.org/how-to-launch-containers-with-docker-compose  


