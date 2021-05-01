package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/6 0007.
 * 创建消毒信息的表
 */


public class Sec_XiaoduInfoEntity implements Serializable{

    private String id;
    private String farmer;
    private  String createtime;
    private String yaopin;
    private  String sheshi;
    private String detail;
    private String state;
    private String photopath;

    public Sec_XiaoduInfoEntity() {
    }

    public Sec_XiaoduInfoEntity(String id, String farmer, String createtime, String yaopin, String sheshi, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.createtime = createtime;
        this.yaopin = yaopin;
        this.sheshi = sheshi;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getYaopin() {
        return yaopin;
    }

    public void setYaopin(String yaopin) {
        this.yaopin = yaopin;
    }

    public String getSheshi() {
        return sheshi;
    }

    public void setSheshi(String sheshi) {
        this.sheshi = sheshi;
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
