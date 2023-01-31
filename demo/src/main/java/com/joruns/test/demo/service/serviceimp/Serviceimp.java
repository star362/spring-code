package com.joruns.test.demo.service.serviceimp;

import com.joruns.test.demo.beans.City;
import com.joruns.test.demo.beans.Province;
import com.joruns.test.demo.beans.User;
import com.joruns.test.demo.dao.UserDao;
import com.joruns.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Serviceimp implements UserService {
    /**
     * @Resource
     */
    @Autowired(required=false)
    private UserDao userDao;

    @Override
    public List<User> selAll() {
        return userDao.selAll();
    }

    /**
     * 插入用户表
     */
    public int insertuser(User user) {
        return userDao.insertuser(user);
    }

    ;

    /**
     * 批量插入用户表
     */
    public int insertuserlist(List<User> users) {
        return userDao.insertuserlist(users);
    }

    ;

    /**
     * 批量查询 指定
     */
    public List<User> seleuserlisr(Integer[] integ) {
        return userDao.seleuserlisr(integ);
    }

    ;

    public List<Province> selectdoubletable(Integer id) {
        return userDao.selectdoubletable(id);
    }

    ;

    public List<Province> selectsheng(Integer id) {
        return userDao.selectsheng(id);
    }

    ;


    public List<City> selectdoubletable1(Integer id) {
        return userDao.selectdoubletable1(id);
    }

    ;
}
