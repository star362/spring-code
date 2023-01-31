package com.test;

import beans.SpringCodeApplication;
import beans.entity.StarUser;
import beans.repository.StarUserRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-05 16:07
 *
 * <p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringCodeApplication.class})
public class demo {
    private static final Logger log = LoggerFactory.getLogger(demo.class);
    @Autowired
    StarUserRepository starUserRepository;

    @Before
    public void before() {
        log.info("===befor====");
    }

    @Test
    public void gettest() {
        final RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:50465/authorityuserrelation/bean?name=123456";
        final ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(url, JSONObject.class);
        log.info("======[{}]", forEntity);
    }

    @Test
    public void posttest() {
        final RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:50465/authorityuserrelation/query";
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpHeaders.setContentType(type);
        Map<String, String> map = new HashMap<>(1 << 4);
        map.put("id", "1");
        HttpEntity<Map<String, Object>> objectHttpEntity = new HttpEntity(map, httpHeaders);
        final ResponseEntity<JSONObject> forEntity = restTemplate.postForEntity(url, objectHttpEntity, JSONObject.class);
        log.info("======[{}]", forEntity);


    }

    @Test
    public void httpclient() {
        RestTemplate rest = new RestTemplate();


    }

    @Test
    public void starUserRepositoryTest() {
        final Specification<StarUser> specification = (root, criteriaQuery, criteriaBuilder) -> {
//1.获得需要查询的属性
            Path<String> major = root.get("userName");
            //2.构造查询条件
            //注意这里的equal是指的SQL查询语句的=
            //第一个参数:需要查询的属性 第二个参数:我们想要查询的属性值
            Predicate predicates = criteriaBuilder.equal(major, "a");

//            Predicate equal8 = criteriaBuilder.equal(root.get("userAge"), 12);
//            predicates = criteriaBuilder.and(predicates, equal8);

            return predicates;
        };

        final List<StarUser> all = starUserRepository.findAll(specification);

        all.forEach(a -> log.info("{}", a));

    }


    @Test
    public void starUserRepositoryTest2() {
        final Specification<StarUser> specification = (root, criteriaQuery, criteriaBuilder) -> {
            final Predicate userAge = criteriaBuilder.equal(root.get("userAge"), 12);
            final Predicate userName = criteriaBuilder.equal(root.get("userName"), "a");

            criteriaQuery.where(
                    userAge,userName
            );
            return criteriaQuery.getRestriction();
        };

        final List<StarUser> all = starUserRepository.findAll(specification);

        all.forEach(a -> log.info("====={}", a));

    }


}
