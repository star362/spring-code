package com.star.stardemo.dataobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;

import java.io.Serializable;


public class StarUser implements Serializable {


    private static final long serialVersionUID = 4222654975491175395L;

    @NonNull
    @ApiModelProperty(value = "", name = "id")
    private Integer id;
    @ApiModelProperty(value = "", name = "user_name")
    private String userName;
    @ApiModelProperty(value = "", name = "user_age")
    private Integer userAge;
    @ApiModelProperty(value = "", name = "user_sex")
    private String userSex;

    public StarUser(@NonNull Integer id, String userName, Integer userAge, String userSex) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public StarUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "StarUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                '}';
    }
}