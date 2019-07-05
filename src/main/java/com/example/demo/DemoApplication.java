package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.controller.TokenFilter;

/**
 * Project startup class
 * 
 * @author Giovana Brito Oliveira
 *
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * Filtering routes that need authorization
	 * 
	 * @return Filter
	 */
	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean<Filter> filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());

		filterRb.addUrlPatterns("/v1/students/delete", "/v1/comments", "/v1/students/update", "/v1/subjects/create",
				"/v1/subjects/delete", "/v1/subjects/update", "/v1/subjects/like", "/v1/subjects/ranking");
		return filterRb;
	}

	/**
	 * Project Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}