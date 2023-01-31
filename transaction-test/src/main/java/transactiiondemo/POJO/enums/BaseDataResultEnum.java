package transactiiondemo.POJO.enums;

/**
 * @author wangyu
 * @date: 2020-04-25 12:46
 *
 * <p>  </p>
 */
public enum BaseDataResultEnum {
    SUCCESS(200, "请求成功"),
    UNKNOWN_ERROR(-100, "未知错误"),
    VAIL_ERROR(-2000001, "参数错误"),
    ASSER_ERROR(-3000002, "参数错误"),
    BASE_ERROR(-4000003, "请求错误"),


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
