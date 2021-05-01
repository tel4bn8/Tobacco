package com.nss.tobacco.entity;

import java.io.Serializable;

/**
 * hongkaoshi
 */

public class Sec_HongkaoshiEntity implements Serializable {
    private String id;
    private String name;
    private String tel;
    private String xiaoguo;//烘烤效果
    private String createtime;
    private String detail;
    private String technician;
    private String state;
    private String photopath;

    public Sec_HongkaoshiEntity() {}

    public Sec_HongkaoshiEntity(String id, String name, String tel, String xiaoguo, String createtime, String detail, String technician, String state, String photopath) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.xiaoguo = xiaoguo;
        this.createtime = createtime;
        this.detail = detail;
        this.technician = technician;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getXiaoguo() {
        return xiaoguo;
    }

    public void setXiaoguo(String xiaoguo) {
        this.xiaoguo = xiaoguo;
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

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
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
