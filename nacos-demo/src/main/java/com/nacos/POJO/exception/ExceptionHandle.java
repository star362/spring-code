package com.nacos.POJO.exception;

import com.nacos.POJO.VO.ResultVO;
import com.nacos.POJO.enums.BaseDataResultEnum;
import com.nacos.POJO.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wangyu
 * @date: 2020-04-25 12:05
 *
 * <p>  </p>
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {


    @ExceptionHandler(value = Exception.class)
    public ResultVO exceptionHandle(Exception e) {
        log.error("=======[Exception]=====",e);
        return ResultVOUtil.error(BaseDataResultEnum.UNKNOWN_ERROR.getCode(),
                BaseDataResultEnum.UNKNOWN_ERROR.getMessage());
    }


    @ExceptionHandler(value = BaseException.class)
    public ResultVO baseExceptionHandle(BaseException baseException) {
        log.error("=======[BaseException]=====",baseException);
        return ResultVOUtil.error(baseException.getErrCode(), baseException.getMessage());
    }


}
