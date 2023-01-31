package com.joruns.test.demo.constant;

public interface WeChatBasicsConstant {
    //appid
    String WECHAT_appID = "wxe6c07952ca7201a3";
    //appsecret
    String WECHAT_appsecret = "83eb273f30cfca5e0caef474622d8244";
    // nonceStr
    String WECHAT_NONCESTR = "faujor";

    String WECHAT_GETTOKEN_GRANT = "client_credential";
    //获取 token url
    String WECHAT_GETTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
    //获取 jssdkticjet
    String WECHAT_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={access_token}&type=jsapi";


}
