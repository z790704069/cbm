package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.dao.CatalogMapper;
import com.kooola.cloudbookmark.domain.Catalog;
import com.kooola.cloudbookmark.service.CatalogService;
import javafx.scene.chart.ChartBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by march on 2018/8/3.
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{
    @Autowired
    private CatalogMapper catalogMapper;

    /**
     * 获取指定用户的所有目录
     * @param uid 用户ID
     * @param tree 是否按照树形结构返回
     * @return
     */
    @Override
    public ArrayList<Catalog> getCatalogsByUser(Integer uid, boolean tree) {
        ArrayList<Catalog> catalogs = catalogMapper.selectByUid(uid);
        if(tree){
            //目录按照树形结构返回
        }
        return catalogs;
    }

    @Override
    public int addCatalog(Catalog catalog) {
        //默认顶级目录,即没有父目录
        //当前父目录layer
        String fatherLayer = null;
        //当前目录level
        Long level = 1l;
        //父目录类信息
        Catalog father = null;

        //如果有父目录存在
        if(null != catalog.getFather()){
            father = catalogMapper.selectByPrimaryKey(catalog.getFather().intValue());
            if(null != father){
                fatherLayer = father.getLayer();
                level = father.getLevel() + 1;  //当前目录level = 父目录level + 1
            }
        }

        //设置当前目录layer
        ArrayList<Catalog> catalogs =
                catalogMapper.selectByUidAndLevel(catalog.getUid().intValue(), level);
        if(0 == catalogs.size()){
            //当前目录级没有目录，根据父目录layer以及当前目录级别中的最后目录的layer计算
            catalog.setLayer(createCatalogName(fatherLayer, null));
        }else {
            //当前目录级有目录，根据父目录layer以及当前目录级别中的最后目录的layer计算
            catalog.setLayer(createCatalogName(fatherLayer, catalogs.get(0).getLayer()));
        }

        //设置当前目录level
        catalog.setLevel(level);
        catalog.setCreateTime(System.currentTimeMillis() / 1000);
        catalogMapper.insert(catalog);
        return 0;
    }

    /**
     * 新目录的layer值构建
     * -001
     * ---001001
     * ---001002
     * ------001002001
     * ------001002002
     * -002
     * ---002001
     * -----002001001
     * -----002001002
     * ---002002
     * ---002003
     * @param fatherLayer
     * @param currentLastLayer
     * @return
     */
    private String createCatalogName(String fatherLayer, String currentLastLayer){
        if(null == fatherLayer && null == currentLastLayer){
            //当前为顶级目录，并且顶级目录中暂无目录
            return "001";
        }
        if(null != fatherLayer && null == currentLastLayer){
            //有父目录，但当前目录级别中无目录
            return fatherLayer + "001";
        }
        //剩下情况为当前目录级别中有目录，直接在最大值的layer上+1即可
        int strLen = currentLastLayer.length();
        String tmp = currentLastLayer.substring(strLen - 3, strLen);
        int num = Integer.valueOf(tmp);
        num = num + 1;
        tmp = String.valueOf(num);
        switch (tmp.length()){
            case 1:
                tmp = "00" + tmp;
                break;
            case 2:
                tmp = "0" + tmp;
        }
        return currentLastLayer.substring(0, strLen - 3) + tmp;
    }
}
