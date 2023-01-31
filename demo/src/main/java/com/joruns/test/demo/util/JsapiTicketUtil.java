package com.joruns.test.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.joruns.test.demo.constant.WeChatBasicsConstant;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class JsapiTicketUtil {


    public String getTicket(){
        RestTemplate resttemplate=new RestTemplate();
//  准备格式化参数
        Map<String, String> map = new HashMap<>();
        map.put("access_token", WeChatBasicsConstant.WECHAT_GETTOKEN_GRANT);

//  格式化提交地址
        ResponseEntity<String> response = resttemplate.exchange(WeChatBasicsConstant.WECHAT_TICKET_URL , HttpMethod.GET,null , String.class, map);
//        ResponseEntity<String> response = resttemplate.postForEntity(WeChatBasicsConstant.WECHAT_GETTOKEN_URL ,null, String.class, map);

        JSONObject jsonObject=JSONObject.parseObject(response.getBody());
        String token=jsonObject.getString("access_token");
        System.out.println("response.getBody():"+response.getBody());
        System.out.println("access_token:"+token);
        Integer expires_in=jsonObject.getInteger("expires_in");
        System.out.println("expires_in:"+expires_in);
        return token;
    }

}
