package datasourcerouting.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-30 19:59
 *
 * <p>
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class HandlerDataSourceAop {

    //@within在类上设置
    //@annotation在方法上进行设置
    @Pointcut("@within(datasourcerouting.utils.DynamicSwitchDataSource)||" +
            "@annotation(datasourcerouting.utils.DynamicSwitchDataSource)")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint)
    {
        log.info("=======pointcut()===Before=====");
    }


    /**
     * 环绕通知
     *
     * @throws Throwable
     * @author:
     * @Date:
     */
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        /*log.info("SpringBootAspect..LogAspect..环绕通知 Before");
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
        DynamicSwitchDataSource logAnno = method.getAnnotation(DynamicSwitchDataSource.class);
        // 获取操作描述的属性值
        DataSourceeEnum operateType = logAnno.dataSource();
        log.info("db:[{}]",operateType);*/


        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);//获取方法上的注解
        if(annotationClass == null){
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);//获取类上面的注解
            if(annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        DataSourceeEnum dataSourceKey = annotationClass.dataSource();
        if(dataSourceKey !=null){
            //给当前的执行SQL的操作设置特殊的数据源的信息
            HandlerDataSource.putDataSource(dataSourceKey);
        }
        log.info("AOP动态切换数据源，className"+joinPoint.getTarget().getClass().getName()
                +"methodName"+method.getName()+";" +
                "dataSourceKey:"+dataSourceKey.toString()==""?"默认数据源":dataSourceKey.toString()
        );

    }

    /**
     * 处理未处理的JAVA异常
     *
     * @author:
     * @Date:
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void exception(Exception e) {
        log.info("异常通知 LogAspect SpringBootAspect...exception ..", e);
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        HandlerDataSource.clear();

        log.info("=======pointcut()===after=====");
    }
}
