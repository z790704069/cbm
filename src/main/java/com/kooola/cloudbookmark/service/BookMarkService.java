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


    /**
     * 为指定文章点赞
     * @param bmid 书签ID
     * @param up true:点赞+1 false:点赞-1
     * @return
     */
    public int pointPraise(Integer bmid, boolean up);


    /**
     * 将书签标记为已读或未读
     * @param bmid 书签ID
     * @param read true:已读 false:未读
     * @return
     */
    public int read(Integer bmid, boolean read);
}
