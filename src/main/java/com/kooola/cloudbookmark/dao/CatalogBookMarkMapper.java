package com.kooola.cloudbookmark.dao;

import com.kooola.cloudbookmark.domain.CatalogBookMark;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
public interface CatalogBookMarkMapper {
    public ArrayList<CatalogBookMark> selectByCatalogId(Integer catalogId);
}
