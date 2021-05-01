package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * wuzi
 */

public class WuziFafang implements Serializable{
    private String id;
    private String farmer;
    private String name;
    private String num;
    private String danwei;
    private String money;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public WuziFafang(){}

    public WuziFafang(String id, String farmer, String name, String num, String danwei,
                      String money, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.name = name;
        this.num = num;
        this.danwei = danwei;
        this.money = money;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
