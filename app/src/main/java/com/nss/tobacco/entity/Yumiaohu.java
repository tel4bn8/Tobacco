package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * yumiaohu
 */

public class Yumiaohu implements Serializable{
    private String id;
    private String name;
    private String idcard;
    private String year;//育苗年度
    private String sex;
    private String age;
    private String education;
    private String village;
    private String phone;
    private String technician;
    private String createtime;
    private String yumiaogongchangid;
    private String detail;
    private String state;
    private String photopath;

    public Yumiaohu(){}

    public Yumiaohu(String id, String name, String idcard, String year, String sex, String age, String education, String village, String phone,
                    String technician, String createtime, String yumiaogongchangid, String detail, String state, String photopath) {
        this.id = id;
        this.name = name;
        this.idcard = idcard;
        this.year = year;
        this.sex = sex;
        this.age = age;
        this.education = education;
        this.village = village;
        this.phone = phone;
        this.technician = technician;
        this.createtime = createtime;
        this.yumiaogongchangid = yumiaogongchangid;
        this.detail = detail;
        this.state = state;
        this.photopath = photopath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getYumiaogongchangid() {
        return yumiaogongchangid;
    }

    public void setYumiaogongchangid(String yumiaogongchangid) {
        this.yumiaogongchangid = yumiaogongchangid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
}
