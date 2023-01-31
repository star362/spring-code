package com.star.stardemo.controller;

import com.star.stardemo.biaoshi.SpringBeanUtils;
import com.star.stardemo.factory.CarFactory;
import com.star.stardemo.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-05-29 16:39
 *
 * <p>
 */
@RestController
public class startTest {

    private static final Logger log = LoggerFactory.getLogger(startTest.class);



    CarFactory carFactory;
    CarService carService;

    public startTest(CarFactory carFactory, CarService carService) {
        this.carFactory = carFactory;
        this.carService = carService;
    }

    @GetMapping("stattestTest")
    public void test(){

       /* StarService b = SpringBeanUtils.getContext().getBean( "helloService",StarService.class);
        StarService c = SpringBeanUtils.getContext().getBean( "helloService1",StarService.class);

        StarService a = SpringBeanUtils.getContext().getBean( "startAutoDemo", StarService.class);
        Test a1 = SpringBeanUtils.getContext().getBean( Test.class);

        final List<CarService> list = carFactory.getCarServiceList();

        log.info("aaaaaaaa===[{}]",a);
        log.info("bbbbbbbb=[{}]",b);
        log.info("cccccccc===[{}]",c);*/


    }



    @ApiOperation(value = "文件上传 ", notes = "支持批量文件上传!")
    @Deprecated
    @PutMapping("/multiUpload")
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "/Users/star/Desktop/";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();

            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                log.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                log.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }

        return "上传成功";

    }


    @Deprecated
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        // 文件地址，真实环境是存放在数据库中的

        File file = new File("/Users/star/Desktop/费用报销制度.pdf");
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode("费用报销制度.pdf", "UTF-8"));
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }
}
