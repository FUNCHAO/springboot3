package com.fc.SpringBoot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Teacher {
	@Id//主键
	@GeneratedValue//自增
	private Integer id;
	private String name;
	//校验，在插入入口处校验
	@Min(value=18,message="低于18不能当老师")
	private Integer age;
	private String password;
	//必须要无参的构造方法，否则报错
	public Teacher() {
	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
