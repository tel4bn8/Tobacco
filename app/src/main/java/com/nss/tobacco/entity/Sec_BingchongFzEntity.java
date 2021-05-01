package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建病虫防治的表
 */
public class Sec_BingchongFzEntity implements Serializable{
    private String id;
    private String farmer;
    private String yaopin;
    private String nongdu;
    private String way;
    private String type;
    private String rate;
    private String area;
    private String loss;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;
    public Sec_BingchongFzEntity(){
    }

    public Sec_BingchongFzEntity(String id, String farmer, String yaopin, String nongdu, String way, String type, String rate, String area, String loss, String createtime, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.yaopin = yaopin;
        this.nongdu = nongdu;
        this.way = way;
        this.type = type;
        this.rate = rate;
        this.area = area;
        this.loss = loss;
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

    public String getYaopin() {
        return yaopin;
    }

    public void setYaopin(String yaopin) {
        this.yaopin = yaopin;
    }

    public String getNongdu() {
        return nongdu;
    }

    public void setNongdu(String nongdu) {
        this.nongdu = nongdu;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
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
