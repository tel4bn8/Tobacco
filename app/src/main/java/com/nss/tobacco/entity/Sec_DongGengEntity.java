package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * Created by lei on 2016/12/19.
 */

public class Sec_DongGengEntity implements Serializable{
    private String id;
    private String farmer;
    private String area;
    private String createtime;
    private String detail;
    private String depth;
    private String state;
    private String photopath;

    public Sec_DongGengEntity() {
    }

    public Sec_DongGengEntity(String id, String farmer, String area, String createtime, String detail, String depth, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.area = area;
        this.createtime = createtime;
        this.detail = detail;
        this.depth = depth;
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

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
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
