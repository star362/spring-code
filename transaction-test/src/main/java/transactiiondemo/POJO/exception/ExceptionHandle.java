package transactiiondemo.POJO.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import transactiiondemo.POJO.VO.ResultVO;
import transactiiondemo.POJO.enums.BaseDataResultEnum;
import transactiiondemo.POJO.util.ResultVOUtil;

/**
 * @author wangyu
 * @date: 2020-04-25 12:05
 *
 * <p>  </p>
 */
@RestControllerAdvice
public class ExceptionHandle {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandle(Exception e) {
//        log.error("=======[exceptionHandle]=====", e);
        e.printStackTrace();
        return ResultVOUtil.error(BaseDataResultEnum.UNKNOWN_ERROR.getCode(),
                BaseDataResultEnum.UNKNOWN_ERROR.getMessage(), e.getMessage());
    }


    //手动抛出的异常
    @ExceptionHandler(value = {BaseException.class, BindException.class, IllegalArgumentException.class})
    public ResultVO baseExceptionHandle(Exception baseException) {
//        log.error("=======[baseExceptionHandle]=====", baseException);
        baseException.printStackTrace();
        if (baseException instanceof BaseException) {
            return ResultVOUtil.error(((BaseException) baseException).getErrCode(), ((BaseException) baseException).getErrMsg(), ((BaseException) baseException).getErrInfo());
        } else if (baseException instanceof BindException) {
            ObjectError objectError = ((BindException)baseException).getBindingResult().getAllErrors().get(0);
            // 然后提取错误提示信息进行返回
            return ResultVOUtil.error(BaseDataResultEnum.ASSER_ERROR.getCode(), BaseDataResultEnum.ASSER_ERROR.getMessage(), objectError.getDefaultMessage());
        }
        return ResultVOUtil.error(BaseDataResultEnum.VAIL_ERROR.getCode(), BaseDataResultEnum.VAIL_ERROR.getMessage(), baseException.getMessage());
    }




}
