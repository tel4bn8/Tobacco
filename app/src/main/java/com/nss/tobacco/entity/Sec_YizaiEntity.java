package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建移栽管理的表
 */
public class Sec_YizaiEntity implements Serializable{
    private String id;
    private String farmer;
    private String type;
    private String area;

    private String rowdis;//行距
    private String betwdis;//株距
    private String gaimo;
    private String way;
    private String fangzhi;
    private String rate;
    private String starttime;
    private String endtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_YizaiEntity() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRowdis() {
        return rowdis;
    }

    public void setRowdis(String rowdis) {
        this.rowdis = rowdis;
    }

    public String getBetwdis() {
        return betwdis;
    }

    public void setBetwdis(String betwdis) {
        this.betwdis = betwdis;
    }

    public String getGaimo() {
        return gaimo;
    }

    public void setGaimo(String gaimo) {
        this.gaimo = gaimo;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getFangzhi() {
        return fangzhi;
    }

    public void setFangzhi(String fangzhi) {
        this.fangzhi = fangzhi;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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
