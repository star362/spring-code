package com.star.springbeandemo.config;

import com.star.springbeandemo.zhoenjiemodel.Department;

public interface PayParserFactory {

    /**
     * 服务定位器
     *
     * @param payType 支付类型
     * @return 返回具体的支付处理实现类
     */
    Department getPayParser(String payType);
}
