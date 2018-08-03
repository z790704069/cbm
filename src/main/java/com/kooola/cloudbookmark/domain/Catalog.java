package com.kooola.cloudbookmark.domain;

/**
 * Created by march on 2018/8/3.
 * 书签目录类
 */
public class Catalog {
    /**
     * 主键ID
     */
    private Long catalogid;

    /**
     * 用户ID
     */
    private Long uid;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 上次目录ID，如果为空说明是顶级目录
     */
    private Long father;

    /**
     * 目录级别 例如：1标识顶级目录
     */
    private Long level;

    /**
     * 目录级别辅助,用于方便标识具体父级目录  例如：001 / 001001 / 001001001 / 001001002
     */
    private String layer;

    /**
     * 目录创建的时间戳
     */
    private Long createTime;

    /**
     * 否逻辑删除 0：否 1：是
     */
    private Integer logicdelete = 0;

    public Long getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Long catalogid) {
        this.catalogid = catalogid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFather() {
        return father;
    }

    public void setFather(Long father) {
        this.father = father;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
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
