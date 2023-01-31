package com.joruns.test.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.junit.Assert.*;



public class DemoApplicationTest {

    @Test
    public void main() {
        final AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(DemoApplication.class);
        final AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(DemoApplication.class);

        System.out.println(annotationConfigWebApplicationContext.getBeanDefinitionNames());
    }
}