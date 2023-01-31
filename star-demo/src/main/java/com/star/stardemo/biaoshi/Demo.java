package com.star.stardemo.biaoshi;

import com.star.stardemo.service.impl.OtherServiceImpl;
import com.star.stardemo.service.impl.OtherServiceImpl2;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author wangyu
 * @date: 2019-12-21 17:43
 * @describe:
 */
public class Demo implements ImportBeanDefinitionRegistrar, DisposableBean {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        final RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(OtherServiceImpl.class);
        final RootBeanDefinition rootBeanDefinition2 = new RootBeanDefinition(OtherServiceImpl2.class);
        beanDefinitionRegistry.registerBeanDefinition("otherServiceImpl", rootBeanDefinition);
        beanDefinitionRegistry.registerBeanDefinition("otherServiceImpl2", rootBeanDefinition2);
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("测试 Bean 已销毁 ...");
    }
}
