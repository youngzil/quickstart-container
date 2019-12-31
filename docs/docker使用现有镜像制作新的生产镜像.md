```
docker使用现有镜像制作新的生产镜像

首先拿到tar包
docker load -i xx.tar
docker images 查看旧名字
docker tag 旧名字  新名字    #就是替换下名字
docker push 新名字
这样就可以在其他机子上docker pull 新名字了

没有原始镜像有没有内网操作方式

可以先用能连上docker 官方镜像的机子下载镜像
docker pull xxx镜像
然后保存为文件
docker save -o xxx.tar xxx镜像
在内网机子上导入
docker load -i xxx.tar
建议内网架设docker私服


docker save -o xx.tar  images_name


dokcer修改现有镜像jdk（从1.7到1.8）
mkdir dockerimage
在dockerimage 将jdk1.8的tar包解压在同名目录
mv  jdk1.8  jdk
vi Dockerfile

FROM 初始镜像名
ADD jdk /app/jdk

docker build -t 新名字 .
[.]表示当前目录
如果新建的dockerfile文件不是[Dockerfile]名字 在[.]后面加上 
-f  dockerfile名字

dockerfile文件想要执行shell 命令
RUN shell命令


docker 修改正在运行的镜像

docker run -it  镜像名 /bin/bash  
记录下镜像名
然后按照需求修改
exit
此时还可以 docker ps -a  查看镜像名ID

docker commit -m "Added json gem" -a "Docker Newbee"  镜像ID  新镜像名
其中，-m 来指定提交的说明信息，跟我们使用的版本控制工具一样；-a 可以指定更新的用户信息；之后是用来创建镜像的容器的 ID；最后指定目标镜像的仓库名和 tag 信息。创建成功后会返回这个镜像的 ID 信息。

docker rm -f containerid  强行删除镜像


docker search  centos
docker pull 下来

