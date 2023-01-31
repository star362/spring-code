package com.joruns.test.demo.util;

import com.joruns.test.demo.beans.MessageText;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

public class TextMessageUtil implements BaseMessageUtil<MessageText> {
    //添加封装发送消息的方法，重载，将内容传入
    @Override
    public String initMessage(String FromUserName, String ToUserName, String Content) {
//        MessageText text = new MessageText();
//        text.setToUserName(FromUserName);
//        text.setFromUserName(ToUserName);
//        text.setContent("您输入的内容是：" + Content);
//        text.setCreateTime(new Date().getTime());
//        text.setMsgType("text");
//        return messageToxml(text);
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("王大夫的公众号!!!!!");
        text.setCreateTime(new Date().getTime());
        text.setMsgType("text");
        return  messageToxml(text);
    }


    @Override
    public String messageToxml(MessageText messageText) {
        XStream xstream  = new XStream();
        xstream.alias("xml", messageText.getClass());
        return xstream.toXML(messageText);
    }


}

