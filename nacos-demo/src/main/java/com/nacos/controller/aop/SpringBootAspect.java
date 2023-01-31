package com.nacos.controller.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.Aspect;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: SpringBootAspect
 * @Description:
 * @date: 2019-10-29 14:03
 * @describe:
 */
@Aspect
@Component
public class SpringBootAspect {

    /**
     * 定义一个切入点
     * @author:
     * @Date:
     */
    @Pointcut(value="execution(* com.nacos.controller.NacosTest.a(..))")
    public void aop(){}



    /**
     * 定义一个前置通知
     * @author:
     * @Date:
     */
    @Before("aop()")
    public void aopBefore(){
        System.out.println("前置通知 SpringBootAspect....aopBefore");
    }

    /**
     * 定义一个后置通知
     * @author:
     * @Date:
     */
    @After("aop()")
    public void aopAfter(){
        System.out.println("后置通知  SpringBootAspect....aopAfter");
    }

    /**
     * 处理未处理的JAVA异常
     * @author:
     * @Date:
     */
    @AfterThrowing(pointcut="aop()",throwing="e")
    public void exception(Exception e){
        System.out.println("异常通知 SpringBootAspect...exception .." + e);
    }


    // returning的值和doAfterReturning的参数名一致
//    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
//        System.out.println("----- 返回值 : " + ret);
//    }

    /**
     * 环绕通知
     * @author:
     * @throws Throwable
     * @Date:
     */
    /*@Around("aop()")
    public void around(ProceedingJoinPoint invocation) throws Throwable{
        System.out.println("SpringBootAspect..环绕通知 Before");
        invocation.proceed();
        System.out.println("SpringBootAspect..环绕通知 After");
    }*/
}
