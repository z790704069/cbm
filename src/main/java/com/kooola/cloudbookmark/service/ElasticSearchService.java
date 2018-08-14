package com.kooola.cloudbookmark.service;

import com.kooola.cloudbookmark.domain.ElasticCBMEntity;

import java.util.List;

/**
 * Created by march on 2018/8/14.
 */
public interface ElasticSearchService {
    void saveElasticCBMEntity(ElasticCBMEntity elasticCBMEntity);

    List<ElasticCBMEntity> searchElasticCBMEntity(String searchContent);

}
