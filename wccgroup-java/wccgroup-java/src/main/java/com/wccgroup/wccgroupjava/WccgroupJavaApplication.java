package com.wccgroup.wccgroupjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class WccgroupJavaApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WccgroupJavaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WccgroupJavaApplication.class, args);
	}
	
}
