package com.star.aoptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-08-19 19:49
 *
 * <p>
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AopMain {

    public static void main(String[] args) {
        SpringApplication.run(AopMain.class);
    }


}
