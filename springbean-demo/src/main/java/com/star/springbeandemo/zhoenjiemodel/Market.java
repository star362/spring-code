package com.star.springbeandemo.zhoenjiemodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-29 16:32
 *
 * <p>
 */
@Component
public class Market implements Department {
    private static final Logger log = LoggerFactory.getLogger(Market.class);

    @Autowired
    private Mediator mediator;  //持有中介者(总经理)的引用

    @Override
    public void selfAction() {
        log.info("市场部：跑去接项目！");
        mediator.command("development");
    }

    @Override
    public void outAction() {
        log.info("市场部向总经理发出申请：汇报工作！项目承接的进度，需要资金支持！");
        mediator.command("finacial");

    }
}
