package com.kooola.cloudbookmark.service.impl;

import com.kooola.cloudbookmark.domain.ElasticCBMEntity;
import com.kooola.cloudbookmark.service.ElasticSearchService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import io.searchbox.core.Index;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by march on 2018/8/14.
 */
@Service("elasticSearchService")
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Autowired
    private JestClient jestClient;

    @Override
    public void saveElasticCBMEntity(ElasticCBMEntity elasticCBMEntity) {
        Index index = new Index.Builder(elasticCBMEntity).
                index(ElasticCBMEntity.INDEX_NAME).type(ElasticCBMEntity.TYPE).build();
        JestResult jestResult = null;
        try {
            jestResult = jestClient.execute(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ElasticCBMEntity> searchElasticCBMEntity(String searchContent) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.commonTermsQuery("title",searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(ElasticCBMEntity.INDEX_NAME).addType(ElasticCBMEntity.TYPE).build();

        try {
            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(ElasticCBMEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
