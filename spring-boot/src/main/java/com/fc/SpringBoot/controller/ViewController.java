package com.fc.SpringBoot.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class ViewController extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) 
	{
		registry.addViewController("/login").setViewName("hello");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/success").setViewName("success");
	}

}
