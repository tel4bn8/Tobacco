package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 成熟采收表
 */
public class Sec_CaishouEntity implements Serializable{

    private String id;
    private String farmer;
    private String starttime;
    private String endtime;
    private String pick;//是否按成熟度采收
    private String same;//成熟度是否一致
    private String kangci;
    private String ganci;
    private String xianyanfenlei;
    private String zhuanyecaiji;
    private String detail;
    private String state;
    private String photopath;

    public Sec_CaishouEntity() {
    }

    public Sec_CaishouEntity(String id, String farmer, String starttime, String endtime, String pick, String same, String kangci,
                             String ganci, String xianyanfenlei, String zhuanyecaiji, String detail, String state, String photopath) {
        this.id = id;
        this.farmer = farmer;
        this.starttime = starttime;
        this.endtime = endtime;
        this.pick = pick;
        this.same = same;
        this.kangci = kangci;
        this.ganci = ganci;
        this.xianyanfenlei = xianyanfenlei;
        this.zhuanyecaiji = zhuanyecaiji;
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

    public String getPick() {
        return pick;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public String getSame() {
        return same;
    }

    public void setSame(String same) {
        this.same = same;
    }

    public String getKangci() {
        return kangci;
    }

    public void setKangci(String kangci) {
        this.kangci = kangci;
    }

    public String getGanci() {
        return ganci;
    }

    public void setGanci(String ganci) {
        this.ganci = ganci;
    }

    public String getXianyanfenlei() {
        return xianyanfenlei;
    }

    public void setXianyanfenlei(String xianyanfenlei) {
        this.xianyanfenlei = xianyanfenlei;
    }

    public String getZhuanyecaiji() {
        return zhuanyecaiji;
    }

    public void setZhuanyecaiji(String zhuanyecaiji) {
        this.zhuanyecaiji = zhuanyecaiji;
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