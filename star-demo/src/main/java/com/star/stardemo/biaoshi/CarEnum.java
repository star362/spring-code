package com.star.stardemo.biaoshi;

import com.star.stardemo.service.CarService;
import com.star.stardemo.service.impl.BaoCarSerivceImpl;
import com.star.stardemo.service.impl.BenCarSerivceImpl;

/**
 * @author wangyu
 * @date: 2019-11-22 16:18
 * @describe:
 */
public enum CarEnum {

    /**
     *
     */
    BAO("baoCarSerivceImpl", BaoCarSerivceImpl.class, CarService.class, "宝马"),
    /**
     *
     */
    BEN("benCarSerivceImpl", BenCarSerivceImpl.class, CarService.class, "奔驰");


    private String functionBeanName;
    private Class<?> clazzImpl;
    private Class<CarService> clazz;
    private String describe;


    CarEnum(String functionBeanName, Class<? extends CarService> clazzImpl, Class<CarService> clazz, String describe) {
        this.functionBeanName = functionBeanName;
        clazzImpl = clazzImpl;
        clazz = clazz;
        this.describe = describe;
    }

    public String getFunctionBeanName() {
        return functionBeanName;
    }


    public Class<?> getClazzImpl() {
        return clazzImpl;
    }

    public Class<CarService> getClazz() {
        return clazz;
    }

    public String getDescribe() {
        return describe;
    }


}
