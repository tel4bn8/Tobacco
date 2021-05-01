package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * yantiandangan
 */

public class YantianDangan implements Serializable{
    private String id;
    private String farmer;
    private String year;
    private String area;
    private String lng;
    private String lat;
    private String src;//土地承包来源
    private String dixing;
    private String soil;
    private String precrop;//前茬作物
    private String water;
    private String dilishuiping;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public YantianDangan(){}

    public YantianDangan(String id, String farmer, String year, String area, String lng, String lat, String src, String dixing, String soil, String precrop, String water,
                         String dilishuiping, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.year = year;
        this.area = area;
        this.lng = lng;
        this.lat = lat;
        this.src = src;
        this.dixing = dixing;
        this.soil = soil;
        this.precrop = precrop;
        this.water = water;
        this.dilishuiping = dilishuiping;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDixing() {
        return dixing;
    }

    public void setDixing(String dixing) {
        this.dixing = dixing;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getPrecrop() {
        return precrop;
    }

    public void setPrecrop(String precrop) {
        this.precrop = precrop;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getDilishuiping() {
        return dilishuiping;
    }

    public void setDilishuiping(String dilishuiping) {
        this.dilishuiping = dilishuiping;
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
