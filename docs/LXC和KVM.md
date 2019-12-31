
```aidl



LXC和KVM区别
LXC介绍
KVM介绍





LXC和KVM区别：
LXC与KVM技术的比较,KVM的优点是一个物理机上可以跑多个操作系统(Guest-OS),然后在每个操作系统运行应用,通过这种方式实现应用的隔离。而使用LXC技术直接可以在Host-OS的基础上实现隔离的。这就是LXC的优势–运行快。但是，如果有两个应用一个是在windows运行的，一个是在linux上运行的，这时只能使用KVM技术来实现了。




1.LXC是什么？
LXC是Linux containers的简称，是一种基于容器的操作系统层级的虚拟化技术。

2.LXC可以做什么？
LXC可以在操作系统层次上为进程提供的虚拟的执行环境，一个虚拟的执行环境就是一个容器。可以为容器绑定特定的cpu和memory节点，分配特定比例的cpu时间、IO时间，限制可以使用的内存大小（包括内存和是swap空间），提供device访问控制，提供独立的namespace（网络、pid、ipc、mnt、uts）。

3.LXC如何实现？
Sourceforge上有LXC这个开源项目，但是LXC项目本身只是一个为用户提供一个用户空间的工具集，用来使用和管理LXC容器。LXC真正的实现则是靠Linux内核的相关特性，LXC项目只是对此做了整合。基于容器的虚拟化技术起源于所谓的资源容器和安全容器。

LXC在资源管理方面依赖与Linux内核的cgroups子系统，cgroups子系统是Linux内核提供的一个基于进程组的资源管理的框架，可以为特定的进程组限定可以使用的资源。LXC在隔离控制方面依赖于Linux内核的namespace特性，具体而言就是在clone时加入相应的flag（NEWNS NEWPID等等）。

4.为什么要选择LXC？
LXC是所谓的操作系统层次的虚拟化技术，与传统的HAL（硬件抽象层）层次的虚拟化技术相比有以下优势：

更小的虚拟化开销（LXC的诸多特性基本由内核特供，而内核实现这些特性只有极少的花费，具体分析有时间再说）
快速部署。利用LXC来隔离特定应用，只需要安装LXC，即可使用LXC相关命令来创建并启动容器来为应用提供虚拟执行环境。传统的虚拟化技术则需要先创建虚拟机，然后安装系统，再部署应用。
LXC跟其他操作系统层次的虚拟化技术相比，最大的优势在于LXC被整合进内核，不用单独为内核打补丁。

5.如何使用LXC？
参见博主其他博文：cgroup和LXC的安装、LXC常用命令介绍、LXC配置文件简介和LXC网络配置实例
https://www.cnblogs.com/lisperl/archive/2012/04/15/2450183.html




基于内核的虚拟机（英语：Kernel-based Virtual Machine，缩写为KVM）是一种用于Linux内核中的虚拟化基础设施，可将Linux内核转化为一个虚拟机监视器。






LXC参考
https://linuxcontainers.org/
https://www.cnblogs.com/lisperl/archive/2012/04/15/2450183.html
https://blog.csdn.net/warrior_0319/article/details/79663213
https://www.ibm.com/developerworks/cn/linux/l-lxc-containers/index.html
https://blog.csdn.net/u011630575/article/details/65444404



KVM参考
https://www.linux-kvm.org/page/Main_Page
https://www.cnblogs.com/luckyall/p/6203008.html



相关连接：
KVM的官方地址：http://www.linux-kvm.org/page/Main_Page
KVM的Howto文档：http://www.linux-kvm.org/page/HOWTO
Kqemu源码地址：http://sourceforge.net/projects/kqemu/
Qemu下载地址：http://wiki.qemu.org/Main_Page
