package com.kooola.cloudbookmark.domain;

import java.io.Serializable;

/**
 * Created by march on 2018/7/26.
 * 用户类
 */
public class User implements Serializable{
    /**
     * 主键ID
     */
    private Long uid;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱，用于登录
     */
    private String email;
    /**
     * 注册时的时间戳
     */
    private Long createTime;
    /**
     * 资料更新时间戳
     */
    private Long updateTime;
    /**
     * 上次登录时间戳
     */
    private Long lastLoginTime;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 自我简介
     */
    private String introduction;
    /**
     * 盐，用于增强密码加密
     */
    private String salt;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
