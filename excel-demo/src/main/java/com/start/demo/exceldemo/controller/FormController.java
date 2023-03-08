package com.start.demo.exceldemo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("a/b")
@Slf4j
public class FormController {


    //    页面跳转
    @RequestMapping("test")
    public void tiaozhuan(@RequestParam(required = false) Map ipmap, HttpServletResponse response) throws IOException {
        log.info("==RequestParam=={}", ipmap);
//        log.info("==RequestBody=={}",ibmap);
        response.sendRedirect("http://www.baidu.com");

    }


}
