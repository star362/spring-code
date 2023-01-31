package com.star.stardemo.event;

import com.star.stardemo.dataobject.StarUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangyu
 * @date: 2020-01-10 15:01
 * @describe:
 */
@Slf4j
@Component
@Order(0)
public class SmsSendListener {
    @EventListener
    public void onApplicationEvent(SendEvent sendEvent) {
        SendEvent event = sendEvent;
        log.info("SmsSendListener向[{}]发送了短信 thread[{}]", ((StarUser)event.getSource()).toString(), Thread.currentThread().getName());
    }


}