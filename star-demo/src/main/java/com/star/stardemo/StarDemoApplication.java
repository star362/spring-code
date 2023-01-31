package com.star.stardemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangyu
 */
@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class StarDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarDemoApplication.class, args);
    }


}
