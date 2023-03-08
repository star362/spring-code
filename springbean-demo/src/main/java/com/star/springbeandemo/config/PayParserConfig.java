package com.star.springbeandemo.config;


import com.star.springbeandemo.zhoenjiemodel.Department;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayParserConfig {

    /**
     * 初始化 payParserFactory bean
     *
     * @return
     */
    @Bean("payParserFactory")
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        // 设置服务定位接口
        factoryBean.setServiceLocatorInterface(PayParserFactory.class);
        return factoryBean;
    }
}
