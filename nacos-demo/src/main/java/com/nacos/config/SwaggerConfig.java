package com.nacos.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @author WANGYU
 * @ProjectName np
 * @Class: SwaggerConfig
 * @Description:
 * @date 2019/4/1 14:31
 * @描述
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.pathMapping("/sitech")//最终调用接口后会和paths拼接在一起
                .select()
                //限制只有在类上加@Api才添加到swagger，默认是都添加的
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //限制只有在方法上加@ApiOperation才添加到swagger，默认是都添加的
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.nacos"))//扫描com路径下的api文档
                .paths(PathSelectors.any())//路径判断
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("star-tencent-Email")//标题
                //.description("测试地址，http://localhost:8421")//描述
//                .termsOfServiceUrl("http://blog.csdn.net/saytime")//（不可见）条款地址
//                .contact(new Contact("王宇", http://www.baidu.com, "star362@163.com"))//作者
                .version("1.0")//版本号
                .build();
    }
}
