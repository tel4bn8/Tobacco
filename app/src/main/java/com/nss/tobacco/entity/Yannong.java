package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/23.
 */

public class Yannong implements Serializable {
    private String id;
    private String name;
    private String idcard;
    private String phone;
    private String sex;
    private String age;
    private String workyear;
    private String education;
    private String area;
    private String year;
    private String skill;
    private String credit;
    private String grade;
    private String village;
    private String createtime;
    private String detail;
    private String technician;
    private String state;
    private String photopath;

    public Yannong(){}

    public Yannong(String id, String name, String idcard, String phone, String sex, String age, String workyear, String education, String area, String year,
                   String skill, String credit, String grade, String village, String createtime, String detail, String technician, String state, String photopath) {
        this.id = id;
        this.name = name;
        this.idcard = idcard;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.workyear = workyear;
        this.education = education;
        this.area = area;
        this.year = year;
        this.skill = skill;
        this.credit = credit;
        this.grade = grade;
        this.village = village;
        this.createtime = createtime;
        this.detail = detail;
        this.technician = technician;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
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
