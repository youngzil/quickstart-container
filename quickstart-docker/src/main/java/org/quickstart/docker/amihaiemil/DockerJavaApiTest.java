/**
 * 项目名称：quickstart-docker 
 * 文件名：DockerJavaApiTest.java
 * 版本信息：
 * 日期：2019年4月1日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.docker.amihaiemil;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

import com.amihaiemil.docker.Container;
import com.amihaiemil.docker.Containers;
import com.amihaiemil.docker.Docker;
import com.amihaiemil.docker.LocalDocker;
import com.amihaiemil.docker.RemoteDocker;

/**
 * DockerJavaApiTest
 * 
 * @author：yangzl@asiainfo.com
 * @2019年4月1日 下午2:41:24
 * @since 1.0
 */
public class DockerJavaApiTest {
    public static void main(String[] args) throws IOException {

        final Docker docker = new RemoteDocker(URI.create("http://localhost:2375"));

        // final Docker localDocker = new LocalDocker("unix:///var/run/docker.sock");

        final Containers containers = docker.containers();
        // 创建容器
        /*final Container created = docker.containers().create(
                Json.createObjectBuilder()
                    .add(..., ...)
                    .build()
              );*/

        final Iterator<Container> existing = containers.all();
        existing.forEachRemaining(container -> {
            System.out.println(container.containerId());
            // System.out.println(container.getString("Names"));
        });

        final Container testCreateContainer = containers.create("testCreate", "quickstart/tomcat:9");
        System.out.println(testCreateContainer.containerId());
        testCreateContainer.start();
        testCreateContainer.stop();
        testCreateContainer.remove();

        for (final Container container : containers) {
            // Containers is the entry point of the Containers API
            // and also implements Iterable<Container>
            System.out.println(container.containerId());
        }

    }

}
