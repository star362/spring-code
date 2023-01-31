package com.star.stardemo.service.impl;

import com.star.stardemo.service.CarService;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author wangyu
 * @date: 2019-11-22 14:25
 * @describe:
 */
@Service
@Primary
public class BaoCarSerivceImpl implements CarService {


    @Override
    public void apply(@NonNull String name) {
        System.out.println("BaoCarSerivceImpl: " + name);
    }


}
