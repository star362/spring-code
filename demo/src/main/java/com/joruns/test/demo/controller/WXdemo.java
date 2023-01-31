package com.joruns.test.demo.controller;


import com.joruns.test.demo.util.CheckUtil;
import com.joruns.test.demo.util.MessageUtil;
import com.joruns.test.demo.util.TextMessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@RequestMapping("/weixin")
public class WXdemo {

    @GetMapping("/demo")
    public void login(HttpServletRequest request, HttpServletResponse response){
        System.out.println("success");
        String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串
        PrintWriter out = null;

        System.out.println("signature"+signature);
        System.out.println("timestamp"+timestamp);
        System.out.println("nonce"+nonce);
        System.out.println("echostr"+echostr);

        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                out.write(echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }

    }


    @PostMapping(value = "/demo")
    public void dopost(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("success");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");

        String message = null;
        //处理文本类型,回复用户输入的内容
        if ("text".equals(MsgType)) {
            TextMessageUtil textMessage = new TextMessageUtil();
            message = textMessage.initMessage(FromUserName, ToUserName, Content);
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.close();

    }
}
