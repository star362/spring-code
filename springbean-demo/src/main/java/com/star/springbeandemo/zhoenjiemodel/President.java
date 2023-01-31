package com.star.springbeandemo.zhoenjiemodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-29 16:29
 *
 * <p>
 *     是实现中介者接口
 */
@Component
public class President implements Mediator {


    @Autowired
    private Map<String,Department> map ;



    @Override
    public void command(String dname) {
        map.get(dname).selfAction();
    }
}
