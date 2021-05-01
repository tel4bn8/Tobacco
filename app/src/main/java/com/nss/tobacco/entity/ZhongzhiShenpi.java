package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * zhongzhishenpi
 */

public class ZhongzhiShenpi implements Serializable{
    private String id;
    private String farmer;
    private String year;
    private String type;
    private String area;
    private String shenpiren1;
    private String shenpiren2;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public ZhongzhiShenpi(){}

    public ZhongzhiShenpi(String id, String farmer, String year, String type, String area, String shenpiren1, String shenpiren2,
                          String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.year = year;
        this.type = type;
        this.area = area;
        this.shenpiren1 = shenpiren1;
        this.shenpiren2 = shenpiren2;
        this.createtime = createtime;
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
