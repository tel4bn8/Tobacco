package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * 创建烘烤的表
 */

public class Sec_HongkaoEntity implements Serializable{
    private String id;
    private String farmer;
    private String area;
    private String beforeweight;
    private String afterweight;
    private String electric;
    private String coal;//耗煤量
    private String zhuanyehongkao;
    private String quqingquza;
    private String money;
    private String createtime;
    private String detail;
    private String state;
    private String photopath;

    public Sec_HongkaoEntity() {}

    public Sec_HongkaoEntity(String id, String farmer, String area, String beforeweight, String afterweight, String electric,
                             String coal, String zhuanyehongkao, String quqingquza, String money, String createtime, String detail,String photopath, String state) {
        this.id = id;
        this.farmer = farmer;
        this.area = area;
        this.beforeweight = beforeweight;
        this.afterweight = afterweight;
        this.electric = electric;
        this.coal = coal;
        this.zhuanyehongkao = zhuanyehongkao;
        this.quqingquza = quqingquza;
        this.money = money;
        this.createtime = createtime;
        this.detail = detail;
        this.photopath = photopath;
        this.state = state;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBeforeweight() {
        return beforeweight;
    }

    public void setBeforeweight(String beforeweight) {
        this.beforeweight = beforeweight;
    }

    public String getAfterweight() {
        return afterweight;
    }

    public void setAfterweight(String afterweight) {
        this.afterweight = afterweight;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }

    public String getCoal() {
        return coal;
    }

    public void setCoal(String coal) {
        this.coal = coal;
    }

    public String getZhuanyehongkao() {
        return zhuanyehongkao;
    }

    public void setZhuanyehongkao(String zhuanyehongkao) {
        this.zhuanyehongkao = zhuanyehongkao;
    }

    public String getQuqingquza() {
        return quqingquza;
    }

    public void setQuqingquza(String quqingquza) {
        this.quqingquza = quqingquza;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
