package com.vek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard // 开启Hystrix仪表化监控注解
public class DeptConsumer9001_HystrixDashboard_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer9001_HystrixDashboard_App.class, args);
	}

}
