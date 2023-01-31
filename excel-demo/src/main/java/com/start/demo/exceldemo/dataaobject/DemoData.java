package com.start.demo.exceldemo.dataaobject;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author wangyu
 * @date: 2019-12-15 12:10
 * @describe:
 */
public class DemoData implements Serializable {


    private static final long serialVersionUID = 3502206941715031812L;


    @Excel(name = "姓名", orderNum = "1", width = 15)
    private String string;

    @Excel(name = "其他")
    private Double doubleData;



    //    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @Excel(name = "日期")
    private String createTimee;


    public String getCreateTimee() {
        return createTimee;
    }

    public void setCreateTimee(String createTimee) {
        this.createTimee = createTimee;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getDoubleData() {
        return doubleData;
    }

    public void setDoubleData(Double doubleData) {
        this.doubleData = doubleData;
    }
}
