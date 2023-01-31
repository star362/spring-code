package com.nacos.controller;


import com.nacos.POJO.VO.ResultVO;
import com.nacos.POJO.util.ResultVOUtil;
import com.nacos.repistory.EmailInfo;
import com.nacos.service.EmailInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: NacosTest
 * @Description:
 * @date 2019/8/28 21:50
 * @描述
 */
@RestController
@Slf4j
@Api(tags = "邮件")
public class NacosTest {


    @Resource(name = "emailInfoServiceImpl")
    private EmailInfoService emailInfoService;


    @Autowired
    @Qualifier("javaMailSender")
    private MailSender javaMailSender;




   /* @ApiOperation(value="使用 163 发送邮件", notes="id=1" )
    @PostMapping("/sendMailstar")
    public void sendSimpleMailstar(MailBean mailBean) {
        try {
            // 构造Email消息
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("star362@163.com");
            message.setTo(mailBean.getRecipient());
            message.setSubject(mailBean.getSubject());
            message.setText(mailBean.getContent());
            javaMailSender.send(message);
            log.info("邮件发送成功sendSimpleMailstar[{}] datetime[{}]",mailBean.toString(), LocalDateTime.now());
        } catch (Exception e) {
            log.error("邮件发送失败[{}]", e.getMessage());
        }
    }*/

   @GetMapping("/test")
   public ResultVO test(){
       return ResultVOUtil.success(emailInfoService.selAll());
   }



    /**
     * 发送单人
     *
     * @param mailBean
     */
    @ApiOperation(value = "使用 wangyu 发送邮件", notes = "发送单人")
    @PostMapping("/sendEmail")
    public ResultVO sendSimpleMail(HttpServletRequest req, HttpServletResponse rep, EmailInfo mailBean) {
        try {

            // 构造Email消息
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("wangyu");
            message.setTo(mailBean.getToEmail());
            message.setSubject(mailBean.getSubject());
            message.setText(mailBean.getRemarks());
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("邮件发送失败[{}]", e.getMessage());
        }
        return ResultVOUtil.success();
    }

    /*@ApiOperation(value = "使用 wangyu 发送邮件 可发送不同主题不同内容", notes = "发送多人")
    @PostMapping("/sendEmaillist")
    public ResultVO sendSimpleMailList(@RequestBody JSONObject bean) {
        JsonBean jsonBean = JSON.parseObject(bean.toJSONString(), JsonBean.class);//最外层 bean
        List<EmailInfo> mailBeanList = JSON.parseArray(jsonBean.getBean().toString(), EmailInfo.class);
        mailBeanList.stream().forEach(m -> {
            try {
                // 构造Email消息
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("wangyu");
                message.setTo(m.getToEmail());
                message.setSubject(m.getSubject());
                message.setText(m.getRemarks());
                javaMailSender.send(message);
                log.info("邮件发送成功[{}] datetime[{}]", m.toString(), LocalDateTime.now());
            } catch (Exception e) {
                log.error("邮件发送失败[{}]", e.getMessage());
            }
        });
        log.info("sendEmaillist---email全部发送完毕 over [{}]", LocalDateTime.now());
        return ResultVOUtil.success();
    }*/


    @ApiOperation(value = "使用 wangyu 发送账管所有邮件", notes = "账管批量发送")
    @PostMapping("/sendEmailAcc")
    public ResultVO sendEmailAcc(EmailInfo emailInfom) {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setState(1);
        emailInfo.setDepa("billing");
        final List<EmailInfo> emailInfoList = emailInfoService.findfirstEmailInfo(emailInfo);
        List<String> collect = emailInfoList.stream().map(m -> m.getToEmail()).collect(Collectors.toList());
         String[] toEmail=new String[collect.size()];
        collect.toArray(toEmail);
        try {
                // 构造Email消息
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("wangyu");
                message.setTo(toEmail);
                message.setSubject(emailInfom.getSubject());
                message.setText(emailInfom.getRemarks());
                javaMailSender.send(message);
                log.info("sendEmailAcc[{}] datetime[{}]", emailInfom.toString(), LocalDateTime.now());
            } catch (Exception e) {
                log.error("sendEmailAcc邮件发送失败[{}]", e.getMessage());
            }
        return ResultVOUtil.success();
    }


    @ApiOperation(value = "使用 wangyu 发送邮件", notes = "自定义批量发送")
    @PostMapping("/sendEmailother")
    public ResultVO sendEmailother(EmailInfo emailInfom) {
        try {
            // 构造Email消息
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("wangyu");
            message.setTo(emailInfom.getToEmail().split(","));
            message.setSubject(emailInfom.getSubject());
            message.setText(emailInfom.getRemarks());
            javaMailSender.send(message);
            log.info("sendEmailother[{}] datetime[{}]", emailInfom.toString(), LocalDateTime.now());
        } catch (Exception e) {
            log.error("sendEmailother 邮件发送失败[{}]", e.getMessage());
        }
        return ResultVOUtil.success();
    }



    @GetMapping("/a")
    public  void a(){
        System.out.println(emailInfoService.a());
    }










}
