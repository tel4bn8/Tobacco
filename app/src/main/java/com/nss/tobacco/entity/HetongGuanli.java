package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * hetongguanli
 */

public class HetongGuanli implements Serializable{
    private String id;
    private String code;
    private String year;
    private String farmer;
    private String type;
    private String area1;//草签面积
    private String num1;
    private String caoqiantime;
    private String detail;
    private String state;
    private String photopath;

    public HetongGuanli(){}

    public HetongGuanli(String id, String code, String year, String farmer, String type, String area1, String num1, String caoqiantime,
                        String detail, String state, String photopath) {
        this.id = id;
        this.code = code;
        this.year = year;
        this.farmer = farmer;
        this.type = type;
        this.area1 = area1;
        this.num1 = num1;
        this.caoqiantime = caoqiantime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getCaoqiantime() {
        return caoqiantime;
    }

    public void setCaoqiantime(String caoqiantime) {
        this.caoqiantime = caoqiantime;
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
