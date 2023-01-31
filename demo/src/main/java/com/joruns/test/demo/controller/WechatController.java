package com.joruns.test.demo.controller;

import com.joruns.test.demo.beans.WeChatJSConfig;
import com.joruns.test.demo.beans.WechatTokenMessage;
import com.joruns.test.demo.service.serviceimp.WechatTokenserviceimp;
import com.joruns.test.demo.util.AccessTokenUtil;
import com.joruns.test.demo.util.enums.WechatTokenEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * jssdk 获取签名返回
 */
@RestController
public class WechatController {

    @Autowired
    WechatTokenserviceimp wechatTokenserviceimp;

    @GetMapping("/getwechatconfig")
    public WeChatJSConfig weChatConfig(String url){
        WeChatJSConfig weChatJSConfig= wechatTokenserviceimp.getWeChatJSConfig(url);
        return weChatJSConfig;

    }
}
