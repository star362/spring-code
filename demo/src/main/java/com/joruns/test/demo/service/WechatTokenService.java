package com.joruns.test.demo.service;

import com.joruns.test.demo.beans.WechatTokenMessage;

import java.util.List;

public interface WechatTokenService {



    List<WechatTokenMessage> getAccessToken(Integer type);


}
