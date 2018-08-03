package com.kooola.cloudbookmark.domain;

/**
 * Created by march on 2018/8/3.
 * 目录（分类）书签映射类
 */
public class CatalogBookMark {
    /**
     * 主键ID
     */
    private Long clbmid;
    /**
     * 目录（分类）ID
     */
    private Long catalogid;
    /**
     * 书签ID
     */
    private Long bmid;
    /**
     * 是否逻辑删除
     */
    private Integer logicdelete;

    public Long getClbmid() {
        return clbmid;
    }

    public void setClbmid(Long clbmid) {
        this.clbmid = clbmid;
    }

    public Long getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Long catalogid) {
        this.catalogid = catalogid;
    }

    public Long getBmid() {
        return bmid;
    }

    public void setBmid(Long bmid) {
        this.bmid = bmid;
    }

    public Integer getLogicdelete() {
        return logicdelete;
    }

    public void setLogicdelete(Integer logicdelete) {
        this.logicdelete = logicdelete;
    }
}
