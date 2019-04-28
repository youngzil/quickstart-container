/**
 * 项目名称：quickstart-docker 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2019年4月1日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.docker.spotify;

import java.net.URI;
import java.util.List;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerClient.ListContainersParam;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2019年4月1日 下午12:29:15
 * @since 1.0
 */
public class DockerClientTest {

    public static void main(String[] args) {
        // ip是在自己虚拟机的ip
        final DockerClient docker = DefaultDockerClient.builder().uri(URI.create("http://localhost:2375")).build();

        try {
            // 获取容器详情，输入参数是容器ID
            final ContainerInfo info = docker.inspectContainer("d60ca1164886");
            System.out.println(info.toString());

            final List<Container> containers = docker.listContainers(ListContainersParam.allContainers());
            final List<Container> containersRuning = docker.listContainers();
            int containersStop = 0;
            for (int i = 0; i < containers.size(); i++) {
                if (containers.get(i).state().equals("exited")) {
                    containersStop++;
                }
            }
            System.out.println("容器总数：" + containers.size());
            System.out.println("正在运行的容器数量：" + containersRuning.size());
            System.out.println("停止的容器：" + containersStop);

        } catch (DockerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
