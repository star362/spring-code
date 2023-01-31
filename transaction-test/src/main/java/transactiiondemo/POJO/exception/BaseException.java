package transactiiondemo.POJO.exception;

import transactiiondemo.POJO.enums.BaseDataResultEnum;

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
    private String errInfo;

    public BaseException(Integer errCode, String errMsg, String errInfo) {
        super(errInfo);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errInfo=errInfo;
    }



    public BaseException(BaseDataResultEnum bsseEnum, String errInfo) {
        this(bsseEnum.getCode(),bsseEnum.getMessage(),errInfo);
    }

    public BaseException(String errInfo) {
        this(BaseDataResultEnum.BASE_ERROR.getCode(),BaseDataResultEnum.BASE_ERROR.getMessage(),errInfo);
    }

    public Integer getErrCode() {
        return errCode;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public String getErrInfo() {
        return errInfo;
    }
}
