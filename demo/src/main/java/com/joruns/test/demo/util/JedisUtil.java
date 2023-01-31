package com.joruns.test.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.SocketTimeoutException;
import java.util.Set;

/**
 * Created by 王小楠/王涵禹
 * Date: 2018/8/7 0007
 */
@Component
//@Slf4j
public class JedisUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    private static JedisPool jedisPool = null;

    private static volatile Jedis jedis = null;
    public JedisUtil(){}

    public static Jedis getJedis(){
        int timeoutCount = 0;
        if (jedis ==null){
            synchronized (Jedis.class){
                if (jedis ==null){
                    try {
                        jedis = getJedisPool().getResource();
                    }
                    catch(Exception e){
                        // 底层原因是SocketTimeoutException，不过redis已经捕捉且抛出JedisConnectionException，不继承于前者
                        if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException)
                        {
                            timeoutCount++;
//                            log.warn("getJedis timeoutCount={}", timeoutCount);
                            System.out.println("getJedis timeoutCount={}"+ timeoutCount);
                            if (timeoutCount > 3)
                            {
                                //return ;
//                                log.error("底层原因是SocketTimeoutException error", e);
                                System.out.println("底层原因是SocketTimeoutException error"+ e);
                            }
                        }else
                        {

//                            log.error("getJedis error", e);
                            System.out.println("getJedis error"+ e);
                            //break;
                        }
                    }
                }
            }
        }
        return jedis;
    }

    public static JedisPool getJedisPool(){
        if (jedisPool ==null){
            synchronized (JedisPool.class){
                if (jedisPool==null){
                    jedisPool = applicationContext.getBean(JedisPool.class);

                }
            }
        }
        return jedisPool;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if(JedisUtil.applicationContext == null){
            JedisUtil.applicationContext  = applicationContext; //初始化 spring applicationContext
        }
    }



    public static byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        try {
         System.out.println("====jedis--------key----====="+      (key));
         System.out.println("====jedis--------value----====="+SerializationUtils.deserialize(value));
         jedis.set(key,value);
         return value;
        } finally {
            //jedis.close();
        }
    }

    public static String set(String key, String value,JedisPool jedisPool2) {
        Jedis jedis =jedisPool2.getResource();
        try {
            System.out.println("====jedis--------key----====="+      (key));
//            System.out.println("====jedis--------value----====="+SerializationUtils.deserialize(value));
            System.out.println("====jedis--------value----====="+value);
            jedis.set(key,value);
            return value;
        } finally {
            //jedis.close();
        }
    }

    public static void expire(byte[] key, int i) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key,i);
        } finally {
            //jedis.close();
        }
    }

    public static byte[] get(byte[] sessionId) {
        Jedis jedis = getJedis();
        if(sessionId == null){
            return null;
        }
        System.out.println("-----------------------"+sessionId);
        try {
            byte[] value = jedis.get(sessionId);
            return value;
        } finally {
//            jedis.close();
        }
    }

    public static void delete(byte[] key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } finally {
            //jedis.close();
        }
    }

    public static Set<byte[]> keys(String predix) {
        Jedis jedis = getJedis();
        try {
            return jedis.keys((predix+"*").getBytes());
        } finally {
            //jedis.close();
        }
    }
}
