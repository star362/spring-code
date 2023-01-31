package com.nacos.dao;


import com.nacos.repistory.A;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ADao {



    List<A> selAll();





}
