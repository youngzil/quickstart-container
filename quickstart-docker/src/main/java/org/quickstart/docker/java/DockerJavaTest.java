/**
 * 项目名称：quickstart-docker 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2019年4月1日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.docker.java;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Event;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.EventsResultCallback;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2019年4月1日 下午12:42:43
 * @since 1.0
 */
public class DockerJavaTest {

    public static void main(String[] args) {

        // 根据自己的情况，按需填写
        /* DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory().withReadTimeout(1000)
                .withConnectTimeout(1000).withMaxTotalConnections(100).withMaxPerRouteConnections(10);
        
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerTlsVerify(true)
                .withDockerCertPath("D:/Data/cert/").withDockerHost("tcp://192.168.99.101:2376")
                .withDockerConfig("D:/Data/cert/").withApiVersion("1.23").withRegistryUrl("https://index.docker.io/v1/")
                .withRegistryUsername("username").withRegistryPassword("password")
                .withRegistryEmail("email").build();
                  DockerClient dockerClient = DockerClientBuilder.getInstance(config).withDockerCmdExecFactory(dockerCmdExecFactory).build();*/

        /*DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://my-docker-host.tld:2376")
                .withDockerTlsVerify(true)
                .withDockerCertPath("/home/user/.docker/certs")
                .withDockerConfig("/home/user/.docker")
                .withApiVersion("1.30") // optional
                .withRegistryUrl("https://index.docker.io/v1/")
                .withRegistryUsername("dockeruser")
                .withRegistryPassword("ilovedocker")
                .withRegistryEmail("dockeruser@github.com")
                .build();
        DockerClient docker = DockerClientBuilder.getInstance(config).build();*/

        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://127.0.0.1:2375").build();

        // docker info
        Info info = dockerClient.infoCmd().exec();
        System.out.println(info);

        CreateContainerResponse container = dockerClient.createContainerCmd("quickstart/tomcat:9").exec();
        dockerClient.startContainerCmd(container.getId()).exec();

        InspectContainerResponse inspectContainerResponse = dockerClient.inspectContainerCmd(container.getId()).exec();
        System.out.println("imageId=" + inspectContainerResponse.getImageId() + ",args=" + inspectContainerResponse.getArgs());

        // 搜索镜像
        List<SearchItem> dockerSearch = dockerClient.searchImagesCmd("busybox").exec();
        System.out.println("Search returned" + dockerSearch.toString());

        // pull
        dockerClient.pullImageCmd("busybox:latest").exec(new ResultCallback<PullResponseItem>() {
            public void onStart(Closeable closeable) {

            }

            public void onNext(PullResponseItem object) {
                System.out.println(object.getStatus());
            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onComplete() {
                System.out.println("pull finished");
            }

            public void close() throws IOException {

            }
        });

        // 创建container 并测试
        // create
        CreateContainerResponse busyboxContainer = dockerClient.createContainerCmd("busybox")
                .withCmd("/bin/bash")
                .exec();
        // start
        dockerClient.startContainerCmd(busyboxContainer.getId()).exec();
        dockerClient.stopContainerCmd(busyboxContainer.getId()).exec();
        dockerClient.removeContainerCmd(busyboxContainer.getId()).exec();

        EventsResultCallback callback = new EventsResultCallback() {
            @Override
            public void onNext(Event event) {
                System.out.println("Event: " + event);
                super.onNext(event);
            }
        };

        try {
            dockerClient.eventsCmd().exec(callback).awaitCompletion().close();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
