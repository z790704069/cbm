package com.kooola.cloudbookmark.utils;

import com.kooola.cloudbookmark.domain.WebSiteInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

/**
 * Created by march on 2018/8/1.
 * html页面元素解析类
 */
public class HtmlParserUtil {
    public static WebSiteInfo process(String url){
        Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (Exception e){
            return null;
        }
        String title = document.title();
        String logoUrl = null;
        Element element = document.head().select("link[href~=.*\\.(ico|png)]").first();
        if (element == null) {
            element = document.head().select("meta[itemprop=image]").first();
            if (element != null) {
                logoUrl = element.attr("content");
            }
        } else {
            logoUrl = element.attr("href");
        }
        return new WebSiteInfo(title, logoUrl,  getHost(url));
    }


    /**
     * 获取域名
     * @param urlStr
     * @return
     */
    public static String getHost(String urlStr){
        String host = urlStr;
        try {
            URL url = new URL(urlStr);
            host=url.getHost();
        } catch (Exception e) {

        }
        return host;
    }


    public static void main(String[] args){

    }
}
