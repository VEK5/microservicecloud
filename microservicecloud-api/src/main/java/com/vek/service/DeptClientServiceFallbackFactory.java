package com.vek.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vek.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
	@Override
	public DeptClientService create(Throwable throwable) {
		return new DeptClientService() {

			@Override
			public Dept get(Long id) {
				return new Dept().setDeptno(id).setDname("该ID--" + id + " 没有对应的信息,null--@HystrixCommand")
						.setDb_source("No this database in MySQL");
			}

			@Override
			public List<Dept> list() {
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}

		};
	}
}
