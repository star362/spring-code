package com.star.stardemo.service.impl;

import com.star.stardemo.service.CarService;
import org.springframework.stereotype.Service;

/**
 * @author wangyu
 * @date: 2019-11-22 14:25
 * @describe:
 */
@Service
public class BenCarSerivceImpl implements CarService {
    @Override
    public void apply(String name) {
        System.out.printf("BenCarSerivceImpl: %s%n", name);
    }



}
