package com.elasticsearchdemo.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-07-02 13:01
 *
 * <p>
 */
@Component
public class BeanUtils implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("springBeanUtils".equals(beanName)) {
        log.info("===postProcessBeforeInitialization====beanName=====[{}]=======bean==[{}]", beanName, bean);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("springBeanUtils".equals(beanName)) {
        log.info("===postProcessAfterInitialization====beanName=====[{}]=======bean==[{}]", beanName, bean);
        }
        return bean;
    }
}
