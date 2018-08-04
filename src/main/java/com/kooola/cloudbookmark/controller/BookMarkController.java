package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLoacl;
import com.kooola.cloudbookmark.common.constants.NormalConstant;
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
    public RestResponseModel addBookMarkWithUrl(@RequestParam String url, @RequestParam Integer catalogId){
        User user = UserThreadLoacl.getUser();
        WebSiteInfo webSiteInfo = null;
        try{
            webSiteInfo = HtmlParserUtil.process(url);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        BookMark bookMark = new BookMark();
        bookMark.setTitle(webSiteInfo.getTitle());
        bookMark.setUrl(url);
        bookMark.setLogoUrl(webSiteInfo.getLogoUrl());
        bookMark.setHost(webSiteInfo.getHost());
        bookMark.setUid(user.getUid());
        bookMarkService.addBookMark(bookMark);
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, bookMark);
    }


    /**
     * 获取当前用户所有书签
     * @return
     */
    @GetMapping(value = "bookmarks")
    @ResponseBody
    public RestResponseModel getBookMarks(@RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "limit", defaultValue = NormalConstant.CBM_PAGE_LIMIT_SIZE) int limit){
        User user = UserThreadLoacl.getUser();
        ArrayList<BookMark> bookMarks = bookMarkService.getBookMarksByUser(user.getUid().intValue(), page, limit);
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, bookMarks);
    }


    /**
     * 指定书签点赞或取消点赞
     * @param bmid
     * @param up up=ture:点赞数加1  up=false 点赞数减1
     * @return
     */
    @PutMapping(value = "bookmarks/{bmid}/pointPraise")
    @ResponseBody
    public RestResponseModel praise(@PathVariable Integer bmid, @RequestParam boolean up){
        try{
            bookMarkService.pointPraise(bmid, up);
        }catch (Exception e){
            return new RestResponseModel(ResultConstant.CBM_BOOKMARK_NOT_EXIST);
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }

    /**
     * 指定书签标记为已读或未读
     * @param bmid
     * @param isRead true:已读 false:未读
     * @return
     */
    @PutMapping(value = "bookmarks/{bmid}/read")
    @ResponseBody
    public RestResponseModel read(@PathVariable Integer bmid, @RequestParam boolean isRead){
        try{
            bookMarkService.read(bmid, isRead);
        }catch (Exception e){
            return new RestResponseModel(ResultConstant.CBM_BOOKMARK_NOT_EXIST);
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }

}
