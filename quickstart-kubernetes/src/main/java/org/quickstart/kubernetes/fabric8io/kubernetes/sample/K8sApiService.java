/**
 * 项目名称：quickstart-kubernetes 
 * 文件名：K8sApiService.java
 * 版本信息：
 * 日期：2019年3月28日
 * Copyright youngzil Corporation 2019
 * 版权所有 *
 */
package org.quickstart.kubernetes.fabric8io.kubernetes.sample;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * K8sApiService
 * 
 * @author：youngzil@163.com
 * @2019年3月28日 下午4:32:29
 * @since 1.0
 */
public class K8sApiService {
    // k8s api封装库调用
    private static KubernetesClient kubernetesClient;
    private static Config config;

    // 初始化 - 连接k8s api server
    public static String init() {
        String initResult = "Init Failed.";
        try {
            config = new ConfigBuilder().withMasterUrl("http://127.0.0.1:8080").build();
            kubernetesClient = new DefaultKubernetesClient(config);
            initResult = "Init Success.";
            System.out.println("init sucess");
        } catch (Exception e) {
            System.out.println("can't init discovery service");
        }
        return initResult;
    }

    // 列出当前命名空间
    public static NamespaceList listNamespace() {
        NamespaceList namespaceList = new NamespaceList();
        try {
            namespaceList = kubernetesClient.namespaces().list();
            System.out.println("list sucess");
        } catch (Exception e) {
            System.out.println("list failed");
        }
        return namespaceList;
    }

    // 列出当前可用节点
    public static NodeList listNode() {
        NodeList nodeList = new NodeList();
        try {
            nodeList = kubernetesClient.nodes().list();
            System.out.println("list sucess");
        } catch (Exception e) {
            System.out.println("list failed");
        }
        return nodeList;
    }
}
