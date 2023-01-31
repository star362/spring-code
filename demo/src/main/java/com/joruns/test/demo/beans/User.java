package com.joruns.test.demo.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
    private Integer FID;
    private String UNAME;
    private Float UAGE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date COMMDATE;

    public User() {
    }

    public Integer getFID() {
        return FID;
    }

    public void setFID(Integer FID) {
        this.FID = FID;
    }

    public String getUNAME() {
        return UNAME;
    }

    public void setUNAME(String UNAME) {
        this.UNAME = UNAME;
    }

    public Float getUAGE() {
        return UAGE;
    }

    public void setUAGE(Float UAGE) {
        this.UAGE = UAGE;
    }

    public Date getCOMMDATE() {
        return COMMDATE;
    }

    public void setCOMMDATE(Date COMMDATE) {
        this.COMMDATE = COMMDATE;
    }

    @Override
    public String toString() {
        return "User{" +
                "FID='" + FID + '\'' +
                ", UNAME='" + UNAME + '\'' +
                ", UAGE=" + UAGE +
                ", COMMDATE=" + COMMDATE +
                '}';
    }

    public User(Integer FID, String UNAME, Float UAGE, Date COMMDATE) {
        this.FID = FID;
        this.UNAME = UNAME;
        this.UAGE = UAGE;
        this.COMMDATE = COMMDATE;
    }

}
