package com.kooola.cloudbookmark.domain;

import java.io.Serializable;

/**
 * Created by march on 2018/8/14.
 */
public class ElasticCBMEntity implements Serializable {
    public static final String INDEX_NAME = "cbm";

    public static final String TYPE = "bookmark";

    private Long bmid;

    private String title;

    private String host;

    private String description;


    public Long getBmid() {
        return bmid;
    }

    public void setBmid(Long bmid) {
        this.bmid = bmid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ElasticCBMEntity(Long bmid, String title, String host, String description){
        this.bmid = bmid;
        this.title = title;
        this.host = host;
        this.description = description;
    }
}
