package com.joruns.test.demo.beans;

import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("province")
public class Province {
    private Integer id;
    private String provinceNum;
    private String provinceName;

    private List<City> citys;

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceNum() {
        return provinceNum;
    }

    public void setProvinceNum(String provinceNum) {
        this.provinceNum = provinceNum;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", provinceNum='" + provinceNum + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", citys=" + citys +
                '}';
    }

    public Province(Integer id, String provinceNum, String provinceName) {
        this.id = id;
        this.provinceNum = provinceNum;
        this.provinceName = provinceName;
    }

    public Province() {

    }
}
