package com.nacos.controller;

import com.nacos.config.SpringBeanUtils;
import com.nacos.controller.aop.CostTime;
import com.nacos.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: AopTest
 * @Description:
 * @date: 2019-10-29 13:46
 * @describe:
 */
@RestController
public class AopTest {

    @Autowired
    private UserService userService;


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private DataSource dataSource;


    @CostTime("B")
    @GetMapping("aoptest")
    public void aopTest(String age, String id) {
        System.out.println("=====aoptest=======");
        throw new RuntimeException("BBBBBBBBBBBBBBBBBB");
    }


    @CostTime("A")
    @GetMapping("aoptest2")
    public String[] aopTest2(String age, String id) {
        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }


    @CostTime("B")
    @GetMapping("aoptest3")
    public String[] aoptest3(String age, String id) {
        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }

    @GetMapping("aoptest4")
    public String[] aoptest4() {
        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }


    @Transactional
    @GetMapping("/b")
    public String[] b() {
//        HashMap map = new HashMap<>();
//        map.put("age", "59");
//        map.put("id", 59);
//        map.put("name", "bbbb");
//      sqlSessionTemplate.selectOne("com.nacos.dao.UserDao.findByAgeOrId", map);

       /* final SqlSession sqlSession = sqlSessionFactory.openSession(true);
        starUser user = sqlSession.selectOne("com.nacos.dao.UserDao.findByAgeOrId", map);
        System.out.println(user);
        sqlSession.close();*/

//        final int update = sqlSessionTemplate.update("com.nacos.dao.UserDao.updateuser", map);
/*
        final UserDao mapper = sqlSessionTemplate.getMapper(UserDao.class);
        final List<starUser> starUsers = mapper.selAll();
        starUsers.stream().forEach(System.out::println);*/


        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }

    @GetMapping("/c")
    public void c() {
        HashMap map = new HashMap<>();
        map.put("age", "59");
        map.put("id", 59);
        map.put("name", "bbbb");
        final SqlSession sqlSession = sqlSessionFactory.openSession();
//        final List<starUser> o = sqlSession.selectList("com.nacos.dao.UserDao.findByAgeOrId", map);
//        System.out.println("0000000" + o.toString());


    }


    @GetMapping("/")
    public void d() {


    }



}
