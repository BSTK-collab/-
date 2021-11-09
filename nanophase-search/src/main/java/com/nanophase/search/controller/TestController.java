package com.nanophase.search.controller;

import com.nanophase.common.util.GsonUtil;
import com.nanophase.search.entity.Test;
import io.prometheus.client.Collector;
import io.prometheus.client.Histogram;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TransportClient client;

    @GetMapping
    public void test() {
        // 聚合出每个月的各个tags的价格平均值
        SearchResponse searchResponse = client.prepareSearch("product").
                addAggregation(
                        AggregationBuilders.dateHistogram("group_by_month").
                                field("create_date").
                                calendarInterval(DateHistogramInterval.MONTH).
                                subAggregation(
                                        AggregationBuilders.
                                                terms("tag_agg").
                                                field("tags.keyword").
                                                subAggregation(
                                                        AggregationBuilders.
                                                                avg("price_agg").
                                                                field("price")
                                                )
                                )
                ).execute().actionGet();
        SearchHit[] hits = searchResponse.getHits().getHits();
        Map<String, Aggregation> map = searchResponse.getAggregations().asMap();
        Aggregation group_by_month = map.get("group_by_month");
        Map<String, Object> metaData = group_by_month.getMetaData();
        LongTerms longTerms = (LongTerms) group_by_month;
        for (LongTerms.Bucket bucket : longTerms.getBuckets()) {

        }
        Histogram histogram = (Histogram) group_by_month;
        histogram.startTimer();
    }


    @GetMapping
    public void createIndex() throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                // 新版的是服务端口，旧版的都是通信端口
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9300, "http")));
        CreateIndexRequest product = new CreateIndexRequest("product");
        product.settings(Settings.builder().
                put("index.number_of_shards", 3).
                put("index.number_of_replicas", 2)
                .build());
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().
                create(product, RequestOptions.DEFAULT);
        if (createIndexResponse.isAcknowledged()) {
            System.out.println("创建完成");
        } else {
            System.out.println("创建失败");
        }
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public void batchInsert() throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                // 新版的是服务端口，旧版的都是通信端口
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9300, "http")));
        BulkRequest bulkRequest = new BulkRequest("product");
        Test test = new Test();
        for (int i = 0; i < 10; i++) {
            bulkRequest.add(new IndexRequest().source(GsonUtil.toJson(test), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.getItems().length);
        restHighLevelClient.close();
    }

    @GetMapping
    public void getById() throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                // 新版的是服务端口，旧版的都是通信端口
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9300, "http")));
        GetRequest getRequest = new GetRequest("product", "1");
        // 多个ID查询
//        MultiGetRequest request =new MultiGetRequest();
//        request.add("product","1");
        // 只查询 id  name
        String[] includes = {"id", "name"};
        String[] excludes = {"desc"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(documentFields);
        restHighLevelClient.close();
    }

    @GetMapping
    public void updateByQuery() throws IOException {
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                // 新版的是服务端口，旧版的都是通信端口
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9300, "http")));
        UpdateByQueryRequest request = new UpdateByQueryRequest("product");
        request.setQuery(QueryBuilders.termQuery("name", "myName"));
        // 在desc字段的后面加一个#
        request.setScript(new Script(ScriptType.INLINE, "painless", "ctx._source_desc+='#';",
                Collections.emptyMap()));
        BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
        System.out.println(bulkByScrollResponse);
        restHighLevelClient.close();
    }
}
