package com.star.aoptest.entity;

import com.star.aoptest.aop.Annotations.Mask;

import java.io.Serializable;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-08-19 19:55
 *
 * <p>
 */

public class School implements Serializable {

    private static final long serialVersionUID = 932002574656009871L;
    @Mask(prefixNoMaskLen = 1,suffixNoMaskLen = 1,maskStr = "&")
    private String name;
    @Mask(prefixNoMaskLen = 0,suffixNoMaskLen = 2,maskStr = "A")
    private String address;
    private String email;
    private long mobile;

    private School sch;

    public School getSch() {
        return sch;
    }

    public void setSch(School sch) {
        this.sch = sch;
    }

    public School() {
    }

    public School(String name, String address, String email, long mobile,School sch) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.sch=sch;
    }

    public School(String name, String address, String email, long mobile) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
