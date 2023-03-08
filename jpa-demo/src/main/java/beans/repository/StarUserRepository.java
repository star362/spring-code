package beans.repository;

import beans.entity.StarUser;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author wangyu
 * @date: 2020-05-08 19:56
 *
 * <p>
 */
@Repository
public interface StarUserRepository extends JpaRepository<StarUser, Integer>, JpaSpecificationExecutor<StarUser> {

    /*排序
    * Sort sort = Sort.by("firstname").ascending()
                        .and(Sort.by("lastname").descending());
  * */

    StarUser findFirstById(Integer id, Sort sort);

    List<StarUser> findFirst3ByUserName(String name, Sort sort);


    /*原生 sql 查询使用 * 或者表字段都可以*/
    @Query(value = "select id,user_age ,user_name ,user_sex  from star_user StarUser where user_name=?1", nativeQuery = true)
    /*非原生 sql 得用实体操作数据*/
//    @Query(value = "select s from StarUser s where s.userName=?1")
    Optional<StarUser> findUname(String name);


    @Transactional(timeout = 10)
    @Modifying
    @Query(value = "update  star_user set  user_name=:#{#starUser.userName},user_age=:#{#starUser.userAge} where id=:sid", nativeQuery = true)
    Integer uptable(@Param("starUser") StarUser starUser, @Param("sid") Integer sid);


}
