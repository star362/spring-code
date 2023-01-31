package com.star.springbeandemo.zhoenjiemodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-29 16:31
 *
 * <p>
 */
@Component
public class Finacial implements Department {

    private static final Logger log = LoggerFactory.getLogger(Finacial.class);

    @Autowired
    private Mediator mediator;  //持有中介者(总经理)的引用

    @Override
    public void selfAction() {
        log.info("财务部：数钱！");

    }

    @Override
    public void outAction() {
        log.info("财务部向总经理发出申请：汇报工作！没钱了，钱太多了！怎么花?");
    }
}
