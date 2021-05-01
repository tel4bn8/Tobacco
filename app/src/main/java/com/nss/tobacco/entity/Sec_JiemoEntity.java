package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建揭膜培土
 */
public class Sec_JiemoEntity implements Serializable{
    private String id;
    private String farmer;
    private String jiemo;
    private String jiemotime;
    private String xiaopeitu;
    private String dapeitu;
    private String area;
    private String height;
    private String paishui;
    private String pianshu;

    private String detail;
    private String state;

    public void setId(String id) {
        this.id = id;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public void setJiemo(String jiemo) {
        this.jiemo = jiemo;
    }

    public void setJiemotime(String jiemotime) {
        this.jiemotime = jiemotime;
    }

    public void setXiaopeitu(String xiaopeitu) {
        this.xiaopeitu = xiaopeitu;
    }

    public void setDapeitu(String dapeitu) {
        this.dapeitu = dapeitu;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setPaishui(String paishui) {
        this.paishui = paishui;
    }

    public void setPianshu(String pianshu) {
        this.pianshu = pianshu;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getId() {
        return id;
    }

    public String getFarmer() {
        return farmer;
    }

    public String getJiemo() {
        return jiemo;
    }

    public String getJiemotime() {
        return jiemotime;
    }

    public String getXiaopeitu() {
        return xiaopeitu;
    }

    public String getDapeitu() {
        return dapeitu;
    }

    public String getArea() {
        return area;
    }

    public String getHeight() {
        return height;
    }

    public String getPaishui() {
        return paishui;
    }

    public String getPianshu() {
        return pianshu;
    }

    public String getDetail() {
        return detail;
    }

    public String getState() {
        return state;
    }

    private String photoPath;

    public Sec_JiemoEntity(){}

    public Sec_JiemoEntity(String id, String farmer, String jiemo, String jiemotime, String xiaopeitu, String dapeitu, String area, String height, String paishui, String pianshu, String detail, String state, String photoPath) {
        this.id = id;
        this.farmer = farmer;
        this.jiemo = jiemo;
        this.jiemotime = jiemotime;
        this.xiaopeitu = xiaopeitu;
        this.dapeitu = dapeitu;
        this.area = area;
        this.height = height;
        this.paishui = paishui;
        this.pianshu = pianshu;
        this.detail = detail;
        this.state = state;
        this.photoPath = photoPath;
    }
}
