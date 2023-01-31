package com.start.demo.poi;

import com.start.demo.exceldemo.util.ExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author wangyu
 * @date: 2019-12-19 09:17
 * @describe:
 */
@Slf4j
@RunWith(SpringRunner.class)
public class PoiModeTest {

    String fileName ;
    List<Map<String, Object>> data ;

    @Before
    public void before() {
        log.info("===before==");
        // 生成文件名
         fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".xlsx";
        // 调用工具类生成Excel，此时接收到的wb即是完整的Excel
        data = new LinkedList<>();
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
    }

    @After
    public void after() {
        log.info("====after====");
    }

    @Test
    public void contextLoads() {
    }


    @Test
    public void apachePoiTest() {
        //1. 创建工作簿 HSSFWorkbook -- 2003版本
//        Workbook workbook = new HSSFWorkbook();
        Workbook workbook = new XSSFWorkbook();//创建2007版本
        //2. 创建表单sheet，并且取文件名
        Sheet sheet = workbook.createSheet("poiTest1");
        //3. 创建文件流，使用简单的文件流就可
        try {
//            FileOutputStream fileInputStream = new FileOutputStream("/Users/star/Desktop/poi2.xls");
            FileOutputStream fileInputStream = new FileOutputStream("/Users/star/Desktop/poi2.xlsx");
            //4. 写入文件
            workbook.write(fileInputStream);
            //5. 关闭流
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }


    @Test
    public void apacheContent() {
        //1. 创建工作簿 HSSFWorkbook -- 2003版本
//        Workbook workbook = new HSSFWorkbook();//创建2003版本
        Workbook workbook = new XSSFWorkbook();//创建2007版本
        //2. 创建表单sheet，并且取文件名
        Sheet sheet = workbook.createSheet("poiTest1");
        //3. 创建行对象 参数：索引（从0开始）
        Row row = sheet.createRow(2);
        //创建单元格 锁定这一行的第某个单元格，参数：索引（从0开始）
        Cell cell = row.createCell(2);
        //在单元格写入内容
        cell.setCellValue("2NaCl");


        //样式处理,创建样式对象
        CellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setBorderTop(BorderStyle.DOTTED);//设置上边框，参数：对边框的某种设置
//        cellStyle.setBorderLeft(BorderStyle.THIN);//设置左
//        cellStyle.setBorderBottom(BorderStyle.MEDIUM);//设置下
//        cellStyle.setBorderRight(BorderStyle.DASHED);//设置右
        //创建字体对象
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");//字体
        font.setFontHeightInPoints((short) 14);//字号
        cellStyle.setFont(font);

        //行高和列宽
        row.setHeightInPoints(50);//行高
        sheet.setColumnWidth(3, 55 * 256);//设置第几列，多宽（字符宽度）所以要除以256
        //居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //将之前的样式全部添加到单元格中
        cell.setCellStyle(cellStyle);


        //4. 创建文件流，使用简单的文件流就可
        try (FileOutputStream fileInputStream = new FileOutputStream("/Users/star/Desktop/poi2.xlsx")) {
            //5. 写入文件
            workbook.write(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void createInstance() {
        fileName = "/Users/star/Desktop/9999.xlsx";
        String[] s={"姓名","日期","其他"};
        final List<Map<String, Object>> maps = new ExportUtil().readExcel(fileName, s);

        maps.forEach(k->{
           k.forEach((ke,v)->{
               log.info("====kkk==[{}]===val[{}]",ke,v);
           });
        });
    }


    @Test
    public void createIexportExcelDow() {

    }


}
