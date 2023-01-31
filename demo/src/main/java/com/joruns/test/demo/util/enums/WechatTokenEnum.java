package com.joruns.test.demo.util.enums;

public enum WechatTokenEnum {
    ACCESS_TOKEN(1,"access_token"),
    JSAPI_TICKET(2,"jsapi_ticket"),

    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    WechatTokenEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
