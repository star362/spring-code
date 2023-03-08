package com.star.springbeandemo;

import com.star.springbeandemo.config.PayParserFactory;
import com.star.springbeandemo.config.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbeanDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbeanDemoApplication.class, args);
    }

    @Autowired
    private PayParserFactory payParserFactory;


    @Override
    public void run(String... args) throws Exception {
        log.info("===run =={}");
        payParserFactory.getPayParser("market").selfAction();
    }
}




