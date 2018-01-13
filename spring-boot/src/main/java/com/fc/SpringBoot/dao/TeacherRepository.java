package com.fc.SpringBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fc.SpringBoot.entity.Teacher;
//继承JpaRepository<Teacher, Integer>  泛型：类，主键类型
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
	//根据年龄查询，查询命名语法要规范才能查出来
	public List<Teacher> findByAge(Integer age);
}
