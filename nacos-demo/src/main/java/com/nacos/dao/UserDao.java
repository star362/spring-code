package com.nacos.dao;



import com.nacos.repistory.StarUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserDao {


    List<StarUser> selAll();

    @Select(value = "select id id,user_name userName,user_age userAge,user_sex userSex from star_user where id=#{id} limit 1")
    Optional<StarUser> findById(Integer id);


    Optional<StarUser> findByAgeOrId(Integer age,Integer id);


    @Select("select id id,user_name userName,user_age userAge,user_sex userSex from star_user WHERE user_name like concat(concat('%',#{name}),'%') and id=#{id}")
    Map findByLikeNanme(String name,Integer id);




}
