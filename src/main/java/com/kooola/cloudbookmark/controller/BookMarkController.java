package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseBo;
import com.kooola.cloudbookmark.common.UserThreadLoacl;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.domain.BookMark;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.BookMarkService;
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

    @PostMapping(value = "add")
    @ResponseBody
    public RestResponseBo add(BookMark bookMark){
        bookMarkService.addBookMark(bookMark);
        return new RestResponseBo(ResultConstant.SUCCESS);
    }

    @GetMapping(value = "getBookMark")
    @ResponseBody
    public RestResponseBo getBookMark(){
        User user = UserThreadLoacl.getUser();
        ArrayList<BookMark> bookMarks = bookMarkService.getBookMarksByUser(user.getUid().intValue());
        return new RestResponseBo(ResultConstant.SUCCESS, bookMarks);
    }
}
