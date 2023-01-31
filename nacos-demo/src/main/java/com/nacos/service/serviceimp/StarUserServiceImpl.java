package com.nacos.service.serviceimp;


import com.nacos.dao.StarUserMapper;
import com.nacos.repistory.StarUser;
import com.nacos.service.StarUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StarUserServiceImpl implements StarUserService {

	@Autowired
	private StarUserMapper starUserMapper;

	@ApiOperation(value = "查询所有")
	@Override
	public List<StarUser> finAllStarUser(){
		return starUserMapper.finAllStarUser();
	}

	@ApiOperation(value = "查询唯一")
	@Override
	public StarUser finFirstStarUser(StarUser starUser){
		return starUserMapper.finFirstStarUser(starUser);
	}

	@ApiOperation(value = "修改")
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	@Override
	public int updateStarUser(StarUser starUser){
		return starUserMapper.updateStarUser(starUser);
	}

	@ApiOperation(value = "删除")
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	@Override
	public int deleteStarUser(StarUser starUser){
		return starUserMapper.deleteStarUser(starUser);
	}

	@ApiOperation(value = "新增")
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	@Override
	public int saveStarUser(StarUser starUser){
		return starUserMapper.saveStarUser(starUser);
	}

}