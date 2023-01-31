package com.joruns.test.demo;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan("com.joruns.test.demo.dao")
//@EnableScheduling
//@EnableCaching
public class AliPayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliPayDemoApplication.class, args);
    }
}
