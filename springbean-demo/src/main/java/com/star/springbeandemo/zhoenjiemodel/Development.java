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
public class Development implements Department {

    private static final Logger log = LoggerFactory.getLogger(Development.class);

    @Autowired
    private Mediator mediator;  //持有中介者(总经理)的引用

    @Override
    public void selfAction() {
        log.info("研发部：专心科研，开发项目！");
        mediator.command("finacial");
    }

    @Override
    public void outAction() {
        log.info("研发部向总经理发出申请：汇报工作！没钱了，需要资金支持！");
    }
}
