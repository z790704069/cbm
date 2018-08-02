package com.kooola.cloudbookmark.service;

import com.kooola.cloudbookmark.domain.BookMark;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/1.
 */
public interface BookMarkService {
    /**
     * 获取指定用户的所有标签
     * @param uid
     * @return
     */
    public ArrayList<BookMark> getBookMarksByUser(Integer uid);

    /**
     * 添加书签
     * @param bookMark
     * @return
     */
    public int addBookMark(BookMark bookMark);
}
