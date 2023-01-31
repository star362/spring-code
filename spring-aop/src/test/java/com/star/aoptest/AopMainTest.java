package com.star.aoptest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.aoptest.entity.School;
import com.star.aoptest.utils.MaskUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AopMainTest {


    private static final Logger log = LoggerFactory.getLogger(AopMainTest.class);


    @Before
    public void before(){
        log.info("=========[{}]","前置 info");
    }

    @After
    public void after(){
        log.info("===========[{}]","后置 info");
    }

    @Test
    public  void test(){
        log.info("====================================");

        final School school = new School("微智信业", "哈尔滨", "wangyu@mvtech.com.cn", 13796189803L);

//        final School maskinfo = MaskUtils.maskinfo(school,School.class);

//        log.info("===========[{}]",maskinfo);
    }

}