package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建苗期管理的表
 */
//创建表

public class Sec_MiaoqiguanliEntity implements Serializable{

    private String id;
    private String farmer;

    private String miaoqing;
    private String createtime;
    private String miaoqi;
    private String zhuangmiaolv;
    private String detail;
    private String state;
    private String photopath;

    public Sec_MiaoqiguanliEntity() {
    }

    public Sec_MiaoqiguanliEntity(String id, String farmer, String miaoqing, String createtime, String miaoqi, String zhuangmiaolv, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.miaoqing = miaoqing;
        this.createtime = createtime;
        this.miaoqi = miaoqi;
        this.zhuangmiaolv = zhuangmiaolv;
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

    public String getMiaoqing() {
        return miaoqing;
    }

    public void setMiaoqing(String miaoqing) {
        this.miaoqing = miaoqing;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMiaoqi() {
        return miaoqi;
    }

    public void setMiaoqi(String miaoqi) {
        this.miaoqi = miaoqi;
    }

    public String getZhuangmiaolv() {
        return zhuangmiaolv;
    }

    public void setZhuangmiaolv(String zhuangmiaolv) {
        this.zhuangmiaolv = zhuangmiaolv;
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
