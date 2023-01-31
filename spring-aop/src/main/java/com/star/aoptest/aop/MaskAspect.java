package com.star.aoptest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-08-19 20:06
 *
 * <p>
 */
@Aspect
@Component
public class MaskAspect {

    private static final Logger log = LoggerFactory.getLogger(MaskAspect.class);

    @Pointcut(value = "@annotation(com.star.aoptest.aop.Annotations.Mask)")
    public void asp() {}




    /**
     * 定义一个前置通知
     *
     * @author:
     * @Date:
     */
    @Before("asp()")
    public void aopBefore() {
        log.info("前置通知 MaskAspect SpringBootAspect....aopBefore");
    }

    /**
     * 定义一个后置通知
     *
     * @author:
     * @Date:
     */
    @After("asp()")
    public void aopAfter() {
        log.info("后置通知 MaskAspect  SpringBootAspect....aopAfter");
    }


    /**
     * 处理未处理的JAVA异常
     *
     * @author:
     * @Date:
     */
    @AfterThrowing(pointcut = "asp()", throwing = "e")
    public void exception(Exception e) {
        log.info("异常通知 MaskAspect SpringBootAspect...exception ..", e);
    }




    /**
     * 环绕通知
     *
     * @throws Throwable
     * @author:
     * @Date:
     */
    @Around("asp()")
    public void around(ProceedingJoinPoint invocation) throws Throwable {
        log.info("SpringBootAspect..MaskAspect..环绕通知 =====");
        long beginTime = System.currentTimeMillis();


    }
}
