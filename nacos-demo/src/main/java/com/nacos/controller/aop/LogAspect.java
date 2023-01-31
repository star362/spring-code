package com.nacos.controller.aop;


import com.nacos.repistory.A;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Slf4j
@Aspect
@Order(2)
@Component
public class LogAspect {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
//    @Qualifier("test2SqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate2;

    @Pointcut(value = "@annotation(com.nacos.controller.aop.CostTime)")
    public void aop() {}


    /**
     * 定义一个前置通知
     *
     * @author:
     * @Date:
     */
    /*@Before("aop()")
    public void aopBefore() {
        log.info("前置通知 LogAspect SpringBootAspect....aopBefore");
    }*/

    /**
     * 定义一个后置通知
     *
     * @author:
     * @Date:
     */
    /*@After("aop()")
    public void aopAfter() {
        log.info("后置通知 LogAspect  SpringBootAspect....aopAfter");
    }
*/

    /**
     * 处理未处理的JAVA异常
     *
     * @author:
     * @Date:
     */
    @AfterThrowing(pointcut = "aop()", throwing = "e")
    public void exception(Exception e) {
        log.info("异常通知 LogAspect SpringBootAspect...exception ..", e);
    }


    // returning的值和doAfterReturning的参数名一致
//    @AfterReturning(returning = "ret", pointcut = "aop()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
//        System.out.println("----- 返回值 : " + ret);
//    }

    /**
     * 环绕通知
     *
     * @throws Throwable
     * @author:
     * @Date:
     */
    @Around("aop()")
    public void around(ProceedingJoinPoint invocation) throws Throwable {
        log.info("SpringBootAspect..LogAspect..环绕通知 Before");
        long beginTime = System.currentTimeMillis();

        //执行方法
        invocation.proceed();

        log.info("SpringBootAspect..LogAspect..环绕通知 After");
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;


        String className = invocation.getTarget().getClass().getName();
        String methodName = invocation.getSignature().getName();
        log.info("class:[{}]==method:[{}]===cost:[{}]:ms", className , methodName , time );


        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) invocation.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        CostTime logAnno = method.getAnnotation(CostTime.class);
        // 获取操作描述的属性值
        String operateType = logAnno.value();
        log.info("db:[{}]",operateType);
        final Map<String, Object> nameAndValue = getNameAndValue(invocation);
        nameAndValue.forEach((k,v)->log.info("====key:[{}]===value[{}]",k,v.toString()));


//        if("A".equals(operateType)){
//            final List<starUser> o = sqlSessionTemplate.selectList("com.nacos.dao.UserDao.findByAgeOrId", nameAndValue);
//            o.forEach(s->log.info("===[{}]",s.toString()));
//        }else{
//            final A o = sqlSessionTemplate2.selectOne("com.nacos.dao.ADao.selAll");
//            log.info("BBBBBB[{}]",o.toString());
//        }
    }

    /**
     * 获取参数Map集合
     * @param joinPoint
     * @return
     */
    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }



}