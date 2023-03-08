package com.star.springbeandemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author wangyu
 * @date: 2020-02-07 09:35
 * @describe:
 */
@Service
@Slf4j
//@Scope("prototype")
public class AService {

    @Autowired
    private BService bService;

    @PostConstruct
    public void initme(){
        log.info("======bbbb===============");
    }



    public void apply(){
        System.out.println("aService====apply");
    }


}
