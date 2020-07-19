package com.vek.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vek.entities.Dept;

//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

	// 根据ID查询用户
	@GetMapping("/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id);

	// 查询所有用户
	@GetMapping("/dept/list")
	public List<Dept> list();

	// 添加用户
	@PostMapping("/dept/add")
	public boolean add(@RequestBody Dept dept);

}
