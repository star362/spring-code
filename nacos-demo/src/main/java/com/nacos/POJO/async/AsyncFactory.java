package com.nacos.POJO.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: AsyncFactory
 * @Description:
 * @date: 2019-10-18 15:39
 * @describe:
 */
@Component
public class AsyncFactory {
    @Async
    public void say(Integer id){

        System.out.format("异步执行线程名字 %s%n",Thread.currentThread().getName());

    }

}
