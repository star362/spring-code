package com.joruns.test.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.joruns.test.demo.beans.WeChatJSConfig;
import com.joruns.test.demo.beans.WechatTokenMessage;
import com.joruns.test.demo.constant.WeChatBasicsConstant;
import com.joruns.test.demo.util.enums.WechatTokenEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取 token
 */
public class AccessTokenUtil {

//    @Autowired
//    private RestTemplate resttemplate;

    /**
     * 获取 token
     * @return
     */
    public WechatTokenMessage getAccToken(){
        RestTemplate resttemplate=new RestTemplate();
//  准备格式化参数
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", WeChatBasicsConstant.WECHAT_GETTOKEN_GRANT);
        map.put("appid", WeChatBasicsConstant.WECHAT_appID);
        map.put("secret", WeChatBasicsConstant.WECHAT_appsecret);
//  格式化提交地址
        ResponseEntity<String> response = resttemplate.exchange(WeChatBasicsConstant.WECHAT_GETTOKEN_URL , HttpMethod.GET,null , String.class, map);
//        ResponseEntity<String> response = resttemplate.postForEntity(WeChatBasicsConstant.WECHAT_GETTOKEN_URL ,null, String.class, map);

        JSONObject jsonObject=JSONObject.parseObject(response.getBody());
        String token=jsonObject.getString("access_token");
        System.out.println("response.getBody():"+response.getBody());
//        System.out.println("access_token:"+token);
//        Integer expires_in=jsonObject.getInteger("expires_in");
//        System.out.println("expires_in:"+expires_in);

        WechatTokenMessage wechatTokenMessage=new WechatTokenMessage();
        wechatTokenMessage.setMessage(token);
        wechatTokenMessage.setType(WechatTokenEnum.ACCESS_TOKEN.getCode());
        return wechatTokenMessage;
    }

    /**
     * 获取 jssdktick
     * @param token
     * @return
     */
    public WechatTokenMessage getJsApiTickent(String token){
        RestTemplate resttemplate=new RestTemplate();
//  准备格式化参数
        Map<String, String> map = new HashMap<>();
        map.put("access_token",token);
//  格式化提交地址
        ResponseEntity<String> response = resttemplate.exchange(WeChatBasicsConstant.WECHAT_TICKET_URL , HttpMethod.GET,null , String.class, map);
//        ResponseEntity<String> response = resttemplate.postForEntity(WeChatBasicsConstant.WECHAT_GETTOKEN_URL ,null, String.class, map);

        JSONObject jsonObject=JSONObject.parseObject(response.getBody());
        System.out.println("response.getBody():"+response.getBody());
        String ticket=jsonObject.getString("ticket");

        WechatTokenMessage wechatTokenMessage=new WechatTokenMessage();
        wechatTokenMessage.setMessage(ticket);
        wechatTokenMessage.setType(WechatTokenEnum.JSAPI_TICKET.getCode());
        return wechatTokenMessage;
    }
    /**
     * 生成签名 返回 config
     */
    public WeChatJSConfig getsignature(String jsapi,String url){
        WeChatJSConfig weChatJSConfig=new WeChatJSConfig();
        weChatJSConfig.setAppId(WeChatBasicsConstant.WECHAT_appID);
        weChatJSConfig.setNonceStr(WeChatBasicsConstant.WECHAT_NONCESTR);
        weChatJSConfig.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));

        String string1="jsapi_ticket="+jsapi+"&noncestr="+WeChatBasicsConstant.WECHAT_NONCESTR+"&timestamp="+weChatJSConfig.getTimestamp()+"&url="+url;

        System.out.println("=========string1==="+string1);
        String signature=SHA1.encode(string1);
        weChatJSConfig.setSignature(signature);
        System.out.println("=========signature==="+signature);
        return weChatJSConfig;
    };





}
