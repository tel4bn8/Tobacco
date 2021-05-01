package com.nss.tobacco.entity;

import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建植保
 */

public class Sec_ZhibaoEntity implements Serializable{
    private String id;
    private String farmer;
    private String createtime;
    private String type;
    private String yaoming;
    private String nongdu;
    private String detail;
    private String state;
    private String photopath;

    public Sec_ZhibaoEntity() {
    }

    public Sec_ZhibaoEntity(String id, String farmer, String createtime, String type, String yaominag, String nongdu, String state, String detail, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.createtime = createtime;
        this.type = type;
        this.yaoming = yaominag;
        this.nongdu = nongdu;
        this.state = state;
        this.detail = detail;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYaominag() {
        return yaoming;
    }

    public void setYaominag(String yaominag) {
        this.yaoming = yaominag;
    }

    public String getNongdu() {
        return nongdu;
    }

    public void setNongdu(String nongdu) {
        this.nongdu = nongdu;
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
