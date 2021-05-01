package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * tianjianpinggu
 */

public class Sec_TianjianpingguEntity implements Serializable {
    private String id;
    private String farmer;
    private String area;
    private String zhunum;
    private String leafnum;//有效叶数
    private String one;//单叶重量
    private String sum;//预测产量
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_TianjianpingguEntity() {}

    public Sec_TianjianpingguEntity(String id, String farmer, String area, String zhunum, String leafnum, String one, String sum,
                                    String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.area = area;
        this.zhunum = zhunum;
        this.leafnum = leafnum;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getZhunum() {
        return zhunum;
    }

    public void setZhunum(String zhunum) {
        this.zhunum = zhunum;
    }

    public String getLeafnum() {
        return leafnum;
    }

    public void setLeafnum(String leafnum) {
        this.leafnum = leafnum;
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
