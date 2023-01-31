package com.joruns.test.demo.controller;
import com.joruns.test.demo.beans.City;
import com.joruns.test.demo.beans.Province;
import com.joruns.test.demo.beans.User;
import com.joruns.test.demo.service.serviceimp.Serviceimp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private Serviceimp serviceimp;

    @RequestMapping("/sel")
    public List<User> selAll(){
        return  serviceimp.selAll();

    }

    /**
     * 插入用户表
     */
    @RequestMapping("/insertuser")
    public int insertuser() throws ParseException {
        Float fl= Float.valueOf(12);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String str="1017-10-10";
        Date d1=  format1.parse(str);
        User user=new User(5,"王大夫",fl,null);

        System.out.println("--------------"+user.toString());
         serviceimp.insertuser(user);
        System.out.println("--------------"+user.toString());
        return 0;
    };

    @RequestMapping("/insertuserlist")
    public  void insertuserlist(){
        List<User> lists=new ArrayList<User>();
        User user;
        for(int i=0;i<10;i++){
            user=new User();
            user.setUAGE((float) i);
            user.setUNAME(""+i);
            user.setCOMMDATE(null);
            lists.add(user);
        }
        serviceimp.insertuserlist(lists);

    }

    @RequestMapping("/seleuserlisr")
    public List<User> seleuserlisr(){
        Integer[] integ={1112,1113,1114,1115,1116,1117};
       return serviceimp.seleuserlisr(integ);
    }

    @RequestMapping("/selectdoubletable")
    public List<Province> selectdoubletable(){
        List<Province> list=serviceimp.selectdoubletable(1);
        for(Province prov:list){
                System.out.println(prov.toString());
        }

        return list;
    };
    @RequestMapping("/selectsheng")
    public List<Province> selectsheng(){
        List<Province> list=serviceimp.selectsheng(1);
        for(Province prov:list){
                System.out.println(prov.toString());
        }

        return list;
    };

    @RequestMapping("/selectdoubletable1")
    public List<City> selectdoubletable1(){
        List<City> list=serviceimp.selectdoubletable1(1);
        for(City prov:list){
                System.out.println(prov.toString());
        }

        return list;
    };

    @PostMapping("/shangchuan")
    public void shangchuan(@RequestParam("file") MultipartFile fileList){
        System.out.println("============");
        System.out.println(fileList);

        System.out.println("============");
    }


}
