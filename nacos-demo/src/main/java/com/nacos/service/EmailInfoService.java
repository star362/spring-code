package com.nacos.service;

import com.nacos.repistory.EmailInfo;

import java.util.List;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: EmailInfoService
 * @Description:
 * @date: 2019-10-19 19:37
 * @describe:
 */
public interface EmailInfoService {


    List<EmailInfo> selAll();

    List<EmailInfo> findfirstEmailInfo(EmailInfo emailInfo);


    String a();
}
