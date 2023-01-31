package com.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: Main
 * @Description:
 * @date 2019/8/28 21:46
 * @描述
 */
@EnableScheduling
@EnableConfigurationProperties
@EnableAsync
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}



