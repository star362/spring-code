package com.joruns.test.demo.dao;

import com.joruns.test.demo.beans.City;
import com.joruns.test.demo.beans.Province;
import com.joruns.test.demo.beans.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {


    List<User> selAll();

    /**
     * 插入用户表
     */
    int insertuser(User user);

    /**
     * 批量插入用户表
     */
    int insertuserlist(List<User> users);

    /**
     * 批量查询 指定
     */
    List<User> seleuserlisr(Integer[] integ);

    List<Province> selectdoubletable(Integer id);


    List<Province> selectsheng(Integer id);


    List<City> selectdoubletable1(Integer id);


}
