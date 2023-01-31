package com.joruns.test.demo.service;

import com.joruns.test.demo.beans.City;
import com.joruns.test.demo.beans.Province;
import com.joruns.test.demo.beans.User;

import java.util.List;

public interface UserService {

    public List<User> selAll();

    /**
     * 插入用户表
     */
    public int insertuser(User user);
    /**
     * 批量插入用户表
     */
    public int insertuserlist(List<User> users);

    /**
     *批量查询 指定
     */
    public List<User> seleuserlisr(Integer[] integ);

    public List<Province> selectdoubletable(Integer id);


    public List<Province> selectsheng(Integer id);




    public List<City> selectdoubletable1(Integer id);

}
