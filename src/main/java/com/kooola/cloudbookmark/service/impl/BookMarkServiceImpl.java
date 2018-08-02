package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.dao.BookMarkMapper;
import com.kooola.cloudbookmark.domain.BookMark;
import com.kooola.cloudbookmark.service.BookMarkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/1.
 */
@Service("bookMarkService")
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    private BookMarkMapper bookMarkMapper;

    @Override
    public ArrayList<BookMark> getBookMarksByUser(Integer uid) {
        return bookMarkMapper.selectByUid(uid);
    }

    @Override
    public int addBookMark(BookMark bookMark) {
        if(null == bookMark.getCreateTime()){
            bookMark.setCreateTime(System.currentTimeMillis() / 1000);
        }
        bookMarkMapper.insert(bookMark);
        return 0;
    }
}
