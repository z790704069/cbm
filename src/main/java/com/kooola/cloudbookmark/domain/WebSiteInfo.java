package com.kooola.cloudbookmark.domain;

/**
 * Created by march on 2018/8/1.
 * 网站信息
 */
public class WebSiteInfo {
    /**
     * 网站标题
     */
    private String title;
    /**
     * 网站logo url
     */
    private String logoUrl;

    /**
     * 网站域名
     */
    private String host;

    public WebSiteInfo(String title, String logoUrl, String host){
        this.title = title;
        this.logoUrl = logoUrl;
        this.host = host;
    }

    public String toString(){
        return "title: " + title + ", logoUrl: " + logoUrl + ", host: " + host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDomain() {
        return host;
    }

    public void setDomain(String host) {
        this.host = host;
    }
}
