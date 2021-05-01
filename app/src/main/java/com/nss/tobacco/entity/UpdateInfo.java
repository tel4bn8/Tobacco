package com.nss.tobacco.entity;

/**
 * appupdata
 */

public class UpdateInfo {

    /**
     * version : 1.0
     * path : 222
     * createtime : 2016-11-18
     */

    private String version;
    private String path;
    private String createtime;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "version='" + version + '\'' +
                ", path='" + path + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
