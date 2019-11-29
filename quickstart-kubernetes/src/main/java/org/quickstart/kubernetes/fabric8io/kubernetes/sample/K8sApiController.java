/**
 * 项目名称：quickstart-kubernetes 
 * 文件名：K8sApiController.java
 * 版本信息：
 * 日期：2019年3月28日
 * Copyright youngzil Corporation 2019
 * 版权所有 *
 */
package org.quickstart.kubernetes.fabric8io.kubernetes.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;
import io.swagger.annotations.ApiOperation;

/**
 * K8sApiController
 * 
 * @author：youngzil@163.com
 * @2019年3月28日 下午5:10:50
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/test")
public class K8sApiController {
    K8sApiService devK8sApiService;

    // k8s api init
    @ApiOperation(value = "Init", notes = "Init")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initK8s() {
        return devK8sApiService.init();
    }

    // k8s namespace list
    @ApiOperation(value = "ListNamespace", notes = "ListNamespace")
    @RequestMapping(value = "/listnamespace", method = RequestMethod.GET)
    public NamespaceList listk8snamespace() {
        return devK8sApiService.listNamespace();
    }

    // k8s node list
    @ApiOperation(value = "ListNode", notes = "ListNode")
    @RequestMapping(value = "/listnode", method = RequestMethod.GET)
    public NodeList listk8snode() {
        return devK8sApiService.listNode();
    }
}
