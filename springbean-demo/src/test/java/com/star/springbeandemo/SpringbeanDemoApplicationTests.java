package com.star.springbeandemo;

import com.star.springbeandemo.controller.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbeanDemoApplicationTests {

    @Autowired
    private TestController TestController;

    @Test
    public void contextLoads() {
        TestController.aa1();
    }

}
