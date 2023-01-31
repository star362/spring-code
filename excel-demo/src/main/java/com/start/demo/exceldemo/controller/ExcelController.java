package com.start.demo.exceldemo.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.excel.EasyExcel;
import com.start.demo.exceldemo.dataaobject.DemoData;
import com.start.demo.exceldemo.dataaobject.DemoDataListener;
import com.start.demo.exceldemo.dataaobject.Employee;
import com.start.demo.exceldemo.util.ExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangyu
 * @date: 2019-12-15 11:58
 * @describe:
 */
@RestController
@Slf4j
public class ExcelController {


    @GetMapping("/")
    public void downLoadExcel(HttpServletResponse response) {
        // 生成文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".xlsx";
        // 调用工具类生成Excel，此时接收到的wb即是完整的Excel
        List<Map<String, Object>> data = new LinkedList<>();
        Map<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("姓名", "张三");
        hashMap.put("年龄", 23);
        hashMap.put("性别", "男");
        data.add(hashMap);
        hashMap = new LinkedHashMap<>();
        hashMap.put("姓名", "李四");
        hashMap.put("年龄", 24);
        hashMap.put("性别", "男");
        data.add(hashMap);
        ExportUtil.exportExcelDow(data, fileName, response);

    }


    /**
     * 导入
     *
     * @param file
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public void importExcel(@RequestParam("file") MultipartFile file)  {
//        ImportParams importParams = new ImportParams();
        // 数据处理
//        IExcelDataHandler<Employee> handler = new UserExcelHandler();
//        handler.setNeedHandlerFields(new String[] { "姓名" });// 注意这里对应的是excel的列名。也就是对象上指定的列名。
//        importParams.setDataHanlder(handler);
//
//        // 需要验证
//        importParams.setNeedVerfiy(true);
    }

    @PostMapping("/importExcel2")
    public void importExcel2(HttpEntity<byte[]> requestEntity) throws Exception {
        final byte[] body = requestEntity.getBody();
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<DemoData> list = ExcelImportUtil.importExcel(
                new ByteArrayInputStream(body),
                DemoData.class, params);
        System.out.println(list.size());
        list.forEach(s-> System.out.println(s));
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
    }




    /**
     excel文件的下载
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=demo.xlsx");
        EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("模板").doWrite(
                new LinkedList()
        );
    }

    /**
     excel文件的上传
     */
    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), DemoData.class, new DemoDataListener()).sheet().doRead();
        return "success";
    }

}
