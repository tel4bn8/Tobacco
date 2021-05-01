package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * Created by lei on 2016/12/14.
 */

public class Sec_TengChaEntity implements Serializable{
    private String id;
    private String farmer;
    private String area;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;
    public Sec_TengChaEntity() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhotoPath() {
        return photopath;
    }

    public void setPhotoPath(String photoPath) {
        this.photopath = photoPath;
    }

    public Sec_TengChaEntity(String id, String farmer, String area, String createTime, String detail, String state, String photoPath) {
        this.id = id;
        this.farmer = farmer;
        this.area = area;
        this.createtime = createTime;
        this.detail = detail;
        this.state = state;
        this.photopath = photoPath;
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

    public String getCreateTime() {
        return createtime;
    }

    public void setCreateTime(String createTime) {
        this.createtime =    createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
