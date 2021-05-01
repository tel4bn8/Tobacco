package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * lihua实体
 */

public class YanyeLihua implements Serializable{

    private String id;
    private String wanggecode;
    private String year;
    private String k;
    private String cl;
    private String yanjian;
    private String huanyuantang;
    private String zongtang;
    private String picktime;
    private String technician;
    private String detail;
    private String state;
    private String photopath;

    public YanyeLihua(){}

    public YanyeLihua(String id, String wanggecode, String year, String k, String cl, String yanjian,
                      String huanyuantang, String zongtang, String picktime, String technician, String detail, String state, String photopath) {
        this.id = id;
        this.wanggecode = wanggecode;
        this.year = year;
        this.k = k;
        this.cl = cl;
        this.yanjian = yanjian;
        this.huanyuantang = huanyuantang;
        this.zongtang = zongtang;
        this.picktime = picktime;
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

    public String getWanggecode() {
        return wanggecode;
    }

    public void setWanggecode(String wanggecode) {
        this.wanggecode = wanggecode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getYanjian() {
        return yanjian;
    }

    public void setYanjian(String yanjian) {
        this.yanjian = yanjian;
    }

    public String getHuanyuantang() {
        return huanyuantang;
    }

    public void setHuanyuantang(String huanyuantang) {
        this.huanyuantang = huanyuantang;
    }

    public String getZongtang() {
        return zongtang;
    }

    public void setZongtang(String zongtang) {
        this.zongtang = zongtang;
    }

    public String getPicktime() {
        return picktime;
    }

    public void setPicktime(String picktime) {
        this.picktime = picktime;
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

}
