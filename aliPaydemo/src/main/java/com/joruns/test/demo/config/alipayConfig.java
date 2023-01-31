package com.joruns.test.demo.config;

import com.alipay.api.AlipayClient;

import com.alipay.api.DefaultAlipayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Slf4j
@Component
//@Configuration
public class alipayConfig {
@Autowired
  private Alibaba alibaba;
@Bean
  public AlipayClient alipayClient(){
      AlipayClient alipayClient=new DefaultAlipayClient(alibaba.getUrl(),alibaba.getAppid(),alibaba.getRsaPrivateKey(),alibaba.getFormat(),alibaba.getCharset(),alibaba.getAlipayPublicKey(),alibaba.getSigntype());
    log.info("==========AlipayClient========={}",alibaba.toString());
      return alipayClient;
  }



}
