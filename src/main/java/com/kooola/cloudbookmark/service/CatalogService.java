package com.kooola.cloudbookmark.service;

import com.kooola.cloudbookmark.domain.Catalog;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
public interface CatalogService {
    /**
     * 获取指定用户的所有目录
     * @param uid
     * @return
     */
    public ArrayList<Catalog> getCatalogsByUser(Integer uid, boolean tree);


    /**
     * 添加目录
     * @param catalog
     * @return
     */
    public int addCatalog(Catalog catalog);
}
