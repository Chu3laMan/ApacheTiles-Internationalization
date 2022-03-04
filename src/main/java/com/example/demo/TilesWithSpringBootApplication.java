package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.example.demo.entities.User;
import com.example.demo.entities.UserInfo;

@SpringBootApplication
public class TilesWithSpringBootApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TilesWithSpringBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TilesWithSpringBootApplication.class, args);
		
	}

}
