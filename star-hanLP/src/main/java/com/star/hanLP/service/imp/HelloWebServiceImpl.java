package com.star.hanLP.service.imp;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.star.hanLP.service.HelloWebService;
import org.springframework.context.annotation.Configuration;

/**
 * Description: <br>
 * Create Date: 2020-11-25 18:07 <br>
 *
 * @author wangyu@mvtech.com.cn
 */
@WebService(
        targetNamespace = "demo.example.com", //wsdl命名空间
        serviceName = "HelloWebService",                 //portType名称 客户端生成代码时 为接口名称
        endpointInterface = "com.star.hanLP.service.HelloWebService")//指定发布webservcie的接口类，此类也需要接入@WebService注解
@Configuration
public class HelloWebServiceImpl implements HelloWebService {

    @Override
    public String Hello(@WebParam(name="name") String name) {
        System.out.println("欢迎你"+name);
        return "欢迎你"+name;
    }
}