package com.kooola.cloudbookmark.dao;

import com.kooola.cloudbookmark.domain.Catalog;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
public interface CatalogMapper {
    public ArrayList<Catalog> selectByUid(Integer uid);

    public Catalog selectByPrimaryKey(Integer catalogid);

    public int insert(Catalog catalog);

    /**
     * 获取指定用户指定目录级别下的所有目录
     * @param uid
     * @param level
     * @return
     */
    public ArrayList<Catalog> selectByUidAndLevel(Integer uid, Long level);
}
