package com.joruns.test.demo.beans;

import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Max;
import java.util.List;

@Alias("city")
public class City {
    private String id;
    private Integer provId;
    private String cityCode;
    private String cityName;

    private Province province1;

    private List<Town> town;

    public List<Town> getTown() {
        return town;
    }

    public void setTown(List<Town> town) {
        this.town = town;
    }

    public Province getProvince1() {
        return province1;
    }

    public void setProvince1(Province province1) {
        this.province1 = province1;
    }

    public City(String id, Integer provId, String cityCode, String cityName) {
        this.id = id;
        this.provId = provId;
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public City() {

    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", provId=" + provId +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", province1=" + province1 +
                ", town=" + town +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
