package com.star.springbeandemo.zhoenjiemodel;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-29 16:28
 *
 * <p>
 *     中介者接口
 */
@FunctionalInterface
public interface Mediator {
    void command(String dname);
}
