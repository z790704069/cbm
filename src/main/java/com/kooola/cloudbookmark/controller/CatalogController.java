package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLocal;
import com.kooola.cloudbookmark.common.constants.NormalConstant;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.domain.BookMark;
import com.kooola.cloudbookmark.domain.Catalog;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.BookMarkService;
import com.kooola.cloudbookmark.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private BookMarkService bookMarkService;

    @GetMapping(value = "catalogs")
    @ResponseBody
    public RestResponseModel getCatalogs(){
        User user = UserThreadLocal.getUser();
        ArrayList<Catalog> catalogs = catalogService.getCatalogsByUser(user.getUid().intValue(), true);
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, catalogs);
    }

    @PostMapping(value = "catalogs")
    @ResponseBody
    public RestResponseModel addCatalog(Catalog catalog){
        User user = UserThreadLocal.getUser();
        catalog.setUid(user.getUid());
        try{
            catalogService.addCatalog(catalog);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS);
    }

    /**
     * 获取目录（分类）下所有书签
     * @param catalogId
     * @return
     */
    @GetMapping(value = "catalogs/{catalogId}/bookmarks")
    @ResponseBody
    public RestResponseModel getBookMarksByCatalog(@PathVariable Integer catalogId,
                                                   @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "limit", defaultValue = NormalConstant.CBM_PAGE_LIMIT_SIZE) int limit){
        ArrayList<BookMark> bookMarks = null;
        try{
            bookMarks = bookMarkService.getBookMarksByCatalog(catalogId, page, limit);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.CBM_SUCCESS, bookMarks);
    }
}
