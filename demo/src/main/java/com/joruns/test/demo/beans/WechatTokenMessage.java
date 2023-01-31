package com.joruns.test.demo.beans;

public class WechatTokenMessage {
    private Integer id;
    private String message;
    private Integer type;

    public WechatTokenMessage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WechatTokenMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", type=" + type +
                '}';
    }
}
