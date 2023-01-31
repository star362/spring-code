package com.elasticsearchdemo.test.util;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-04-25 12:46
 *
 * <p>  </p>
 */
public enum BaseDataResultEnum {
    /**
     * 请求成功
     */
    SUCCESS(1, "请求成功"),

    /**
     * TONG 网关接口调用成功码
     */
    OK(200,"网关请求成功码"),

    /**
     * 失败
     */
    FAIL(-100, "失败"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-1000, "未知错误"),

    /**
     * 参数错误
     */
    VAIL_ERROR(-2000001, "参数错误"),

    /**
     * 参数错误
     */
    ASSER_ERROR(-3000002, "参数错误"),

    /**
     * 请求错误
     */
    BASE_ERROR(-4000003, "请求错误"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1000001, "系统错误");

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
