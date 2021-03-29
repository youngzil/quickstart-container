有时候我们想执行某个容器的某条命令，但又不想进入容器内。那该怎么办？

于是脚本可以这样写

```
#!/bin/bash
DOCKER_ID=62f3f40ab240
sudo docker exec -it $DOCKER_ID /bin/bash -c 'cd /packages/detectron && python tools/train.py'
```



参考
[docker在容器外执行某个容器内的某个命令](https://blog.csdn.net/weixin_32820767/article/details/80643091)


