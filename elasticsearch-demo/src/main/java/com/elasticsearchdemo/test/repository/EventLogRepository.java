package com.elasticsearchdemo.test.repository;

import com.elasticsearchdemo.test.entity.EventLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-07-05 10:54
 *
 * <p>
 */
@Repository
public interface EventLogRepository extends ElasticsearchRepository<EventLog,String> {




}
