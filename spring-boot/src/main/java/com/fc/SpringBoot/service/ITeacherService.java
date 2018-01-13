package com.fc.SpringBoot.service;

import java.util.List;

import com.fc.SpringBoot.entity.Teacher;

public interface ITeacherService {
	public List<Teacher> getAllTeas();
	public Teacher saveTea(Teacher tea);
	public void delete(Integer id);
	public Teacher update(Teacher tea);
	public Teacher getTea(Integer id);
	public List<Teacher>findByAge(Integer age);
}
