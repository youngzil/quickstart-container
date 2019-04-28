/**
 * 项目名称：quickstart-kubernetes 
 * 文件名：Fabric8KubernetesClientTest.java
 * 版本信息：
 * 日期：2019年4月3日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.kubernetes.fabric8io.kubernetes;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * Fabric8KubernetesClientTest
 * 
 * @author：youngzil@163.com
 * @2019年4月3日 下午3:33:51
 * @since 1.0
 */
public class Fabric8KubernetesClientTest {

    public static void main(String[] args) {
        
//        参考https://blog.csdn.net/jiangpeng_xu/article/details/83688990

        Config config = new ConfigBuilder().withMasterUrl("http://127.0.0.1:8080").build();
        KubernetesClient client = new DefaultKubernetesClient(config);// 使用默认的就足够了

        /*获取资源操作类
         client.apps().deployments()
        client.namespaces() 
        client.services()
        client.pods()       
        client.customResources()
        client.storage()
        client.network()
        client.nodes()
        client.replicationControllers()
        client.resourceQuotas()
        */

        // 资源的CRUD
        String namespaceName = "default";
        // 创建：
//        Service service = client.services().inNamespace(namespaceName).create(service);

        // 更新：
        Namespace namespace = client.namespaces().withName(namespaceName).get();
        // update resources
        client.namespaces().createOrReplace(namespace);

        // 查询：
        ServiceList services = client.services().inNamespace("default").list();
        Service service2 = client.services().inNamespace("default").withName("myservice").get();

        // 删除：
        client.services().inNamespace("default").withName("myservice").delete();

        client.services().inNamespace("default").withName("myservice").edit()
                .editMetadata()
                .addToLabels("another", "label")
                .endMetadata()
                .done();

    }
}
