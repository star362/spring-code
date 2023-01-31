package com.elasticsearchdemo.test.collection;

import com.elasticsearchdemo.test.entity.EventLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestcollectionTest {

    private static final Logger log = LoggerFactory.getLogger(TestcollectionTest.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private Elasticsearch elasticsearch;


    @Test
    public void test1() {
        SortBuilder sortBuilder1 = SortBuilders.fieldSort("SPORT.keyword").order(SortOrder.DESC);
        final QueryBuilder termQueryBuilder = new TermQueryBuilder("ID.keyword", "00001");
        final MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("ID.keyword", "00001");
        SearchQuery sq = new NativeSearchQueryBuilder()
//                .withQuery(new MatchAllQueryBuilder())
//                .withQuery(matchQueryBuilder)
                .withQuery(QueryBuilders.matchQuery("ID.keyword", "00001"))
                .withPageable(new PageRequest(0, 3))
                .withSourceFilter(new FetchSourceFilter(new String[]{"DIP", "SPORT", "OCCURRENCE_TIME"}, null))
                .withSort(sortBuilder1)
                .build();
        final List<EventLog> eventLogs = elasticsearchTemplate.queryForList(sq, EventLog.class);

        eventLogs.stream().forEach(a -> {
            log.info("=========pojo====[{}]\t===-[{}]", a, eventLogs.size());
        });


    }

    @Test
    public void test3() {
        // 创建聚合查询条件
        TermsAggregationBuilder agg = AggregationBuilders.terms("dip_type").field("DIP.keyword");//.size(100);// size是查询聚合出来的条数

        SearchQuery sq = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("DIP.keyword", "192.168.1.110"))
                .addAggregation(agg)
                .build();
        final AggregatedPage<EventLog> eventLogs = elasticsearchTemplate.queryForPage(sq, EventLog.class);

//        eventLogs.stream().forEach(a -> {
//            log.info("=========pojo====[{}]\t===-[{}]", a, eventLogs.size());
//        });

        // 取出聚合结果
        Aggregations entitiesAggregations = eventLogs.getAggregations();
        Terms terms = (Terms) eventLogs.getAggregation("dip_type");

// 遍历取出聚合字段列的值，与对应的数量
        for (Terms.Bucket bucket : terms.getBuckets()) {
            String keyAsString = bucket.getKeyAsString(); // 聚合字段列的值
            long docCount = bucket.getDocCount();// 聚合字段对应的数量
            log.info("===keyAsString=[{}]==========docCount==[{}]", keyAsString, docCount);
        }
        System.out.println("====");
    }


    @Test
    public void test2() throws JsonProcessingException {
        elasticsearch.getpage();
    }
}