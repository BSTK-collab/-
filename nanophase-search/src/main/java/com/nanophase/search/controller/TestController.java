package com.nanophase.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

public class TestController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void test(){
    }
}
