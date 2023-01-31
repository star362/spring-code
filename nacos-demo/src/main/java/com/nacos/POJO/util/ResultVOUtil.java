package com.nacos.POJO.util;


import com.nacos.POJO.VO.ResultVO;
import com.nacos.POJO.enums.BaseDataResultEnum;

/**
 * 结果返回工具类
 */
public class ResultVOUtil {
    /**
     * 成功
     * @param object 对象
     * @return ResultVO
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(BaseDataResultEnum.SUCCESS.getCode());
        resultVO.setMsg(BaseDataResultEnum.SUCCESS.getMessage());
        return resultVO;
    }

    /**
     * 成功
     * @return ResultVO
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * 错误
     * @param code 代码
     * @param msg  消息
     * @return
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
