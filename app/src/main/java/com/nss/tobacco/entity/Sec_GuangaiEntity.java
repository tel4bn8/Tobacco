package com.nss.tobacco.entity;

import com.nss.tobacco.daos.Sec_GuangaiDao;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建灌溉的表
 */
public class Sec_GuangaiEntity implements Serializable{
    private String id;
    private String farmer;
    private String worktime;
    private String way;
    private String area;
    private String detail;
    private String state;
    private String photopath;

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Sec_GuangaiEntity() {
    }

    public Sec_GuangaiEntity(String id, String farmer, String worktime, String way, String area, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.worktime = worktime;
        this.way = way;
        this.area = area;
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

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
