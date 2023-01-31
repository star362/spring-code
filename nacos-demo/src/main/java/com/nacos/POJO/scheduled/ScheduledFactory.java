package com.nacos.POJO.scheduled;

import com.nacos.repistory.EmailInfo;
import com.nacos.service.EmailInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: ScheduledFactory
 * @Description:
 * @date: 2019-10-18 15:40
 * @describe:
 */
@Slf4j
@Component
public class ScheduledFactory {


    @Autowired
    @Qualifier("javaMailSender")
    private MailSender javaMailSender;

    @Autowired
    private EmailInfoService emailInfoService;

//    @Scheduled(cron="0/1 * * * * ?")
    private void configureTasks() {
//        System.err.format("执行静态定时任务时间: %tF %tT%n" , LocalDateTime.now(),LocalDateTime.now());
    }


//    @Scheduled(cron="0/10 * * * * ?")
    @Scheduled(cron="0 0 8 ? * MON-FRI")
    public void sendSimpleMail() {
        String format = String.format("%tF %tT", LocalDateTime.now(), LocalDateTime.now());
        try {
            EmailInfo emailInfo = new EmailInfo();
            emailInfo.setToEmail("wangyu");
            emailInfo.setState(1);
            emailInfo.setDepa("billing");
            final List<EmailInfo> emailInfoList = emailInfoService.findfirstEmailInfo(emailInfo);
            emailInfoList.stream().forEach(e->{
                // 构造Email消息
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("star362@163.com");
//                message.setFrom("wangyu");
                message.setTo(e.getToEmail());
                String sub=new String(e.getSubject());
                String concat = sub.concat(format);
                message.setSubject(concat);
                message.setText(e.getRemarks());
                javaMailSender.send(message);
            });
            log.error("定时邮件发送成功[{}]", format);
        } catch (Exception e) {
            log.error("定时邮件发送失败[{}]", e.getMessage());
            e.printStackTrace();
        }
    }


}
