package com.nacos.service;


import com.nacos.repistory.StarUser;
import io.swagger.annotations.ApiOperation;

import java.util.List;

public interface StarUserService {

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