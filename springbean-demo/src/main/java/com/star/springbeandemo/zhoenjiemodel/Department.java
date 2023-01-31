package com.star.springbeandemo.zhoenjiemodel;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-29 16:30
 *
 * <p>
 *     被中介管理接口
 */
public interface Department {

    void selfAction(); //做本部门的事情
    void outAction();  //向总经理发出申请
}
