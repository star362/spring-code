package com.nacos.POJO.exception;

import java.io.Serializable;

/**
 * @author wangyu
 * @date: 2020-04-25 11:57
 *
 * <p>  </p>
 */
public class BaseException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = 998597124833022572L;

    private Integer errCode;
    private String errMsg;


    public BaseException(Integer errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.errMsg = msg;
    }


    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
