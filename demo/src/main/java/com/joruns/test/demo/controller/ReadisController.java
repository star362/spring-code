package com.joruns.test.demo.controller;


import com.joruns.test.demo.beans.Town;
import com.joruns.test.demo.config.JedisConfig;
import com.joruns.test.demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;

@RestController
public class ReadisController {

    @Autowired
    JedisPool jedisPool;

    @Autowired
    JedisUtil jedisUtil;

    @RequestMapping("/readis/demo")
//    @Cacheable( key = "#town.id" ,value="town")
    public void test(Town town){

//        Jedis jedis = new Jedis("localhost",6379);
//        jedis.auth("123456");//验证密码
        //设置 redis 字符串数据
//        Jedis jedis = jedisPool.getResource();
//        System.out.println("连接成功");
//        jedis.set("runoobkey", town.getId());
//        // 获取存储的数据并输出
//        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
//
//        jedis.close();


    }
}
