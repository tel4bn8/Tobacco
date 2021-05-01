package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * zaihaitongji
 */

public class Sec_Zaihaitongji implements Serializable{

    private String id;
    private String farmer;
    private String type;
    private String area1;
    private String area2;
    private String lose;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_Zaihaitongji() {}

    public Sec_Zaihaitongji(String id, String farmer, String type, String area1, String area2,
                            String lose, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.type = type;
        this.area1 = area1;
        this.area2 = area2;
        this.lose = lose;
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

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
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
