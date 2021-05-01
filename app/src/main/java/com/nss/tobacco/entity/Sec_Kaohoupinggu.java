package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * kaohoupinggu
 */

public class Sec_Kaohoupinggu implements Serializable {
    private String id;
    private String farmer;
    private String kangci;//
    private String oneganshu;//单炕杆数
    private String one;//单杆重量
    private String sum;//烤后烤烟出炕量
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_Kaohoupinggu() {}

    public Sec_Kaohoupinggu(String id, String farmer, String kangci, String oneganshu, String one, String sum,
                            String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.kangci = kangci;
        this.oneganshu = oneganshu;
        this.one = one;
        this.sum = sum;
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

    public String getKangci() {
        return kangci;
    }

    public void setKangci(String kangci) {
        this.kangci = kangci;
    }

    public String getOneganshu() {
        return oneganshu;
    }

    public void setOneganshu(String oneganshu) {
        this.oneganshu = oneganshu;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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
