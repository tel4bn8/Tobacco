package com.nss.tobacco.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 *
 */
@Table(name = "UserInfo")
public class UserInfo {

    /**
     * id : 1
     * username : admin
     * password : 123
     * realname : 张三
     * idcard : 411522199210017880
     * phone : 18810061781
     * email : 272727@qq.com
     * age : 18
     * sex : 男
     * companyid : null
     * level : 4
     * createtime : null
     * status : 1
     * type : null
     */
    @Column(name = "id", isId = true)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "realname")
    private String realname;
    @Column(name = "idcard")
    private String idcard;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;
    @Column(name = "sex")
    private String sex;
    @Column(name = "companyid")
    private Object companyid;
    @Column(name = "level")
    private String level;
    @Column(name = "createtime")
    private Object createtime;
    @Column(name = "status")
    private String status;
    @Column(name = "type")
    private Object type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Object companyid) {
        this.companyid = companyid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Object createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", companyid=" + companyid +
                ", level='" + level + '\'' +
                ", createtime=" + createtime +
                ", status='" + status + '\'' +
                ", type=" + type +
                '}';
    }
}
