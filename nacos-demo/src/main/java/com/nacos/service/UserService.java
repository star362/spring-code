package com.nacos.service;

import com.nacos.config.SpringBeanUtils;
import com.nacos.dao.UserDao;

import com.nacos.repistory.StarUser;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {


    List<StarUser> selAll();

    Optional<StarUser> findById(Integer id);

    void say(Integer id);


    default StarUser findByLikeName(String name) {

        final SqlSessionTemplate sqlsession = SpringBeanUtils.getBeanOfClass(SqlSessionTemplate.class);
//        starUser user = sqlsession.selectOne("com.nacos.dao.UserDao.findByLikeName1", name);
        final UserDao mapper = sqlsession.getMapper(UserDao.class);
        final Map map = mapper.findByLikeNanme("三", 1);
//        final Supplier<List<starUser>> selAll = mapper::selAll;

        return null;
    }




}
