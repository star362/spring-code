package com.star.springbeandemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangyu
 * @date: 2020-02-18 14:28
 * @describe:
 */
@Configuration
@EnableAsync
@Slf4j
public class ShutDownConfig {



    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
//        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        Executor executorService = Executors.newCachedThreadPool();//获取到服务器的cpu内核
        executor.setCorePoolSize(5);//核心池大小
        executor.setMaxPoolSize(10);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("star-asyn-");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        executor.initialize();
        return executor;
    }


}
