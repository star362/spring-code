package com.joruns.test.demo.service.serviceimp;

import com.joruns.test.demo.beans.WeChatJSConfig;
import com.joruns.test.demo.beans.WechatTokenMessage;
import com.joruns.test.demo.dao.WeChatData;
import com.joruns.test.demo.service.WechatTokenService;
import com.joruns.test.demo.util.AccessTokenUtil;
import com.joruns.test.demo.util.enums.WechatTokenEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WechatTokenserviceimp implements WechatTokenService {

    @Autowired
    WeChatData weChatData;

    @Override
    public List<WechatTokenMessage> getAccessToken(Integer type) {
        return weChatData.getAccessToken(type);
    }




    //获取签名
    public WeChatJSConfig getWeChatJSConfig(String url) {
        AccessTokenUtil gettoken=new AccessTokenUtil();
        List<WechatTokenMessage> wechatTokenMessages=weChatData.getAccessToken(WechatTokenEnum.JSAPI_TICKET.getCode());//获取 jsapi
          String jsapi= wechatTokenMessages.get(0).getMessage();
        WeChatJSConfig weChatJSConfig=gettoken.getsignature(jsapi,url);
        return  weChatJSConfig;
    }





//    @Scheduled(fixedRate = 7000000 ) //定时更新 token
    public void resetAccessTokenInfo() {
        AccessTokenUtil gettoken=new AccessTokenUtil();
        WechatTokenMessage wechatTokenMessage= gettoken.getAccToken();
        if(weChatData.getAccessToken(WechatTokenEnum.ACCESS_TOKEN.getCode()).size()>0){
            weChatData.updateAccessToken(wechatTokenMessage);
        }else{
            weChatData.insertAccessToken(wechatTokenMessage);
        }

    }

//        @Scheduled(fixedRate = 7100000 ) //定时更新 tickent 应该比 token 时间大一点
    public void resetJsApitickentInfo() {
        AccessTokenUtil gettoken=new AccessTokenUtil();
        String token=null;
       //获取 token
        List<WechatTokenMessage> wechatTokenMessages= weChatData.getAccessToken(WechatTokenEnum.ACCESS_TOKEN.getCode());
        if(wechatTokenMessages.size()>0){
            token=wechatTokenMessages.get(0).getMessage();
        }else{
            WechatTokenMessage wechatTokenMessage= gettoken.getAccToken();
            token=wechatTokenMessage.getMessage();
            weChatData.insertAccessToken(wechatTokenMessage);
        }

        WechatTokenMessage wechatTokenMessage= gettoken.getJsApiTickent(token);
        if(weChatData.getAccessToken(WechatTokenEnum.JSAPI_TICKET.getCode()).size()>0){
            weChatData.updateAccessToken(wechatTokenMessage);
        }else{
            weChatData.insertAccessToken(wechatTokenMessage);
        }

    }
}
