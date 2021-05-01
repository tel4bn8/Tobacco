package com.nss.tobacco.entity;

/**
 * wanggeID
 */

public class WanggeID {
    private String name;

    public WanggeID(String name) {
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
        return "WanggeID{" +
                "name='" + name + '\'' +
                '}';
    }
}