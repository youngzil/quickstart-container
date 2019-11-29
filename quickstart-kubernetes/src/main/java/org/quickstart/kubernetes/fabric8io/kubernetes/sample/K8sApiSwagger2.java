/**
 * 项目名称：quickstart-kubernetes 
 * 文件名：K8sApiSwagger.java
 * 版本信息：
 * 日期：2019年3月28日
 * Copyright youngzil Corporation 2019
 * 版权所有 *
 */
package org.quickstart.kubernetes.fabric8io.kubernetes.sample;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * K8sApiSwagger
 * 
 * @author：youngzil@163.com
 * @2019年3月28日 下午5:05:38
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class K8sApiSwagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.quickstart.kubernetes.fabric8io.kubernetes.sample"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot - Swagger Api File")
                .description("RESTful API")
                .termsOfServiceUrl("http://k8sapitest:port/test")
                .version("1.0")
                .build();
    }
}
