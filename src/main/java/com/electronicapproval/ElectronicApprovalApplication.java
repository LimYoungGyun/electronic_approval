package com.electronicapproval;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ElectronicApprovalApplication extends SpringBootServletInitializer {
	
	// Server TimeZone Setting
	@PostConstruct
	void started() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		// 한국 시간
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ElectronicApprovalApplication.class, args);
	}

}
