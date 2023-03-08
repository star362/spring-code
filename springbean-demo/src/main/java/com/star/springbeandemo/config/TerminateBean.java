package com.star.springbeandemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author wangyu
 * @date: 2020-02-18 14:25
 * @describe:
 */
@Slf4j
//@Component
public class TerminateBean {

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @PreDestroy
    public void preDestroy(){
        log.info("============PreDestroy==========");
        threadPoolTaskExecutor.shutdown();
    }





}



