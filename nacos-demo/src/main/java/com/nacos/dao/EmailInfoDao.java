package com.nacos.dao;

import com.nacos.repistory.EmailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: EmailInfoDao
 * @Description:
 * @date: 2019-10-19 19:38
 * @describe:
 */
@Mapper
public interface EmailInfoDao {


    List<EmailInfo> selAll();


    List<EmailInfo> findfirstEmailInfo(EmailInfo emailInfo);
}
