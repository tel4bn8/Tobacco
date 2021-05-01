package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * yumiaoshenpi
 */

public class YumiaoShenpi implements Serializable{
    private String id;
    private String farmer;
    private String year;
    private String type;
    private String area;
    private String num;
    private String shenpiren1;
    private String shenpiren2;
    private String applytime;
    private String detail;
    private String state;
    private String photopath;

    public YumiaoShenpi(){}

    public YumiaoShenpi(String id, String farmer, String year, String type, String area, String num, String shenpiren1,
                        String shenpiren2, String applytime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.year = year;
        this.type = type;
        this.area = area;
        this.num = num;
        this.shenpiren1 = shenpiren1;
        this.shenpiren2 = shenpiren2;
        this.applytime = applytime;
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

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getShenpiren1() {
        return shenpiren1;
    }

    public void setShenpiren1(String shenpiren1) {
        this.shenpiren1 = shenpiren1;
    }

    public String getShenpiren2() {
        return shenpiren2;
    }

    public void setShenpiren2(String shenpiren2) {
        this.shenpiren2 = shenpiren2;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
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