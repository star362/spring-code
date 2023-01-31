package com.elasticsearchdemo.test.collection;

import com.elasticsearchdemo.test.entity.EventLog;
import com.elasticsearchdemo.test.repository.EventLogRepository;
import com.elasticsearchdemo.test.util.ResultVO;
import com.elasticsearchdemo.test.util.ResultVOUtil;
import com.elasticsearchdemo.test.util.SpringBeanUtils;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-30 12:48
 *
 * <p>
 */
@RestController
public class Elasticsearch {

    private static final Logger log = LoggerFactory.getLogger(Elasticsearch.class);

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private EventLogRepository eventLogRepository;


    @GetMapping("getbean")
    public ResultVO getbean() {
        return ResultVOUtil.success(SpringBeanUtils.getContext().getBeanDefinitionNames());
    }


    @PostMapping("test")
    public ResultVO test() {
        final HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("subdsId", 45);
        return ResultVOUtil.success(objectObjectHashMap);
    }


    @GetMapping("getpage")
    public List getpage() {


        final Page<EventLog> search = eventLogRepository.search(new RangeQueryBuilder("OCCURRENCE_TIME.keyword").gte("2020-05-08").lt("2020-05-09"),
                PageRequest.of(0, 4,
                        Sort.by("OCCURRENCE_TIME.keyword").descending()));
        System.out.println("总条数" + search.getTotalElements());
        System.out.println("总页数" + search.getTotalPages());

        final Stream<EventLog> eventLogStream = search.get();

        eventLogStream.forEach(a -> System.out.println(a));

        return null;
    }


}
