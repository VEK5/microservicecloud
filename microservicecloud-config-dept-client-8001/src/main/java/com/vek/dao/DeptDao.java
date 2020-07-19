package com.vek.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.vek.entities.Dept;

@Mapper
public interface DeptDao
{
	//添加部门
	public boolean addDept(Dept dept);

	//根据ID查找部门
	public Dept findById(Long id);

	//查找所有部门
	public List<Dept> findAll();
	
}
