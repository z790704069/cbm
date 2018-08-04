package com.kooola.cloudbookmark.dao;

import com.kooola.cloudbookmark.domain.BookMark;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by march on 2018/8/1.
 */
public interface BookMarkMapper {

    public BookMark selectByPrimaryKey(Integer bmid);

    public int insert(BookMark bookMark);

    public int deleteByPrimaryKey(Integer bmid);

    public ArrayList<BookMark> selectByUid(Integer uid);

    public int updatePointPraiseById(BookMark bookMark);

    public int updateIsReadById(BookMark bookMark);

    public ArrayList<BookMark> selectInBmIds(@Param("bmids") ArrayList<Integer> bmids);
}
