
Docker命令包括：
- [Docker本身](#Docker本身)
- [Docker镜像](#Docker镜像)
- [Docker容器](#Docker容器)
- [Docker容器启动日志查看](#Docker容器启动日志查看)
- [Dockerfile结构和制作](#Dockerfile结构和制作)

Docker原理



https://github.com/zhangguanzhang/docker-need-to-know/blob/master/SUMMARY.md


[Docker run 命令参数及使用](https://www.jianshu.com/p/ea4a00c6c21c)  


gitlab 容器的参数示例
您可以使用以下的 docker run 命令可以创建出一个简单的 gitlab 容器：

docker run \
-d \
-p 20180:80 \
-p 20122:22 \
--restart always \
-v /data/gitlab/config:/etc/gitlab \
-v /data/var/log/gitlab:/var/log/gitlab \
-v /data/gitlab/data:/var/opt/gitlab \
--name gitlab \
gitlab/gitlab-ce:8.16.7-ce.0






Image标识：Image ID 或者 [注册仓库/]repository:tag
Container标识：Container ID 或者 Container names
外部访问容器（-p 和 -P 标记）和容器之间互联（web与外部互联，db和web互联，db不对外映射端口，web可以访问db）
数据库和容器之间的挂载和映射
 
 
 

## Docker本身
docker version
docker info
docker login : 登陆到一个Docker镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub
docker logout : 登出一个Docker镜像仓库，如果未指定镜像仓库地址，默认为官方仓库 Docker Hub

docker login -u 用户名 -p 密码 SERVER地址



## Docker镜像
docker镜像：显示和搜索，构建和删除，拉取和提交、导入和导出
docker image ls (docker images)  列出本地镜像，docker images [OPTIONS] [REPOSITORY[:TAG]]
docker search image名字
docker history runoob/ubuntu:v3  查看指定镜像的创建历史。


构建、修改内容和tag、提交、导入导出、删除image
docker build -t mynginx:0.1 .(Dockerfile 所在的路径,当前目录使用.)
docker build -t="ouruser/sinatra:v2" .(Dockerfile 所在的路径,当前目录使用.)
docker build -t rocketmqinc/rocketmq:4.4.0 --build-arg version=4.4.0 .    --build-arg传递参数到image

docker rmi training/sinatra  移除本地的镜像(有container引用该image，必须先删除container)


docker commit -m "Added json gem" -a "Docker Newbee" 0b2616b0e5a8 ouruser/sinatra:v2   从容器创建一个新的镜像。
docker tag 5db5f8471261 ouruser/sinatra:devel  修改镜像的标签
docker tag ubuntu:15.10 runoob/ubuntu:v3


拉取和提交、导入和导出
docker pull Image标识
docker pull -a java   拉取所有镜像
docker pull ubuntu:12.04
docker pull registry.hub.docker.com/ubuntu:12.04
docker pull dl.dockerpool.com:5000/ubuntu:12.04
该命令实际上相当于 $ sudo docker pull registry.hub.docker.com/ubuntu:12.04 命令，即从注册服务器 registry.hub.docker.com 中的 ubuntu 仓库来下载标记为 12.04 的镜像。

docker并没有提供一个直接的命令可以查询远程仓库的标签信息，但是提供的一个网页进行查询：
查询URL为：  https://registry.hub.docker.com/v1/repositories/【镜像名】/tags
比如 centos 的所有镜像标签查询url为：
https://registry.hub.docker.com/v1/repositories/centos/tags


docker push ouruser/sinatra  把自己创建的镜像上传到仓库中来共享，将本地的镜像上传到镜像仓库,要先登陆到镜像仓库
docker save -o ubuntu_14.04.tar ubuntu:14.04  导出镜像到本地文件
docker load --input ubuntu_14.04.tar (或docker load < ubuntu_14.04.tar)  从导出的本地文件中再导入到本地镜像库,这将导入镜像以及其相关的元数据信息（包括标签等）


docker save -o ubuntu_14.04.tar ubuntu:14.04
docker load < ubuntu_14.04.tar


docker import  my_ubuntu_v3.tar runoob/ubuntu:v4  


docker save 导出image的
docker export 导出container的

docker load 和docker import 都是导入本地image的，一个是从save的image导入，一个是从export的container导入

总结一下docker save和docker export的区别：
docker save保存的是镜像（image），docker export保存的是容器（container）；
docker load用来载入镜像包，docker import用来载入容器包，但两者都会恢复为镜像；
docker load不能对载入的镜像重命名，而docker import可以为镜像指定新名称。

docker 容器导入导出有两种方法：需要注意两种方法不可混用。

一种是使用 save 和 load 命令
docker save ubuntu:load>/root/ubuntu.tar
docker load<ubuntu.tar

一种是使用 export 和 import 命令
docker export 98ca36> ubuntu.tar
cat ubuntu.tar | sudo docker import - ubuntu:import


把tar文件镜像导入本地镜像仓库  
docker load -i msptest.tar  
  
运行  
docker run -it registry.yw.zj.test.com/x86ddpt-ywjx/rwdd_account:20181019111707993112826 bash   
  
yangzl:docker yangzl$ vi Dockfile  
yangzl:docker yangzl$ more Dockfile  
FROM registry.yw.zj.test.com/x86ddpt-ywjx/rwdd_account:20181019111707993112826  
ADD xxx.tgz /app/pkg  
  



## Docker容器

docker容器：显示部分和全部，显示历史和区别
docker container ls 只会显示运行的容器
docker container ps
docker ps -a 显示停止的
docker ps -n 6   相当于docker ps -a 并且只显示前6行
docker ps -a -q  列出所有创建的容器ID。
docker diff container名字  查看容器区别，检查容器里文件结构的更改。
docker history nginx:v2	  查看容器历史



运行、进入和退出Container
docker run --name webserver -d -p 4000:80 nginx   -d后台运行
docker run --name docker-demo -d -p 8080:80 docker-demo:0.1
docker run -t -i ubuntu:14.04 /bin/bash 以 ubuntu:14.04 镜像启动一个容器，以交互模式运行，并为容器重新分配一个伪输入终端。
docker run -d -P --name web -v /src/webapp:/opt/webapp training/webapp python app.py  加载主机的 /src/webapp 目录到容器的 /opt/webapp 目录
docker run -d -P --name web -v /src/webapp:/opt/webapp:ro training/webapp python app.py  挂载目录只读模式


docker run -d -p 9876:9876 -v `pwd`/data/namesrv/logs:/root/logs -v `pwd`/data/namesrv/store:/root/store --name rmqnamesrv  rocketmqinc/rocketmq:4.4.0 sh mqnamesrv
docker run -d -p 10911:10911 -p 10909:10909 -v `pwd`/data/broker/logs:/root/logs -v `pwd`/data/broker/store:/root/store --name rmqbroker --link rmqnamesrv:namesrv -e "NAMESRV_ADDR=namesrv:9876" rocketmqinc/rocketmq:4.4.0 sh mqbroker
-e 设置环境变量
--link <name or id>:alias  其中，name和id是源容器的name和id，alias是源容器在link下的别名。
#如果在最后一行有CMD，执行docker run的时候最后就不能带执行脚本参数


docker create ：创建一个新的容器但不启动它，用法同 docker run
docker create  --name myrunoob  nginx:latest  


exit 命令或 Ctrl+d 来退出终端
docker attach nostalgic_hypatia   当多个窗口同时使用该命令进入该容器时，所有的窗口都会同步显示。如果有一个窗口阻塞了，那么其他窗口也无法再进行操作。
docker exec -it 775c7c9ee1e1 /bin/bash  
docker exec -it mynginx /bin/sh /root/runoob.sh  在容器mynginx中以交互模式执行容器内/root/runoob.sh脚本
docker exec -it rmqbroker sh ./tools.sh org.apache.rocketmq.example.quickstart.Producer  进入rmqbroker容器以交互模式执行容器内Producer脚本
docker exec -it rmqbroker ./mqadmin clusterList -n 10.21.38.83:9876




启动、停止、restart重启、pause/unpause、删除 container
docker start|stop|restart
docker container start|stop|restart 容器标识（容器ID或容器名字）
docker container rm docker-demo
docker container pause
docker container unpause

docker kill :杀掉一个运行中的容器。-s :向容器发送一个信号
docker kill -s KILL mynginx 


docker rm -f db01 db02  强制删除
docker rm -l db  删除到容器的链接，不是删除容器




## Docker容器启动日志查看
docker logs -f -t --tail 20 zylmysql

想创建mysql容器运行，但是发现出错了  
先通过docker ps -a查到已经被停止的容器的id  
然后通过docker logs id 来查看相应的日志信息  




docker inspect : 获取容器/镜像的元数据。
docker inspect mysql:5.6
docker inspect ab00f544b507


docker top quickstart-tomcat   查看容器中运行的进程信息，支持 ps 命令参数。

查看所有运行容器的进程信息。
for i in  `docker ps |grep Up|awk '{print $1}'`;do echo \ &&docker top $i; done



docker cp :用于容器与主机之间的数据拷贝。
docker cp src dest

将主机/www/runoob目录拷贝到容器96f7f14e99ab的/www目录下。
docker cp /www/runoob 96f7f14e99ab:/www/

将主机/www/runoob目录拷贝到容器96f7f14e99ab中，目录重命名为www。
docker cp /www/runoob 96f7f14e99ab:/www

将容器96f7f14e99ab的/www目录拷贝到主机的/tmp目录中。
docker cp  96f7f14e99ab:/www /tmp/


docker commit :从容器创建一个新的镜像。
docker commit -m "Added json gem" -a "Docker Newbee" 0b2616b0e5a8 ouruser/sinatra:v2   从容器创建一个新的镜像。
docker tag 5db5f8471261 ouruser/sinatra:devel  修改镜像的标签



Docker stop停止/remove删除所有容器
$ docker ps // 查看所有正在运行容器
$ docker stop containerId // containerId 是容器的ID

$ docker ps -a // 查看所有容器
$ docker ps -a -q // 查看所有容器ID

$ docker stop $(docker ps -a -q) //  stop停止所有容器
$ docker  rm $(docker ps -a -q) //   remove删除所有容器




查看详细信息、日志
docker logs Container标识
docker port Container标识 [端口] 来查看当前映射的端口配置，也可以查看到绑定的地址
docker inspect 44fc0f0582d9   查看该容器的详细信息

docker logs -f mynginx
查看容器mynginx从2016年7月1日后的最新10条日志。
docker logs --since="2016-07-01" --tail=10 mynginx

docker port :列出指定的容器的端口映射，或者查找将PRIVATE_PORT NAT到面向公众的端口。





导出和导入容器
docker export 7691a814370e > ubuntu.tar
cat ubuntu-14.04-x86_64-minimal.tar.gz  |docker import - ubuntu:14.04  下载了一个 ubuntu-14.04 的镜像，之后使用以下命令导入

docker export -o mysql-`date +%Y%m%d`.tar a404c6c174a2





## Dockerfile结构和制作

详细查看Dockerfile文件

Dockerfile 由一行行命令语句组成，并且支持以 # 开头的注释行。
一般的，Dockerfile 分为四部分：基础镜像信息、维护者信息、镜像操作指令和容器启动时执行指令。
其中，一开始必须指明所基于的镜像名称，接下来推荐说明维护者信息。
后面则是镜像操作指令，例如 RUN 指令，RUN 指令将对镜像执行跟随的命令。每运行一条 RUN 指令，镜像添加新的一层，并提交。
最后是 CMD 指令，来指定运行容器时的操作命令。
FROM + MAINTAINER + RUN + CMD








下载镜像
sudo docker pull ubuntu:12.04
sudo docker pull registry.hub.docker.com/ubuntu:12.04
sudo docker pull dl.dockerpool.com:5000/ubuntu:12.04
该命令实际上相当于 $ sudo docker pull registry.hub.docker.com/ubuntu:12.04 命令，即从注册服务器 registry.hub.docker.com 中的 ubuntu 仓库来下载标记为 12.04 的镜像。


例如下面的命令指定使用镜像 ubuntu:14.04 来启动一个容器，让其中运行 bash 应用。如果不指定具体的标记，则默认使用 latest 标记信息。
$ sudo docker run -t -i ubuntu:14.04 /bin/bash


显示本地已有的镜像
docker images
sudo docker images ouruser/sinatra


修改镜像
1、先使用下载的镜像启动容器。
$ sudo docker run -t -i training/sinatra /bin/bash

2、在容器中添加 json 和 gem 两个应用。
root@0b2616b0e5a8:/# gem install json

3、当结束后，我们使用 exit 来退出，现在我们的容器已经被我们改变了，使用 docker commit 命令来提交更新后的副本。
sudo docker commit -m "Added json gem" -a "Docker Newbee" 0b2616b0e5a8 ouruser/sinatra:v2
-m 来指定提交的说明信息，跟我们使用的版本控制工具一样
-a 可以指定更新的用户信息；
之后是用来创建镜像的容器的 ID，修改前的镜像的id或者说模板镜像id
最后指定目标镜像的仓库名和 tag 信息。
创建成功后会返回这个镜像的 ID 信息。


利用 Dockerfile 来创建镜像
1、新建一个目录和一个 Dockerfile
$ mkdir sinatra
$ cd sinatra
$ touch Dockerfile

2、Dockerfile 中每一条指令都创建镜像的一层，例如：
# This is a comment
FROM ubuntu:14.04
MAINTAINER Docker Newbee <newbee@docker.com>
RUN apt-get -qq update
RUN apt-get -qqy install ruby ruby-dev
RUN gem install sinatra

Dockerfile 基本的语法是
使用#来注释
FROM 指令告诉 Docker 使用哪个镜像作为基础
接着是维护者的信息
RUN开头的指令会在创建中运行，比如安装一个软件包，在这里使用 apt-get 来安装了一些软件

3、编写完成 Dockerfile 后可以使用 docker build 来生成镜像。
$ sudo docker build -t="ouruser/sinatra:v2" .
其中 -t 标记来添加 tag，指定新的镜像的用户信息。 
“.” 是 Dockerfile 所在的路径（当前目录），也可以替换为一个具体的 Dockerfile 的路径。

*注意一个镜像不能超过 127 层


还可以用 docker tag 命令来修改镜像的标签。
$ sudo docker tag 5db5f8471261 ouruser/sinatra:devel
$ sudo docker images ouruser/sinatra


上传镜像
用户可以通过 docker push 命令，把自己创建的镜像上传到仓库中来共享。例如，用户在 Docker Hub 上完成注册后，可以推送自己的镜像到仓库中。
$ sudo docker push ouruser/sinatra


从本地文件系统导入
要从本地文件系统导入一个镜像，可以使用 openvz（容器虚拟化的先锋技术）的模板来创建： openvz 的模板下载地址为 templates 。
比如，先下载了一个 ubuntu-14.04 的镜像，之后使用以下命令导入：
sudo cat ubuntu-14.04-x86_64-minimal.tar.gz  |docker import - ubuntu:14.04


存出镜像
如果要导出镜像到本地文件，可以使用 docker save 命令。
$ sudo docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
ubuntu              14.04               c4ff7513909d        5 weeks ago         225.4 MB
...
$sudo docker save -o ubuntu_14.04.tar ubuntu:14.04


载入镜像
可以使用 docker load 从导出的本地文件中再导入到本地镜像库，例如
$ sudo docker load --input ubuntu_14.04.tar
或
$ sudo docker load < ubuntu_14.04.tar
这将导入镜像以及其相关的元数据信息（包括标签等）。


移除
如果要移除本地的镜像，可以使用 docker rmi 命令。注意 docker rm 命令是移除容器。
$ sudo docker rmi training/sinatra

注意：在删除镜像之前要先用 docker rm 删掉依赖于这个镜像的所有容器。


新建并启动
所需要的命令主要为 docker run。
例如，下面的命令输出一个 “Hello World”，之后终止容器。
$ sudo docker run ubuntu:14.04 /bin/echo 'Hello world'
Hello world
这跟在本地直接执行 /bin/echo 'hello world' 几乎感觉不出任何区别。

下面的命令则启动一个 bash 终端，允许用户进行交互。
$ sudo docker run -t -i ubuntu:14.04 /bin/bash
root@af8bae53bdd3:/#
其中，-t 选项让Docker分配一个伪终端（pseudo-tty）并绑定到容器的标准输入上， -i 则让容器的标准输入保持打开。


当利用 docker run 来创建容器时，Docker 在后台运行的标准操作包括：
检查本地是否存在指定的镜像，不存在就从公有仓库下载
利用镜像创建并启动一个容器
分配一个文件系统，并在只读的镜像层外面挂载一层可读写层
从宿主主机配置的网桥接口中桥接一个虚拟接口到容器中去
从地址池配置一个 ip 地址给容器
执行用户指定的应用程序
执行完毕后容器被终止


启动已终止容器
可以利用 docker start 命令，直接将一个已经终止的容器启动运行。
容器的核心为所执行的应用程序，所需要的资源都是应用程序运行所必需的。除此之外，并没有其它的资源。可以在伪终端中利用 ps 或 top 来查看进程信息。
root@ba267838cc1b:/# ps
  PID TTY          TIME CMD
    1 ?        00:00:00 bash
   11 ?        00:00:00 ps
可见，容器中仅运行了指定的 bash 应用。这种特点使得 Docker 对资源的利用率极高，是货真价实的轻量级虚拟化。


守护态运行
更多的时候，需要让 Docker 容器在后台以守护态（Daemonized）形式运行。此时，可以通过添加 -d 参数来实现。

例如下面的命令会在后台运行容器。
$ sudo docker run -d ubuntu:14.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"
1e5535038e285177d5214659a068137486f96ee5c2e85a4ac52dc83f2ebe4147

容器启动后会返回一个唯一的 id，也可以通过 docker ps 命令来查看容器信息。
$ sudo docker ps
CONTAINER ID  IMAGE         COMMAND               CREATED        STATUS       PORTS NAMES
1e5535038e28  ubuntu:14.04  /bin/sh -c 'while tr  2 minutes ago  Up 1 minute        insane_babbage

要获取容器的输出信息，可以通过 docker logs 命令。
$ sudo docker logs insane_babbage
hello world
。。。


终止容器
可以使用 docker stop 来终止一个运行中的容器。

此外，当Docker容器中指定的应用终结时，容器也自动终止。 例如对于上一章节中只启动了一个终端的容器，用户通过 exit 命令或 Ctrl+d 来退出终端时，所创建的容器立刻终止。

终止状态的容器可以用 docker ps -a 命令看到。例如
sudo docker ps -a
CONTAINER ID        IMAGE                    COMMAND                CREATED             STATUS                          PORTS               NAMES
ba267838cc1b        ubuntu:14.04             "/bin/bash"            30 minutes ago      Exited (0) About a minute ago                       trusting_newton
98e5efa7d997        training/webapp:latest   "python app.py"        About an hour ago   Exited (0) 34 minutes ago                           backstabbing_pike

处于终止状态的容器，可以通过 docker start 命令来重新启动。
此外，docker restart 命令会将一个运行态的容器终止，然后再重新启动它。


进入容器
在使用 -d 参数时，容器启动后会进入后台。 某些时候需要进入容器进行操作，有很多种方法，包括使用 docker attach 命令或 nsenter 工具等。

attach 命令
docker attach 是Docker自带的命令。下面示例如何使用该命令。

$sudo docker attach nostalgic_hypatia
但是使用 attach 命令有时候并不方便。当多个窗口同时 attach 到同一个容器的时候，所有窗口都会同步显示。当某个窗口因命令阻塞时,其他窗口也无法执行操作了。


导出容器
如果要导出本地某个容器，可以使用 docker export 命令。
$ sudo docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                    PORTS               NAMES
7691a814370e        ubuntu:14.04        "/bin/bash"         36 hours ago        Exited (0) 21 hours ago                       test
$ sudo docker export 7691a814370e > ubuntu.tar
这样将导出容器快照到本地文件。


导入容器快照
可以使用 docker import 从容器快照文件中再导入为镜像，例如
$ cat ubuntu.tar | sudo docker import - test/ubuntu:v1.0
$ sudo docker images

此外，也可以通过指定 URL 或者某个目录来导入，例如
$sudo docker import http://example.com/exampleimage.tgz example/imagerepo

*注：用户既可以使用 docker load 来导入镜像存储文件到本地镜像库，也可以使用 docker import 来导入一个容器快照到本地镜像库。这两者的区别在于容器快照文件将丢弃所有的历史记录和元数据信息（即仅保存容器当时的快照状态），而镜像存储文件将保存完整记录，体积也要大。此外，从容器快照文件导入时可以重新指定标签等元数据信息。


删除
可以使用 docker rm 来删除一个处于终止状态的容器。

例如

$sudo docker rm  trusting_newton
trusting_newton
如果要删除一个运行中的容器，可以添加 -f 参数。Docker 会发送 SIGKILL 信号给容器。



登录
可以通过执行 docker login 命令来输入用户名、密码和邮箱来完成注册和登录。 注册成功后，本地用户目录的 .dockercfg 中将保存用户的认证信息。

基本操作
用户无需登录即可通过 docker search 命令来查找官方仓库中的镜像，并利用 docker pull 命令来将它下载到本地。
例如以 centos 为关键词进行搜索：
$ sudo docker search centos


外部访问容器
容器中可以运行一些网络应用，要让外部也可以访问这些应用，可以通过 -P 或 -p 参数来指定端口映射。

当使用 -P 标记时，Docker 会随机映射一个 49000~49900 的端口到内部容器开放的网络端口。
使用 docker ps 可以看到，本地主机的 49155 被映射到了容器的 5000 端口。此时访问本机的 49155 端口即可访问容器内 web 应用提供的界面。
$ sudo docker run -d -P training/webapp python app.py
$ sudo docker ps -l

同样的，可以通过 docker logs 命令来查看应用的信息。

-p（小写的）则可以指定要映射的端口，并且，在一个指定端口上只可以绑定一个容器。支持的格式有 ip:hostPort:containerPort | ip::containerPort | hostPort:containerPort。

映射所有接口地址
使用 hostPort:containerPort 格式本地的 5000 端口映射到容器的 5000 端口，可以执行
$ sudo docker run -d -p 5000:5000 training/webapp python app.py
此时默认会绑定本地所有接口上的所有地址。

映射到指定地址的指定端口
可以使用 ip:hostPort:containerPort 格式指定映射使用一个特定地址，比如 localhost 地址 127.0.0.1
$ sudo docker run -d -p 127.0.0.1:5000:5000 training/webapp python app.py

映射到指定地址的任意端口
使用 ip::containerPort 绑定 localhost 的任意端口到容器的 5000 端口，本地主机会自动分配一个端口。
$ sudo docker run -d -p 127.0.0.1::5000 training/webapp python app.py

还可以使用 udp 标记来指定 udp 端口
$ sudo docker run -d -p 127.0.0.1:5000:5000/udp training/webapp python app.py


查看映射端口配置
使用 docker port 来查看当前映射的端口配置，也可以查看到绑定的地址
$ docker port nostalgic_morse 5000
127.0.0.1:49155.

注意：
容器有自己的内部网络和 ip 地址（使用 docker inspect 可以获取所有的变量，Docker 还可以有一个可变的网络配置。）
-p 标记可以多次使用来绑定多个端口
例如
$ sudo docker run -d -p 5000:5000  -p 3000:80 training/webapp python app.py


自定义容器命名
连接系统依据容器的名称来执行。因此，首先需要自定义一个好记的容器命名。
虽然当创建容器的时候，系统默认会分配一个名字。自定义命名容器有2个好处：
1、自定义的命名，比较好记，比如一个web应用容器我们可以给它起名叫web
2、当要连接其他容器时候，可以作为一个有用的参考点，比如连接web容器到db容器

使用 --name 标记可以为容器自定义命名。
$ sudo docker run -d -P --name web training/webapp python app.py

使用 docker ps 来验证设定的命名。
$ sudo docker ps -l

也可以使用 docker inspect 来查看容器的名字
$ sudo docker inspect -f "{{ .Name }}" aed84ee21bde
/web

注意：容器的名称是唯一的。如果已经命名了一个叫 web 的容器，当你要再次使用 web 这个名称的时候，需要先用docker rm 来删除之前创建的同名容器。
在执行 docker run 的时候如果添加 --rm 标记，则容器在终止后会立刻删除。注意，--rm 和 -d 参数不能同时使用。



容器互联
容器的连接（linking）系统是除了端口映射外，另一种跟容器中应用交互的方式。
该系统会在源和接收容器之间创建一个隧道，接收容器可以看到源容器指定的信息。

容器互联
使用 --link 参数可以让容器之间安全的进行交互。

下面先创建一个新的数据库容器。
$ sudo docker run -d --name db training/postgres

删除之前创建的 web 容器
$ docker rm -f web

然后创建一个新的 web 容器，并将它连接到 db 容器
$ sudo docker run -d -P --name web --link db:db training/webapp python app.py
此时，db 容器和 web 容器建立互联关系。

--link 参数的格式为 --link name:alias，其中 name 是要链接的容器的名称，alias 是这个连接的别名。

使用 docker ps 来查看容器的连接
$ docker ps

可以看到自定义命名的容器，db 和 web，db 容器的 names 列有 db 也有 web/db。这表示 web 容器链接到 db 容器，web 容器将被允许访问 db 容器的信息。

Docker 在两个互联的容器之间创建了一个安全隧道，而且不用映射它们的端口到宿主主机上。在启动 db 容器的时候并没有使用 -p 和 -P 标记，从而避免了暴露数据库端口到外部网络上。


Docker 通过 2 种方式为容器公开连接信息：
环境变量
更新 /etc/hosts 文件


使用 env 命令来查看 web 容器的环境变量
$ sudo docker run --rm --name web2 --link db:db training/webapp env
. . .
DB_NAME=/web2/db
DB_PORT=tcp://172.17.0.5:5432
DB_PORT_5000_TCP=tcp://172.17.0.5:5432
DB_PORT_5000_TCP_PROTO=tcp
DB_PORT_5000_TCP_PORT=5432
DB_PORT_5000_TCP_ADDR=172.17.0.5
. . .
其中 DB_ 开头的环境变量是供 web 容器连接 db 容器使用，前缀采用大写的连接别名。

除了环境变量，Docker 还添加 host 信息到父容器的 /etc/hosts 的文件。下面是父容器 web 的 hosts 文件
$ sudo docker run -t -i --rm --link db:db training/webapp /bin/bash
root@aed84ee21bde:/opt/webapp# cat /etc/hosts
172.17.0.7  aed84ee21bde
. . .
172.17.0.5  db
这里有 2 个 hosts，第一个是 web 容器，web 容器用 id 作为他的主机名，第二个是 db 容器的 ip 和主机名。 可以在 web 容器中安装 ping 命令来测试跟db容器的连通。

root@aed84ee21bde:/opt/webapp# apt-get install -yqq inetutils-ping
root@aed84ee21bde:/opt/webapp# ping db
PING db (172.17.0.5): 48 data bytes
56 bytes from 172.17.0.5: icmp_seq=0 ttl=64 time=0.267 ms
56 bytes from 172.17.0.5: icmp_seq=1 ttl=64 time=0.250 ms
56 bytes from 172.17.0.5: icmp_seq=2 ttl=64 time=0.256 ms
用 ping 来测试db容器，它会解析成 172.17.0.5。

注意：官方的 ubuntu 镜像默认没有安装 ping，需要自行安装。

用户可以链接多个父容器到子容器，比如可以链接多个 web 到 db 容器上。




