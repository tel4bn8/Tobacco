package com.nss.tobacco.entity;

/**
 * 烟农姓名
 */

public class FarmerEntity {
    private String name;

    public FarmerEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}