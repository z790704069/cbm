package com.kooola.cloudbookmark.controller;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLoacl;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.domain.Catalog;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping(value = "catalogs")
    @ResponseBody
    public RestResponseModel getCatalogs(){
        User user = UserThreadLoacl.getUser();
        ArrayList<Catalog> catalogs = catalogService.getCatalogsByUser(user.getUid().intValue(), true);
        return new RestResponseModel(ResultConstant.SUCCESS, catalogs);
    }

    @PostMapping(value = "catalogs")
    @ResponseBody
    public RestResponseModel addCatalog(Catalog catalog){
        User user = UserThreadLoacl.getUser();
        catalog.setUid(user.getUid());
        try{
            catalogService.addCatalog(catalog);
        }catch (Exception e){
            return new RestResponseModel(e.getMessage());
        }
        return new RestResponseModel(ResultConstant.SUCCESS);
    }
}
