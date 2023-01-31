package com.star.springbeandemo.controller;

import com.star.springbeandemo.config.SpringBeanUtils;
import com.star.springbeandemo.config.TerminateBean;
import com.star.springbeandemo.service.AService;
import com.star.springbeandemo.zhoenjiemodel.Market;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author wangyu
 * @date: 2020-02-07 09:43
 * @describe:
 */
@RestController
@Slf4j
public class TestController  {



    @Autowired
    ConfigurableApplicationContext context;



    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;




    @GetMapping("/test")
    public String test(){

        return "close success";
    }




    @GetMapping("/aa")
    public String aa(){

        threadPoolTaskExecutor.execute(() -> {

            int i=0;
            while (i<200000) {
            while (i<200000) {while (i<200000) {while (i<200000) {while (i<200000) {while (i<200000) {}}}}}

                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                Random random=new Random();
                random.nextInt(10);
                System.out.println("===="+i+":"+Thread.currentThread().getName());
                i++;
            }
            System.out.println("runnable===========:"+Thread.currentThread().getName());
        });


        return "success";
    }


    @GetMapping("/aaa")
    public String aaa(){
        
        log.info("==========aaaaa=========");

        return "success";
    }

    @Autowired
    private Market market;

    @GetMapping("/aa1")
    public String aa1(){


        market.selfAction();
        //market.outAction();


        return "success";
    }


    @GetMapping("/getbean1")
    public void getbean1(){
        final ApplicationContext context = SpringBeanUtils.getContext();
        ConfigurableApplicationContext context1= (ConfigurableApplicationContext) context;
        final DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context1.getBeanFactory();
        beanFactory.removeBeanDefinition("AService");
    }

    @GetMapping("/getbean")
    public String[] getbean(){
        SpringBeanUtils.getContext().getBeanDefinitionCount();
        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }

    @Autowired
    private AService aService;
    @GetMapping("/aService")
    public void aService(){

        aService.apply();
    }


}
