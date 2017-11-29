package com.sda.spring;

import com.sda.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FinparadiseApplication extends SpringBootServletInitializer {

	@Autowired
	private AccountService accountService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FinparadiseApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FinparadiseApplication.class, args);
	}
}
