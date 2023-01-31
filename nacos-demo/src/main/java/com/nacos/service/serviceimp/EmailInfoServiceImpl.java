package com.nacos.service.serviceimp;

import com.nacos.dao.EmailInfoDao;
import com.nacos.repistory.EmailInfo;
import com.nacos.service.EmailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: EmailInfoServiceImpl
 * @Description:
 * @date: 2019-10-19 19:37
 * @describe:
 */
@Service
@Primary
public class EmailInfoServiceImpl implements EmailInfoService {



    @Autowired
    private EmailInfoDao emailInfoDao;


    @Override
    public List<EmailInfo> selAll() {
        return emailInfoDao.selAll();
    }

    @Override
    public List<EmailInfo> findfirstEmailInfo(EmailInfo emailInfo) {
        return emailInfoDao.findfirstEmailInfo(emailInfo);
    }

    @Override
    public String a() {
        return "EmailInfoServiceImpl";
    }


}
