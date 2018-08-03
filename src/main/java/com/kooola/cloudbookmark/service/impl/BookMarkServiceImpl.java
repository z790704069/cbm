package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.common.exception.MyException;
import com.kooola.cloudbookmark.dao.BookMarkMapper;
import com.kooola.cloudbookmark.domain.BookMark;
import com.kooola.cloudbookmark.service.BookMarkService;
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

    @Override
    public int pointPraise(Integer bmid, boolean up) {
        BookMark bookMark = bookMarkMapper.selectByPrimaryKey(bmid);
        if(null == bookMark){
            throw new MyException(ResultConstant.CBM_BOOKMARK_NOT_EXIST);
        }
        Long praiseNum = bookMark.getPointPraiseNum();
        //根据up的值来决定点赞数是 +1 还是 -1
        if(up){
            praiseNum = praiseNum + 1;
        }else {
            praiseNum = praiseNum - 1 < 0 ? 0 : praiseNum - 1;
        }
        bookMark.setPointPraiseNum(praiseNum);
        bookMarkMapper.updatePointPraiseById(bookMark);
        return 0;
    }

    @Override
    public int read(Integer bmid, boolean read) {
        BookMark bookMark = bookMarkMapper.selectByPrimaryKey(bmid);
        if(null == bookMark){
            throw new MyException(ResultConstant.CBM_BOOKMARK_NOT_EXIST);
        }
        Integer isRead = bookMark.getIsRead();
        Integer isReadParam = read ? 1 : 0;
        //如果预设值的值跟数据库中相同则不需要操作数据库
        if(isRead == isReadParam){
            return 0;
        }
        bookMark.setIsRead(isReadParam);
        bookMarkMapper.updateIsReadById(bookMark);
        return 0;
    }
}
