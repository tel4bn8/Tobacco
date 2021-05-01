package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * baoxian
 */

public class Sec_Baoxian implements Serializable {
    private String id;
    private String farmer;
    private String toubaoarea;//投保面积
    private String lipeiarea;//理赔面积
    private String hetongarea;//合同面积
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_Baoxian() {}

    public Sec_Baoxian(String id, String farmer, String toubaoarea, String lipeiarea, String hetongarea,
                       String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.toubaoarea = toubaoarea;
        this.lipeiarea = lipeiarea;
        this.hetongarea = hetongarea;
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

    public String getToubaoarea() {
        return toubaoarea;
    }

    public void setToubaoarea(String toubaoarea) {
        this.toubaoarea = toubaoarea;
    }

    public String getLipeiarea() {
        return lipeiarea;
    }

    public void setLipeiarea(String lipeiarea) {
        this.lipeiarea = lipeiarea;
    }

    public String getHetongarea() {
        return hetongarea;
    }

    public void setHetongarea(String hetongarea) {
        this.hetongarea = hetongarea;
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
