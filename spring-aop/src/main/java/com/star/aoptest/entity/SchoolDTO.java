package com.star.aoptest.entity;

import com.star.aoptest.aop.Annotations.Mask;
import org.springframework.cglib.beans.BeanCopier;

import java.io.Serializable;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-08-19 19:55
 *
 * <p>
 */

public class SchoolDTO implements Serializable {

    private static final long serialVersionUID = 932002574656009871L;
    @Mask(prefixNoMaskLen = 1, suffixNoMaskLen = 1, maskStr = "&")
    private String name;
    @Mask(prefixNoMaskLen = 0, suffixNoMaskLen = 2, maskStr = "A")
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

    public SchoolDTO() {
    }

    public SchoolDTO(String name, String address, String email, long mobile, School sch) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.sch = sch;
    }

    public SchoolDTO(String name, String address, String email, long mobile) {
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
        return "SchoolDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", mobile=" + mobile +
                ", sch=" + sch +
                '}';
    }

//    public static void main(String[] args) {
//        School school = new School();
//        school.setAddress("add");
//        school.setEmail("163");
//        school.setName("name");
//        School schoolsch = new School();
//        schoolsch.setAddress("addschoolsch");
//        schoolsch.setEmail("163schoolsch");
//        schoolsch.setName("nameschoolsch");
//        school.setSch(schoolsch);
//        SchoolDTO schoolDTO = new SchoolDTO();
//
//        BeanCopier.create(School.class, SchoolDTO.class, false)
//                .copy(school,schoolDTO,null);
//
//        System.out.println("old" + school);
//        System.out.println("new" + schoolDTO);
//
//        schoolsch.setAddress("==============");
//        schoolDTO.setAddress("add2");
//
////        schoolDTO.setSch(schoolsch2);
//        System.out.println("=============================" );
//        System.out.println("old" + school);
//        System.out.println("new" + schoolDTO);
//    }
}
