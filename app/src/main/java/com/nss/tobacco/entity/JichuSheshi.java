package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * jichusheshi
 */

public class JichuSheshi implements Serializable{
    private String id;
    private String year;
    private String village;
    private String kaofangnum;
    private String jijingnum;
    private String createtime;
    private String technician;
    private String detail;
    private String state;
    private String photopath;

    public JichuSheshi(){}

    public JichuSheshi(String id, String year, String village, String kaofangnum, String jijingnum,
                       String createtime, String technician, String detail, String state, String photopath) {
        this.id = id;
        this.year = year;
        this.village = village;
        this.kaofangnum = kaofangnum;
        this.jijingnum = jijingnum;
        this.createtime = createtime;
        this.technician = technician;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getKaofangnum() {
        return kaofangnum;
    }

    public void setKaofangnum(String kaofangnum) {
        this.kaofangnum = kaofangnum;
    }

    public String getJijingnum() {
        return jijingnum;
    }

    public void setJijingnum(String jijingnum) {
        this.jijingnum = jijingnum;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
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

    @Override
    public String toString() {
        return "JichuSheshi{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                ", village='" + village + '\'' +
                ", kaofangnum='" + kaofangnum + '\'' +
                ", jijingnum='" + jijingnum + '\'' +
                ", createtime='" + createtime + '\'' +
                ", technician='" + technician + '\'' +
                ", detail='" + detail + '\'' +
                ", state='" + state + '\'' +
                ", photopath='" + photopath + '\'' +
                '}';
    }
}
