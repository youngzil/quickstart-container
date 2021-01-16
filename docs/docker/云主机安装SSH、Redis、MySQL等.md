1. 修改SSH端口
[SSH远程登录配置文件sshd_config详解](https://blog.csdn.net/Field_Yang/article/details/51568861)
在远程连接服务sshd配置文件中进行修改，/etc/ssh/sshd_config文件中修改远程连接端口port，之后重启服务systemctl restart sshd 端口生效。

2. 端口防火墙开启：比如SSH设置的是9000开启，Redis开启6379，MySQL开启3306

下面是阿里云主机上开启防火墙的地址

后续您需要在安全组中放行您添加的端口
添加安全组规则;
https://help.aliyun.com/document_detail/25471.html?spm=5176.11065259.1996646101.searchclickresult.38633b310dsfjg
安全组就像我们服务器内的防火墙，有效保证我们服务器的运行安全。


3. 登录上主机之后
更新相关依赖yum -y update

4. 安装其他组件：比如RZSZ、Docker、Docker安装Redis、Docker安装MySQL
[Linux上安装rz、sz](https://blog.csdn.net/qq_27870421/article/details/94550689)
Docker安装参考 [CentOS 8.0 安装Docker](Docker安装.md#CentOS-8.0-安装Docker)
[Docker安装Redis](/Users/yangzl/git/quickstart-cache/quickstart-redis/docs/deploy-shell/Docker安装Redis.md)
[Docker安装MySQL](/Users/yangzl/git/quickstart-database/docs/MySQL/deploy/Docker安装MySQL.md)
[Docker删除容器与镜像](https://blog.csdn.net/qq_32447301/article/details/79387649)

