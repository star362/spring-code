package com.joruns.test.demo.beans;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("town")
public class Town implements Serializable {
  private String id;
  private String cityId;
  private String townName;
  private  String townCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public Town() {
    }
}
