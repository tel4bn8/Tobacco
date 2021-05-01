package com.nss.tobacco.entity;

import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建打顶抑牙的表
 */

public class Sec_DadingEntity implements Serializable{
    private String id;
    private String farmer;
    private String zhangshi;
    private String yiyaji;
    private String xianleitime;
    private String dadingtime;
    private String youhuatime;
    private String youhuashu;
    private String detail;
    private String state;
    private String photopath;

    public Sec_DadingEntity() {
    }

    public Sec_DadingEntity(String id, String farmer, String zhangshi, String yiyaji, String xianleitime, String dadingtime, String youhuatime, String youhuashu, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.zhangshi = zhangshi;
        this.yiyaji = yiyaji;
        this.xianleitime = xianleitime;
        this.dadingtime = dadingtime;
        this.youhuatime = youhuatime;
        this.youhuashu = youhuashu;
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

    public String getZhangshi() {
        return zhangshi;
    }

    public void setZhangshi(String zhangshi) {
        this.zhangshi = zhangshi;
    }

    public String getYiyaji() {
        return yiyaji;
    }

    public void setYiyaji(String yiyaji) {
        this.yiyaji = yiyaji;
    }

    public String getXianleitime() {
        return xianleitime;
    }

    public void setXianleitime(String xianleitime) {
        this.xianleitime = xianleitime;
    }

    public String getDadingtime() {
        return dadingtime;
    }

    public void setDadingtime(String dadingtime) {
        this.dadingtime = dadingtime;
    }

    public String getYouhuatime() {
        return youhuatime;
    }

    public void setYouhuatime(String youhuatime) {
        this.youhuatime = youhuatime;
    }

    public String getYouhuashu() {
        return youhuashu;
    }

    public void setYouhuashu(String youhuashu) {
        this.youhuashu = youhuashu;
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
