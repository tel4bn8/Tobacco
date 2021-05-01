package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建温湿度
 */

public class Sec_WenshiduEntity implements Serializable{

    private String id;
    private String farmer;
    private String wendu;
    private String shidu;
    private String createtime;


    private String miaoqing;
    private String detail;
    private String state;
    private String photopath;

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

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
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

    public Sec_WenshiduEntity() {

    }
    public String getMiaoqing() {
        return miaoqing;
    }

    public void setMiaoqing(String miaoqing) {
        this.miaoqing = miaoqing;
    }

    public Sec_WenshiduEntity(String id, String farmer, String wendu, String shidu, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.wendu = wendu;
        this.shidu = shidu;
        this.createtime = createtime;
        this.detail = detail;
        this.state = state;
        this.photopath = photopath;
    }
}
