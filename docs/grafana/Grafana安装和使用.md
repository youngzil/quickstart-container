- [Grafana介绍](#Grafana介绍)
- [Grafana安装](#Grafana安装)
- [Grafana数据源和Dashboard](#Grafana数据源和Dashboard)



---------------------------------------------------------------------------------------------------------------------
## Grafana介绍


[Grafana官网](https://grafana.com/grafana/)  
[Grafana学习](https://grafana.com/blog/)  
[Grafana Github](https://github.com/grafana/grafana)  
[Grafana文档](https://grafana.com/docs/)  
[Grafana安装手册](https://grafana.com/docs/grafana/latest/installation/)  
[Grafana docker安装手册](https://grafana.com/docs/grafana/latest/installation/docker/)  
[Grafana下载](https://grafana.com/grafana/download?platform=mac)  


The open and composable observability and data visualization platform. Visualize metrics, logs, and traces from multiple sources like Prometheus, Loki, Elasticsearch, InfluxDB, Postgres and many more.

开放且可组合的可观察性和数据可视化平台。可视化来自多个来源的指标，日志和跟踪，例如Prometheus，Loki，Elasticsearch，InfluxDB，Postgres等

The open-source platform for monitoring and observability.

用于监视和可观察性的开源平台。


---------------------------------------------------------------------------------------------------------------------
## Grafana安装


[Grafana安装手册](https://grafana.com/docs/grafana/latest/installation/)  
[Grafana docker安装手册](https://grafana.com/docs/grafana/latest/installation/docker/)

[grafana配置](https://www.cnblogs.com/shhnwangjian/p/6911415.html)  




### MacOS安装

brew update
brew install grafana

curl -O https://dl.grafana.com/oss/release/grafana-7.5.7.darwin-amd64.tar.gz
tar -zxvf grafana-7.5.7.darwin-amd64.tar.gz



后台启动grafana

nohup yourPath/bin/grafana-server > yourLogPath/grafana.stdout 2>&1 &


cd grafana-7.5.7
mkdir -p logs
nohup bin/grafana-server > logs/grafana.stdout 2>&1 &


登录grafana，默认端口为3000, 初始账号密码为admin/admin

访问http://localhost:3000就可以进入到Grafana的界面中，默认情况下使用账户admin/admin进行登录。




### Docker安装

docker pull grafana/grafana:latest

docker pull grafana/grafana:7.5.2


docker run -d --name=grafana -p 3000:3000 grafana/grafana
docker run -d --name grafana -p 3000:3000 grafana/grafana:7.5.2



docker exec -it grafana bash

ps -ef| grep grafana

查看grafana进程信息，数据路径和日志路径等


访问http://localhost:3000就可以进入到Grafana的界面中，默认情况下使用账户admin/admin进行登录。


配置InfluxDB数据源：
- Query Language要配置influxQL，否则可能有问题，连接不上，网上好多人都遇到了，没有解决
- url要使用ip，不然可能网络不通，比如本机就http://172.16.113.4:8086





### 安装插件

docker run -d \
--name=grafana \
-p 3000:3000 \
-e "GF_INSTALL_PLUGINS=grafana-clock-panel,grafana-simple-json-datasource" \
grafana/grafana

或者

-e "GF_INSTALL_PLUGINS=grafana-clock-panel 1.0.1,grafana-simple-json-datasource 1.3.5".



Install plugins from other sources

docker run -d \
-p 3000:3000 \
--name=grafana \
-e "GF_INSTALL_PLUGINS=http://plugin-domain.com/my-custom-plugin.zip;custom-plugin" \
grafana/grafana


---------------------------------------------------------------------------------------------------------------------
## Grafana数据源和Dashboard


一、添加数据源

Grafana默认支持的数据源：Graphite，InfluxDB，OpenTSDB，Prometheus，Elasticsearch，CloudWatch

Grafana支持同时绑定多套数据源，根据自己需求管理即可。



二、管理仪表盘（Dashboard）

仪表盘（Dashboard），顾名思义，就是管理各种图表的地方。
仪表盘由行（Row）+图表面板（Panel）组成。
Panel主要支持：Graph，Singlestat，Dashlist，Table和Text。

图形（Graph）、统计（Stat）、表格（Table）、文本（Text）
Panel：面板，实际上就是row展示信息的方式，支持表格（table），列表（alert list），热图（Heatmap）等多种方式，具体可以去官网上查阅。



[grafana dashboards模板下载](https://grafana.com/grafana/dashboards)


[Grafana全面瓦解](https://www.jianshu.com/p/7e7e0d06709b)  
[Prometheus 快速入门教程（三）：Grafana 图表配置快速入门](https://www.cnblogs.com/chanshuyi/p/03_grafana_chart_quick_start.html)  
[Prometheus运维七 详解可视化Grafana工具](https://blog.csdn.net/ZhanBiaoChina/article/details/107048324)  




[Grafana快速入门：InfluxDB数据源以及曲线图表仪表盘配置](https://ken.io/note/grafana-quickstart-influxdb-datasource-graph)  
[Prometheus + Grafana 监控配置指北：打造日志监控系统](https://counter2015.com/2019/04/30/grafana-moniter/)  
[利用InfluxDB+Grafana搭建Flink on YARN作业监控大屏](https://cloud.tencent.com/developer/article/1677840)  
[Grafana + InfluxDB 实现 Jmeter 压测的图形化监控](https://cloud.tencent.com/developer/article/1728825)  
[使用Docker安装配置Grafana](https://www.voidking.com/dev-docker-grafana/)  


[How to Build Grafana Dashboards with InfluxDB, Flux and InfluxQL](https://www.influxdata.com/blog/how-grafana-dashboard-influxdb-flux-influxql/)  
[Use Grafana with InfluxDB OSS](https://docs.influxdata.com/influxdb/v2.0/tools/grafana/)  
[integration grafana/](https://www.influxdata.com/integration/grafana/)  
[How to Use Grafana with InfluxDB to Monitor Time Series Data](https://www.influxdata.com/blog/how-to-use-grafana-with-influxdb-to-monitor-time-series-data/)  
[partners/grafana/](https://www.influxdata.com/partners/grafana/)  
[Using InfluxDB in Grafana](https://grafana.com/docs/grafana/latest/datasources/influxdb/)  
[InfluxDB Datasource - Native Plugin](http://docs.grafana.org/datasources/influxdb/)  


[Telegraf+Influxdb+Grafana 轻量级监控系统部署](https://cloud.tencent.com/developer/article/1677926)  
[Docker+influxdb+grafana安装部署](https://blog.csdn.net/luoyiliuliu/article/details/108645756)  
[]()  
[]()  
[]()  

[阿里巴巴 Sentinel + InfluxDB + Chronograf 实现监控大屏](https://my.oschina.net/u/4346199/blog/3251780)  






