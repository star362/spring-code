package com.nacos.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nacos.config.SpringBeanUtils;
import com.nacos.POJO.VO.ResultVO;
import com.nacos.POJO.enums.BaseDataResultEnum;
import com.nacos.POJO.exception.BaseException;
import com.nacos.POJO.util.ResultVOUtil;
import com.nacos.repistory.StarUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyu
 * @date: 2020-01-14 16:35
 * @describe:
 */
@Slf4j
@RestController
public class UserController {



    @GetMapping("/f")
    public void f() throws JsonProcessingException {
        StarUser starUser = new StarUser(1,"uname",22,"man");

        ObjectMapper mapper = new ObjectMapper();

        final String s = mapper.writeValueAsString(starUser);
    }


    @GetMapping("/g")
    public ResultVO g() {
        return ResultVOUtil.success(SpringBeanUtils.getContext().getBeanDefinitionNames());
    }


    @GetMapping("/h")
    public ResultVO h() {

        int i = 1 / 0;


        return ResultVOUtil.success();

//        throw new BaseException(444,"错误信息展示");

    }

    @GetMapping("/i")
    public ResultVO i() {




        throw new BaseException(BaseDataResultEnum.SYSTEM_ERROR.getCode(),BaseDataResultEnum.SYSTEM_ERROR.getMessage());

    }





}
