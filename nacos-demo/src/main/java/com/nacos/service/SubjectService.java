package com.nacos.service;

import java.util.*;

import com.nacos.repistory.Subject;
import io.swagger.annotations.ApiOperation;

public interface SubjectService {

	@ApiOperation(value = "查询所有")
	 List<Subject> finAllSubject();

	@ApiOperation(value = "查询唯一")
	 Subject finFirstSubject(Subject subject);

	@ApiOperation(value = "修改")
	 int updateSubject(Subject subject);

	@ApiOperation(value = "删除")
	 int deleteSubject(Subject subject);

	@ApiOperation(value = "新增")
	 int saveSubject(Subject subject);

}