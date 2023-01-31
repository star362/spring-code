package com.star.stardemo.event;

import com.star.stardemo.dataobject.StarUser;
import org.springframework.context.ApplicationEvent;

/**
 * @author wangyu
 * @date: 2020-01-10 15:01
 * @describe:
 */
public class SendEvent extends ApplicationEvent {
    /**
     *
     */
    private static final long serialVersionUID = -8818206490285815283L;

    /**
     * 目的地
     */
    public SendEvent(StarUser source) {
        super(source);
    }


}