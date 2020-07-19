package com.vek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vek.entities.Dept;
import com.vek.service.DeptService;

@RestController // @RestController = @Controller + @ResponseBody
public class DeptController {
	@Autowired
	private DeptService service;

	@Autowired
	private DiscoveryClient client;

	// 添加用户
	@PostMapping("/dept/add")
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	// 根据ID查询用户
	@GetMapping("/dept/get/{id}")
	@HystrixCommand(fallbackMethod = "get_Hystrix") // 一旦该方法异常，则会调用下边的get_Hystrix()方法
	public Dept get(@PathVariable("id") Long id) {

		Dept dept = service.get(id);
		// 判断dept是否为空，如果为空就抛出异常
		if (null == dept) {
			throw new RuntimeException("该ID--" + id + " 没有对应的信息");
		}
		return dept;
	}

	// fallback方法
	public Dept get_Hystrix(@PathVariable("id") Long id) {

		return new Dept().setDeptno(id).setDname("该ID--" + id + " 没有对应的信息,null--@HystrixCommand")
				.setDb_source("No this database in MySQL");
	}

	// 查询所有用户
	@GetMapping("/dept/list")
	public List<Dept> list() {
		return service.list();
	}

	// 服务发现
	@GetMapping("/dept/discovery")
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}
}
