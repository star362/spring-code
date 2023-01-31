package com.star.stardemo.controller;

import com.star.stardemo.biaoshi.SpringBeanUtils;
import com.star.stardemo.dataobject.StarUser;
import com.star.stardemo.event.SendEvent;
import com.star.stardemo.event.MyEventPublish;
import com.star.stardemo.factory.CarFactory;
import com.star.stardemo.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author wangyu
 * @date: 2019-11-22 14:28
 * @describe:
 */
@RestController
public class CarController {

    private static final Logger log = LoggerFactory.getLogger(CarController.class);


    @Autowired
    CarFactory carFactory;


    @Autowired
    CarService carService;

    @Autowired
    private List<CarService> carServiceList;

    /***
     *  容器事件由容器触发
     */
    @Autowired
    private MyEventPublish myEventPublish;


    @GetMapping("/a")
    public void a(String name) {


        final Iterator<CarService> iterator2 = carServiceList.iterator();
        while (iterator2.hasNext()) {
            final CarService next = iterator2.next();
            next.apply("====");
        }


    }


    @GetMapping("/b")
    public void b(String name)  {

        log.info("======");

        System.out.println("rearrange code");

    }


  /*  @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/d")
    public List<starUser> d()  {


            String sql = "select id id,user_name userName,user_age userAge,user_sex userSex from star_user where id=? limit 1";
            List<starUser> users = jdbcTemplate.query(sql, new Object[] {1},new BeanPropertyRowMapper<starUser>(starUser.class));
            users.forEach(System.out::println);

        return users;
    }*/

    @GetMapping("/c")
    public String[] c() {

        return SpringBeanUtils.getContext().getBeanDefinitionNames();

    }

    @GetMapping("/e")
    public void e() {
//        StopWatch a=new StopWatch();
//        a.start("开始");
        log.info(Thread.currentThread().getName());

        StarUser starUser = new StarUser();
        starUser.setId(66);
        starUser.setUserAge(22);
        starUser.setUserName("test");
        starUser.setUserSex("女");
        SendEvent event = new SendEvent(starUser);
        myEventPublish.publish(event);
//        a.stop();
//        log.info(a.prettyPrint());

    }

    @GetMapping("/f")
    public String StarUser() throws ClassNotFoundException, InterruptedException {
        final Class<?> aClass = Class.forName("com.star.stardemo.service.impl.BaoCarSerivceImpl");
        final CarService beanOfClass = (CarService) SpringBeanUtils.getBeanOfClass(aClass);
        beanOfClass.apply(": ffffffff");
        int i = 0;
        while (i < 20) {
            Thread.sleep(1000);
            Random random = new Random();
            random.nextInt(10);
            System.out.println("====" + i);
            i++;
        }
        return "success";
    }


    @GetMapping("/fg")
    public String StarUser2(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        log.error("=====[{}]===", ip);
        return ip;
    }


    @GetMapping("/out")
    public String StarUser3(HttpServletRequest request) throws InterruptedException {


        int i = 0;
        while (i < 20) {
            Thread.sleep(1000);
            Random random = new Random();
            random.nextInt(10);
            System.out.println("====" + i);
            i++;
        }


        RestTemplate restTemplate = new RestTemplate();
//        HttpClient httpClient = new HttpClient();


        return "success";
    }


}
