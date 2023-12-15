package com.star.springbeandemo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.star.springbeandemo.config.SpringBeanUtils;
import com.star.springbeandemo.service.AService;
import com.star.springbeandemo.zhoenjiemodel.Market;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wangyu
 * @date: 2020-02-07 09:43
 * @describe:
 */
@RestController
@Slf4j
public class TestController {


    @Autowired
    ConfigurableApplicationContext context;


    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @GetMapping("/test")
    public String test() {

        return "close success";
    }


    @GetMapping("/aa")
    public String aa() {

        threadPoolTaskExecutor.execute(() -> {

            int i = 0;
            while (i < 200000) {
                while (i < 200000) {
                    while (i < 200000) {
                        while (i < 200000) {
                            while (i < 200000) {
                                while (i < 200000) {
                                }
                            }
                        }
                    }
                }

                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                Random random = new Random();
                random.nextInt(10);
                System.out.println("====" + i + ":" + Thread.currentThread().getName());
                i++;
            }
            System.out.println("runnable===========:" + Thread.currentThread().getName());
        });


        return "success";
    }


    @GetMapping("/aaa")
    public String aaa() {

        log.info("==========aaaaa=========");

        return "success";
    }

    @Autowired
    private Market market;

    @GetMapping("/aa1")
    public String aa1() {


        market.selfAction();
        //market.outAction();


        return "success";
    }


    @GetMapping("/getbean1")
    public void getbean1() {
        final ApplicationContext context = SpringBeanUtils.getContext();
        ConfigurableApplicationContext context1 = (ConfigurableApplicationContext) context;
        final DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context1.getBeanFactory();
        beanFactory.removeBeanDefinition("AService");
    }

    @GetMapping("/getbean")
    public String[] getbean() {
        SpringBeanUtils.getContext().getBeanDefinitionCount();
        return SpringBeanUtils.getContext().getBeanDefinitionNames();
    }

    @Autowired
    private AService aService;

    @GetMapping("/aService")
    public void aService() {

        aService.apply();
    }


    @GetMapping("/code")
    public void code(@RequestParam String codeUrl, HttpServletResponse response) throws IOException, WriterException {

        //生成二维码
        //map中存放的是生成二维码的参数
        Map<EncodeHintType, Object> map2 = new HashMap<EncodeHintType, Object>();
        map2.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        //生成二维码，参数依次是：二维码内容，二维码编码方式，长，宽，参数
        BitMatrix encode = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 200, 200, map2);

        //获取输出流，响应到前台
        OutputStream out = response.getOutputStream();

        MatrixToImageWriter.writeToStream(encode, "jpg", out);

        out.flush();
        out.close();
    }

}
