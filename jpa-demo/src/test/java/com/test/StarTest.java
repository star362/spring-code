package com.test;

import beans.SpringCodeApplication;
import beans.entity.StarUser;
import beans.repository.StarUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author wangyu@mvtech.com.cn
 * @date: 2020-06-05 16:07
 *
 * <p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringCodeApplication.class})
public class StarTest {
    private static final Logger log = LoggerFactory.getLogger(StarTest.class);
    @Autowired
    StarUserRepository starUserRepository;

    @Before
    public void before() {
        log.info("===befor====");
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
                    userAge, userName
            );
            return criteriaQuery.getRestriction();
        };

        final List<StarUser> all = starUserRepository.findAll(specification);

        all.forEach(a -> log.info("====={}", a));

    }

    @Test
    public void starUserRepositoryTest3() {
        final Specification<StarUser> specification = (root, criteriaQuery, criteriaBuilder) -> {
            final Predicate userName = criteriaBuilder.equal(root.get("userName"), "a");
            final Predicate userAge = criteriaBuilder.equal(root.get("userAge").as(Integer.class), 12);


            final Predicate and = criteriaBuilder.and(userName, userAge);

//            criteriaQuery.where(
//                    userAge,userName
//            );
            return and;
        };

        final List<StarUser> all = starUserRepository.findAll(specification);

        all.forEach(a -> log.info("====={}", a));

    }
    @Test
    public void starUserRepositoryTest4() {
        final StarUser starUser = new StarUser();
        starUser.setId(1);
        starUser.setUserName("a");
//
//        Example<StarUser> example = Example.of(starUser);

        ExampleMatcher matcher=ExampleMatcher.matching()
                .withMatcher("userName",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id");
        Example<StarUser> example = Example.of(starUser,matcher);




        final List<StarUser> all = starUserRepository.findAll(example);

        all.forEach(a -> log.info("====={}", a));

    }


}
