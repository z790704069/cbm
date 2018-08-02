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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by march on 2018/8/1.
 */

@RestController
@RequestMapping(value = "/bookmark")
public class BookMarkController {
    @Autowired
    private BookMarkService bookMarkService;


    /**
     * 添加书签
     * @param url  待加入书签的原始url
     * @return
     */
    @PostMapping(value = "add")
    @ResponseBody
    public RestResponseBo addBookMarkWithUrl(@RequestParam String url){
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
     * 添加书签
     * @param bookMark
     * @return
     */
//    @PostMapping(value = "add")
//    @ResponseBody
//    public RestResponseBo add(BookMark bookMark){
//        bookMarkService.addBookMark(bookMark);
//        return new RestResponseBo(ResultConstant.SUCCESS);
//    }

    /**
     * 获取当前用户所有书签
     * @return
     */
    @GetMapping(value = "getBookMark")
    @ResponseBody
    public RestResponseBo getBookMark(){
        User user = UserThreadLoacl.getUser();
        ArrayList<BookMark> bookMarks = bookMarkService.getBookMarksByUser(user.getUid().intValue());
        return new RestResponseBo(ResultConstant.SUCCESS, bookMarks);
    }
}
