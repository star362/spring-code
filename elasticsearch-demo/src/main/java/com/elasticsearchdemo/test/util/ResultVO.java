package com.elasticsearchdemo.test.util;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-13 16:20
 *
 * <p>
 * 请求返回格式类
 * <pre>
 * {
 *     "code": 20000000,
 *     "msg": "未通过",
 *     "data": null
 * }
 * </pre>
 */
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 2742893794493929658L;

    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    @JsonProperty("msg")
    private String message;

    /**
     * 具体内容.
     */
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String msg) {
        this(code, msg, null);
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public ResultVO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultVO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultVO<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
