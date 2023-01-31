package com.star.stardemo.service.impl;

import com.star.stardemo.service.CarService;
import com.star.stardemo.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangyu
 * @date: 2019-12-21 17:13
 * @describe:
 */
public class OtherServiceImpl implements OtherService, CarService {

    @Autowired
    CarService carService;

    @Override
    public void otherfa() {
        System.out.println("OtherServiceImplf方法");
    }

    @Override
    public void apply(String name) {
        carService.apply(name);
        otherfa();
    }
}
