package transactiiondemo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyu
 * @Title BeanUtils
 * @describe
 * @date 2019/11/20 09:57
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware, BeanFactoryAware, InitializingBean, BeanPostProcessor, BeanNameAware {

    private static final Logger log = LoggerFactory.getLogger(SpringBeanUtils.class);

    private static ApplicationContext context;

    private static BeanFactory beanFactory;


    public SpringBeanUtils() {
        log.info("=====构造方法=========");
    }

    public static <T> T getBeanOfClass(Class<T> t) {
        Assert.notNull(t, "获取bean的类型不能为空");
        return context.getBean(t);
    }

    public static Object getBeanOfName(String name) {
        Assert.notNull(name, "获取bean的名称不能为空");
        return context.getBean(name);
    }

    public static <T> T getBeanOfNameAndClass(String name, Class<T> t) {
        Assert.notNull(name, "获取bean的名称不能为空");
        Assert.notNull(t, "获取bean的类型不能为空");
        return context.getBean(name, t);
    }

    public static <T> Map<String, T> getBeanMapsOfType(Class<T> clazz) {
        Assert.notNull(clazz, "获取bean的类型不能为空");
        return context.getBeansOfType(clazz);
    }

    public static <T> List<T> getBeanListOfType(Class<T> clazz) {
        Assert.notNull(clazz, "获取bean的类型不能为空");
        final Map<String, T> map = context.getBeansOfType(clazz);
        List<T> list = new LinkedList<>();
        map.forEach((k, v) -> list.add(v));
        return list;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringBeanUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.context = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        log.info("=======setBeanName===[{}]", name);
    }

    @PostConstruct
    public void initMethod() {
        log.info("=====PostConstruct======");
    }

    @PreDestroy
    public void PreDestroy() {
        log.info("=====PreDestroy======");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("===InitializingBean===");
        Assert.notNull(context, "容器初始化错误");
        Assert.notNull(beanFactory, "容器初始化错误");
    }


}
