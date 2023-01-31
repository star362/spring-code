package com.start.demo.exceldemo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author wangyu
 * @date: 2019-12-19 10:08
 * @describe:
 */
public class ExportUtil {

    /**
     * 无模块导出Excel方法，
     *
     * @param data     需要导出的数据
     * @param fileName 导出后保存到本地的文件名
     * @return 创建好的Excel文件，用于前端下载
     * @throws
     */
    public static Workbook exportExcel(List<Map<String, Object>> data, String fileName) {
        // 从参数data中解析出打印的每列标题，放入title中
        List<String> title = new LinkedList<>();
        for (Map.Entry<String, Object> entry : data.get(0).entrySet()) {
            title.add(entry.getKey());
        }
        // 新建一个Excel文件
//        Workbook wb = new HSSFWorkbook();//2003 版本
        Workbook wb = new XSSFWorkbook();//创建2007版本
        // Excel中的sheet
        Sheet sheet = wb.createSheet();
        // sheet中的行，0表示第一行
        Row row = sheet.createRow(0);
        // 设置标题居中
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // sheet中的单元格
        Cell cell = null;

        // 给第一行赋值，值为我们从参数中解析出的标题，因此需要我们在传参的时候需要严格按照约定
        for (int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        // 根据参数内容数量，创建表格行数
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);

            Map<String, Object> values = data.get(i);

            // 将参数插入每一个单元格
            for (int k = 0; k < title.size(); k++) {
                Optional<Object> optional = Optional.ofNullable(values.get(title.get(k)));
                row.createCell(k).setCellValue(optional.orElse("").toString());
            }
        }
        // 将文件写到硬盘中，将来根据需求，或写到服务器中，因此在实际开发中，最好将"E:/Temp/"配置在.properties配置文件中，仪表项目上线事更换方便
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/star/Desktop/" + fileName))) {

            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


    public static Workbook exportExcelDow(List<Map<String, Object>> data, String fileName, HttpServletResponse response) {
        // 从参数data中解析出打印的每列标题，放入title中
        List<String> title = new LinkedList<>();
        for (Map.Entry<String, Object> entry : data.get(0).entrySet()) {
            title.add(entry.getKey());
        }
        // 新建一个Excel文件
//        Workbook wb = new HSSFWorkbook();//2003 版本
        Workbook wb = new XSSFWorkbook();//创建2007版本
        // Excel中的sheet
        Sheet sheet = wb.createSheet();
        // sheet中的行，0表示第一行
        Row row = sheet.createRow(0);
        // 设置标题居中
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // sheet中的单元格
        Cell cell = null;

        // 给第一行赋值，值为我们从参数中解析出的标题，因此需要我们在传参的时候需要严格按照约定
        for (int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        // 根据参数内容数量，创建表格行数
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);

            Map<String, Object> values = data.get(i);

            // 将参数插入每一个单元格
            for (int k = 0; k < title.size(); k++) {
                Optional<Object> optional = Optional.ofNullable(values.get(title.get(k)));
                row.createCell(k).setCellValue(optional.orElse("").toString());
            }
        }

        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return wb;
    }


    /**
     * 读取excel 第1张sheet （xls和xlsx）
     *
     * @param filePath excel路径
     * @param columns  列名（表头）
     * @return
     * @author
     */
    public List<Map<String, Object>> readExcel(String filePath, String columns[]) {
        Sheet sheet = null;
        Row row = null;
        Row rowHeader = null;
        List<Map<String, Object>> list = null;
        Object cellData = null;
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            if (wb != null) {
                // 用来存放表中数据
                list = new ArrayList<Map<String, Object>>();
                // 获取第一个sheet
                sheet = wb.getSheetAt(0);
                // 获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                // 获取第一行
                rowHeader = sheet.getRow(0);
                row = sheet.getRow(0);
                // 获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                for (int i = 1; i < rownum; i++) {
                    Map<String, Object> map = new LinkedHashMap();
                    row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < colnum; j++) {
                            if (columns[j].equals(getCellFormatValue(rowHeader.getCell(j)))) {
                                cellData = getCellFormatValue(row.getCell(j));
                                map.put(columns[j], cellData);
                            }
                        }
                    } else {
                        break;
                    }
                    list.add(map);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取单个单元格数据
     *
     * @param cell
     * @return
     * @author lizixiang ,2018-05-08
     */
    public Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                System.out.println(cell.getRichStringCellValue().getString());
                cellValue=cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                // 判断cell是否为日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 转换为日期格式YYYY-mm-dd
                    System.out.println("获取单个单元格数据:" + cell.getDateCellValue());
                    cellValue = cell.getDateCellValue();
                } else {
                    System.out.println("获取单个单元格数据:" + cell.getNumericCellValue());
                    cellValue = cell.getNumericCellValue();
                }
                break;
            case BOOLEAN:
                System.out.println("获取单个单元格数据:" + cell.getBooleanCellValue());
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                System.out.println("获取单个单元格数据:" + cell.getCellFormula());
                cellValue = cell.getCellFormula();
                break;
            default:
                System.out.println("default");
                cellValue = "";
        }

        return cellValue;
    }


}
