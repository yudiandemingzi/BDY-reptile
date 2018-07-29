package com.jincou.pajiekou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//启动类加上定时任务注解
@EnableScheduling
@SpringBootApplication
public class PajiekouApplication {

	public static void main(String[] args) {
		SpringApplication.run(PajiekouApplication.class, args);
	}
}
