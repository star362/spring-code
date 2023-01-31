package com.nacos.POJO.enums;

/**
 * @author wangyu
 * @date: 2020-04-25 12:46
 *
 * <p>  </p>
 */
public enum BaseDataResultEnum {
    SUCCESS(0, "成功"),
    FAIIL(1,"失败"),
    UNKNOWN_ERROR(-1, "未知错误"),


    SYSTEM_ERROR(-1000001,"系统错误")
            ;

    private Integer code;

    private String message;

    BaseDataResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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
}
