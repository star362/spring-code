package com.nacos.service.serviceimp;

import java.util.List;

import com.nacos.dao.SubjectMapper;
import com.nacos.repistory.Subject;
import com.nacos.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.annotations.ApiOperation;

//@Service
public class SubjectServiceImpl {

	/*@Autowired
	private SubjectMapper subjectMapper;

	@ApiOperation(value = "查询所有")
	public List<Subject> finAllSubject(){
		return subjectMapper.finAllSubject();
	}

	@ApiOperation(value = "查询唯一")
	public Subject finFirstSubject(Subject subject){
		return subjectMapper.finFirstSubject(subject);
	}

	@ApiOperation(value = "修改")
	public int updateSubject(Subject subject){
		return subjectMapper.updateSubject(subject);
	}

	@ApiOperation(value = "删除")
	public int deleteSubject(Subject subject){
		return subjectMapper.deleteSubject(subject);
	}

	@ApiOperation(value = "新增")
	public int saveSubject(Subject subject){
		return subjectMapper.saveSubject(subject);
	}*/

}