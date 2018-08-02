package com.kooola.cloudbookmark.domain;

/**
 * Created by march on 2018/8/1.
 * 书签类
 */
public class BookMark {
    /**
     * 主键id
     */
    private Long bmid;

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 书签指向的链接
     */
    private String url;
    /**
     * 书签标题
     */
    private String title;
    /**
     * 书签描述
     */
    private String description;
    /**
     * logo的链接
     */
    private String logoUrl;
    /**
     * 点赞数
     */
    private Long pointPraiseNum;
    /**
     * 创建的时间戳
     */
    private Long createTime;
    /**
     * 是否逻辑删除 0：否 1：是
     */
    private Integer logicdelete;

    public Long getBmid() {
        return bmid;
    }

    public void setBmid(Long bmid) {
        this.bmid = bmid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getPointPraiseNum() {
        return pointPraiseNum;
    }

    public void setPointPraiseNum(Long pointPraiseNum) {
        this.pointPraiseNum = pointPraiseNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Integer logicdelete) {
        this.logicdelete = logicdelete;
    }
}
