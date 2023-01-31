package com.nacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyu
 * @Title BeanUtils
 * @describe
 * @date 2019/11/20 09:57
 */
@Slf4j
@Component
public class SpringBeanUtils implements ApplicationContextAware, BeanFactoryAware, InitializingBean {

    private static ApplicationContext context;

    private static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
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
    public void afterPropertiesSet() throws Exception {

    }
}
