package com.nacos.service.serviceimp;


import com.nacos.dao.UserDao;

import com.nacos.repistory.StarUser;
import com.nacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Serviceimp implements UserService {
    /**
     * @Resource
     */
    @Autowired
    private UserDao userDao;

    @Override
    public List<StarUser> selAll() {
        return userDao.selAll();
    }

    @Override
    public Optional<StarUser> findById(Integer id){
        return userDao.findById(id);
    }


    @Async
    @Override
    public void say(Integer id){

        System.out.format("异步执行线程名字Serviceimp %s%n",Thread.currentThread().getName());

    }




}
