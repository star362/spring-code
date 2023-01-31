package com.joruns.test.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alibaba")
//@Configuration
public class Alibaba {
    // 商户appid
//    @Value("{alibaba.appid}")
    public  String appid;
    // 私钥 pkcs8格式的
//    @Value("{alibaba.RsaPrivateKey}")
    public  String RsaPrivateKey ;
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    @Value("{alibaba.notifyUrl}")
    public  String notifyUrl ;
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
//    @Value("{alibaba.returnUrl}")
    public  String returnUrl ;
    // 请求网关地址
//    @Value("{alibaba.url}")
    public  String url;
    // 编码
//    @Value("{alibaba.charset}")
    public  String charset ;
    // 返回格式
//    @Value("{alibaba.format}")
    public  String format ;
    // 支付宝公钥
//    @Value("{alibaba.AlipayPublicKey}")
    public  String AlipayPublicKey ;
    // 日志记录目录
//    @Value("{alibaba.logPath}")
    public  String logPath ;
    // RSA2
//    @Value("{alibaba.signtype}")
    public  String signtype ;
}
