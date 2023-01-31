package com.star.stardemo.event;

import com.star.stardemo.dataobject.StarUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangyu
 * @date: 2020-01-10 15:01
 * @describe:
 */
@Slf4j
@Component
@Order(1)
public class MailSendListener implements ApplicationListener<SendEvent> {

    @Override
    public void onApplicationEvent(SendEvent sendEvent) {
        SendEvent event = sendEvent;
        log.info("MailSender向[{}]发送了邮件 thread[{}]", ((StarUser)event.getSource()).toString(), Thread.currentThread().getName());
    }


}