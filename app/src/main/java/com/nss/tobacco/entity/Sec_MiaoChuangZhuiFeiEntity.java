package com.nss.tobacco.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lei on 2016/12/8.
 * 苗床追肥实体
 */

public class Sec_MiaoChuangZhuiFeiEntity implements Serializable{

    private String id;
    private String farmer;
    private String type;
    private String num;
    private String area;
    private String way;
    private String createtime;
    private String detail;
    private String state;//表示所属数据库是否上传
    private String photopath;
    public Sec_MiaoChuangZhuiFeiEntity(){}
    public Sec_MiaoChuangZhuiFeiEntity(String id, String farmer, String type, String num, String area, String way, String createtime, String detail, String state,String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.type = type;
        this.num = num;
        this.area = area;
        this.way = way;
        this.createtime = createtime;
        this.detail = detail;
        this.state = state;
        this.photopath=photopath;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
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


    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
    public String getPhotopath() {
        return this.photopath;
    }
}
