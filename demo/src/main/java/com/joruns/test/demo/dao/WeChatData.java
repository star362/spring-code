package com.joruns.test.demo.dao;

import com.joruns.test.demo.beans.WechatTokenMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WeChatData {

    List<WechatTokenMessage> getAccessToken(Integer type);

    int insertAccessToken(WechatTokenMessage wechatTokenMessage);

    int updateAccessToken(WechatTokenMessage wechatTokenMessage);



}
