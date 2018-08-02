package com.kooola.cloudbookmark.utils;

import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.common.exception.MyException;
import com.kooola.cloudbookmark.domain.WebSiteInfo;
import org.apache.commons.lang3.StringUtils;
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
    /**
     * 从url中获取html元素（title、logourl、域名）
     * @param url
     * @return
     */
    public static WebSiteInfo process(String url){
        Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (Exception e){
            throw new MyException(ResultConstant.CBM_HTML_GET_OR_PARSER_FAIL);
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
        if(StringUtils.isNotBlank(logoUrl) ){
            if(logoUrl.startsWith("//")){
                logoUrl = "http:" + logoUrl;
            }else if(!logoUrl.startsWith("http") && !logoUrl.startsWith("/")){
                logoUrl = getHost(url) + "/" + logoUrl;
            }else if(!logoUrl.startsWith("http")){
                logoUrl = getHost(url)+logoUrl;
            }
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
        WebSiteInfo webSiteInfo = HtmlParserUtil.process("https://mp.wexin.qq.com/s?__biz=MzAxOTc0NzExNg==&mid=2665513160&idx=1&sn=d938db4f1a2d62514b57e92fd8d3d749&scene=21#wechat_redirect");
        if(null == webSiteInfo){
            System.out.println("error");
            return;
        }
        System.out.print(webSiteInfo.toString());
    }
}
