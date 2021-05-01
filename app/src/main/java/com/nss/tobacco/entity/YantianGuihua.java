package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * yantianguihua
 */

public class YantianGuihua implements Serializable{

    private String id;
    private String village;
    private String year;
    private String area;
    private String lng;
    private String lat;
    private String dixing;
    private String soil;
    private String feili;
    private String moditime;
    private String technician;
    private String detail;
    private String state;
    private String photopath;


    public YantianGuihua(){}

    public YantianGuihua(String id, String village, String year, String area, String lng, String lat, String dixing,
                         String soil, String feili, String moditime, String technician, String detail, String state, String photopath) {
        this.id = id;
        this.village = village;
        this.year = year;
        this.area = area;
        this.lng = lng;
        this.lat = lat;
        this.dixing = dixing;
        this.soil = soil;
        this.feili = feili;
        this.moditime = moditime;
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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public String getFeili() {
        return feili;
    }

    public void setFeili(String feili) {
        this.feili = feili;
    }

    public String getModitime() {
        return moditime;
    }

    public void setModitime(String moditime) {
        this.moditime = moditime;
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
