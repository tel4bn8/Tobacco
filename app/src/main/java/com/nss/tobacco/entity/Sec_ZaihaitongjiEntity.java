package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/8 0008.
 * 创建自然灾害统计的表
 */
@Table(name = "zaihaitongji")
public class Sec_ZaihaitongjiEntity {
    @Column(name = "id",isId = true, autoGen = true)
    private String id;
    @Column(name = "farmer")
    private String farmer;
    @Column(name = "area")
    private String area;
    @Column(name = "type")
    private String type;
    @Column(name = "detail")
    private String detail;

    public Sec_ZaihaitongjiEntity() {
    }

    public Sec_ZaihaitongjiEntity(String id, String farmer, String area, String type, String detail) {
        this.id = id;
        this.farmer = farmer;
        this.area = area;
        this.type = type;
        this.detail = detail;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Sec_ZaihaitongjiEntity{" +
                "id='" + id + '\'' +
                ", farmer='" + farmer + '\'' +
                ", area='" + area + '\'' +
                ", type='" + type + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
