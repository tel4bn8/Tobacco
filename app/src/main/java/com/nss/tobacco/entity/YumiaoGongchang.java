package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * yumiaogongchang
 */

public class YumiaoGongchang implements Serializable{
    private String id;
    private String name;
    private String village;
    private String structure;
    private String area;
    private String miaosum;
    private String dapengnum;
    private String buildtime;
    private String technician;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public YumiaoGongchang(){}

    public YumiaoGongchang(String id, String name, String village, String structure, String area, String miaosum, String dapengnum, String buildtime,
                           String technician, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.name = name;
        this.village = village;
        this.structure = structure;
        this.area = area;
        this.miaosum = miaosum;
        this.dapengnum = dapengnum;
        this.buildtime = buildtime;
        this.technician = technician;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMiaosum() {
        return miaosum;
    }

    public void setMiaosum(String miaosum) {
        this.miaosum = miaosum;
    }

    public String getDapengnum() {
        return dapengnum;
    }

    public void setDapengnum(String dapengnum) {
        this.dapengnum = dapengnum;
    }

    public String getBuildtime() {
        return buildtime;
    }

    public void setBuildtime(String buildtime) {
        this.buildtime = buildtime;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
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