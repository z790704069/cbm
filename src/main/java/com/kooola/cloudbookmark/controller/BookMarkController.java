package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseBo;
import com.kooola.cloudbookmark.common.UserThreadLoacl;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.domain.BookMark;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.domain.WebSiteInfo;
import com.kooola.cloudbookmark.service.BookMarkService;
import com.kooola.cloudbookmark.utils.HtmlParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by march on 2018/8/1.
 */

@RestController
public class BookMarkController {
    @Autowired
    private BookMarkService bookMarkService;


    /**
     * 添加书签(通过传入的url进行爬取该网站的信息形成书签信息并保存)
     * @param url  待加入书签的原始url
     * @return
     */
    @PostMapping(value = "bookmarks")
    @ResponseBody
    public RestResponseBo addBookMarkWithUrl(@RequestParam String url, HttpServletRequest request){
        User user = UserThreadLoacl.getUser();
        WebSiteInfo webSiteInfo = null;
        try{
            webSiteInfo = HtmlParserUtil.process(url);
        }catch (Exception e){
            return new RestResponseBo(e.getMessage());
        }
        BookMark bookMark = new BookMark();
        bookMark.setTitle(webSiteInfo.getTitle());
        bookMark.setUrl(url);
        bookMark.setLogoUrl(webSiteInfo.getLogoUrl());
        bookMark.setHost(webSiteInfo.getHost());
        bookMark.setUid(user.getUid());
        bookMarkService.addBookMark(bookMark);
        return new RestResponseBo(ResultConstant.SUCCESS, bookMark);
    }


    /**
     * 获取当前用户所有书签
     * @return
     */
    @GetMapping(value = "bookmarks")
    @ResponseBody
    public RestResponseBo getBookMarks(){
        User user = UserThreadLoacl.getUser();
        ArrayList<BookMark> bookMarks = bookMarkService.getBookMarksByUser(user.getUid().intValue());
        return new RestResponseBo(ResultConstant.SUCCESS, bookMarks);
    }


    /**
     * 指定书签点赞、取消点赞
     * @param bmid
     * @param up up=ture:点赞数加1  up=false 点赞数减1
     * @return
     */
    @PutMapping(value = "bookmarks/{bmid}/pointPraise")
    @ResponseBody
    public RestResponseBo bookmarkRead(@PathVariable Integer bmid, @RequestParam boolean up){
        User user = UserThreadLoacl.getUser();
        bookMarkService.pointPraise(bmid, up);
        return new RestResponseBo(ResultConstant.SUCCESS);
    }


}
