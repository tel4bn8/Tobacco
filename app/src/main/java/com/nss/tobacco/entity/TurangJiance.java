package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * turangjiance
 */

public class TurangJiance implements Serializable{
    private String id;
    private String wanggecode;
    private String year;
    private String youjizhi;
    private String ph;
    private String n;
    private String p;
    private String k;
    private String cl;
    private String picktime;
    private String technician;
    private String detail;
    private String state;
    private String photopath;

    public TurangJiance(){}

    public TurangJiance(String id, String wanggecode, String year, String youjizhi, String ph, String n,
                        String p, String k, String cl, String picktime, String technician, String detail, String state, String photopath) {
        this.id = id;
        this.wanggecode = wanggecode;
        this.year = year;
        this.youjizhi = youjizhi;
        this.ph = ph;
        this.n = n;
        this.p = p;
        this.k = k;
        this.cl = cl;
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

    public String getYoujizhi() {
        return youjizhi;
    }

    public void setYoujizhi(String youjizhi) {
        this.youjizhi = youjizhi;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
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

    @Override
    public String toString() {
        return "TurangJiance{" +
                "id='" + id + '\'' +
                ", wanggecode='" + wanggecode + '\'' +
                ", year='" + year + '\'' +
                ", youjizhi='" + youjizhi + '\'' +
                ", ph='" + ph + '\'' +
                ", n='" + n + '\'' +
                ", p='" + p + '\'' +
                ", k='" + k + '\'' +
                ", cl='" + cl + '\'' +
                ", picktime='" + picktime + '\'' +
                ", technician='" + technician + '\'' +
                ", detail='" + detail + '\'' +
                ", state='" + state + '\'' +
                ", photopath='" + photopath + '\'' +
                '}';
    }
}
