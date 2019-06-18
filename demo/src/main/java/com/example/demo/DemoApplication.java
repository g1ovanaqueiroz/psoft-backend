package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import controller.TokenFilter;

@SpringBootApplication
public class DemoApplication {
	
	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean<Filter> filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/private");
		return filterRb;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}