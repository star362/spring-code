package com.start.demo.exceldemo;

import com.alibaba.excel.EasyExcel;
import com.start.demo.exceldemo.dataaobject.DemoData;
import com.start.demo.exceldemo.dataaobject.DemoDataListener;
import com.start.demo.exceldemo.dataaobject.DemoHeadDataListener;
import com.start.demo.exceldemo.dataaobject.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelDemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        String fileName = "/Users/star/Desktop/9999.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet("Sheet1").headRowNumber(0).doRead();



        /*// 写法2：
        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();*/
    }


    @Test
    public void headerRead() {
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        String fileName = "/Users/star/Desktop/aaa.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        EasyExcel.read(fileName, Employee.class, new DemoHeadDataListener()).sheet().doRead();
    }


}
