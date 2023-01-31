package com.star.stardemo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author wangyu
 * @date: 2020-01-13 11:30
 * @describe:
 */
@Component
public class MyEventPublish implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(SendEvent obj) {
        this.publisher.publishEvent(obj);
    }
}
