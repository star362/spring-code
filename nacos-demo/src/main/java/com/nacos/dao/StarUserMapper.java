package com.nacos.dao;


import com.nacos.repistory.StarUser;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface StarUserMapper {

	@ApiOperation(value = "查询所有")
	 List<StarUser> finAllStarUser();

	@ApiOperation(value = "查询唯一")
	 StarUser finFirstStarUser(StarUser starUser);

	@ApiOperation(value = "修改")
	 int updateStarUser(StarUser starUser);

	@ApiOperation(value = "删除")
	 int deleteStarUser(StarUser starUser);

	@ApiOperation(value = "新增")
	 int saveStarUser(StarUser starUser);

}