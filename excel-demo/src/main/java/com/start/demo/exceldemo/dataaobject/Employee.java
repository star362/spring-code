package com.start.demo.exceldemo.dataaobject;


import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @author wangyu
 * @date: 2019-12-20 09:16
 * @describe:
 */
//@Data
public class Employee implements Serializable {

//    private static final long serialVersionUID = 1L;

    /**
     * id
     */
//    @ExcelIgnore
//    private String        id;

    @Excel(name = "姓名", orderNum = "1", width = 15)
    private String username;
    @Excel(name = "年龄")
    private Integer age;
    @Excel(name = "性别")
    private String sex;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    // 省略了getter,setter
}