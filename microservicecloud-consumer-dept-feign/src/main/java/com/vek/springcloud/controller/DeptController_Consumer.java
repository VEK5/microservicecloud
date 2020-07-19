package com.vek.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vek.entities.Dept;
import com.vek.service.DeptClientService;

@RestController
public class DeptController_Consumer {

	@Autowired
	private DeptClientService service;

	// 根据ID获取部门信息
	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	// 获取所有部门信息
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		return service.list();
	}

	// 添加部门信息
	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept dept) {
		return service.add(dept);
	}
	
}