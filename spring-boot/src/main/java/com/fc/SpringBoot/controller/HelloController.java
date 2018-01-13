package com.fc.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fc.SpringBoot.vo.Student;
//@RequestBode和Controller的合体
@RestController
public class HelloController {
	//获取资源文件中的参数
	@Value("${hello}")
	private String hello;
	@Autowired
	private Student student;
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello(){
		
		return hello;
	}
	@RequestMapping(value="/hello2",method=RequestMethod.GET)
	public Student hello2(){
		
		return student;
	}
}
