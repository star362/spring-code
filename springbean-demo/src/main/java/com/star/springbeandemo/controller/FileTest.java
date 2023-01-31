package com.star.springbeandemo.controller;

import com.star.springbeandemo.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangyu
 * @date: 2020-03-07 12:38
 * @describe:
 */
@RestController
@PropertySource({"classpath:test.properties"})
public class FileTest {


    @Value("${className}")
    private String className;
    @Value("${methodName}")
    private String methodName;

    @Autowired
    private AService aService;


    @GetMapping("/filete")
    public String fileTe() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class c = Class.forName(className);
        final Object o = c.newInstance();
        final Method method = c.getMethod(methodName, null);//方法名 参数类型
        final String invoke = (String) method.invoke(o, null);
        System.out.println(invoke);
        return "success";
    }

//    @GetMapping("/getbean")
//    public void getbean(){
//        ReflectionUtils.invokeMethod(,aService,null);
//    }


}
