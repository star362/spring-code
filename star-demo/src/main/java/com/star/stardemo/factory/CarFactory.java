package com.star.stardemo.factory;

import com.star.stardemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author wangyu
 * @date: 2019-11-22 14:30
 * @describe: 设置容器工厂 动态获取 容器
 */
@Component
public class CarFactory {

    @Autowired
    private Map<String, CarService> carServiceMaps;

    @Autowired
    private List<CarService> carServiceList;


    /**
     * 获取 单个 bean
     */
    public CarService getSerivce(String name) {
        if (!carServiceMaps.containsKey(name)) {
            throw new RuntimeException("获取汽车实例错误不存在:" + name);
        }
        return carServiceMaps.get(name);
    }

    /**获取相应 bean 的方法*/
    public void getBeanFunction(String beanname, String functionsname) {
        getSerivce(beanname).apply(functionsname);
    }

    public void statePattern(String functionsname) {
        carServiceList.stream().forEach(c -> c.apply(functionsname));
    }


    public Map<String, CarService> getCarServiceMaps() {
        return carServiceMaps;
    }

    public List<CarService> getCarServiceList() {
        return carServiceList;
    }


}
