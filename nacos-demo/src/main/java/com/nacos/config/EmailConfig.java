package com.nacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author WANGYU
 * @ProjectName nacos-boot
 * @Class: EmailConfig
 * @Description:
 * @date: 2019-10-19 18:48
 * @describe:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;

    @Bean
    public MailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        return javaMailSender;
    }
}
