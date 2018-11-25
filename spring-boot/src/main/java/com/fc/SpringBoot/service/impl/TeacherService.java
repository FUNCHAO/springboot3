package com.fc.SpringBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fc.SpringBoot.dao.PagingAndSortingRepository;
import com.fc.SpringBoot.dao.TeacherRepository;

import com.fc.SpringBoot.entity.Teacher;
import com.fc.SpringBoot.exception.TeacherException;
import com.fc.SpringBoot.service.ITeacherService;
@Service
public class TeacherService implements ITeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
//	@Autowired
//	private PagingAndSortingRepository<Teacher,Integer> pagingAndSortingRepository;
	@Override
	public List<Teacher> getAllTeas() {		
		return teacherRepository.findAll();
	}
	@Override
	public Teacher saveTea(Teacher tea) {
		return teacherRepository.save(tea);
	}
	@Override
	public void delete(Integer id) {
		teacherRepository.delete(id);
	}
	@Override
	public Teacher update(Teacher tea){
		Teacher teacher = teacherRepository.save(tea);
		return teacher;
	}
	@Override
	public Teacher getTea(Integer id) {
		//提示语或示例场景可能不合理
		Teacher teacher = teacherRepository.findOne(id);
		//此处异常会抛到页面
		if(null==teacher){
			throw new TeacherException(1,"老师不存在");
		}
		if(teacher.getAge()>60){
			throw new TeacherException(1,"该老师已经退休。。。");
		}
		return teacher;
	}
	@Override
	public List<Teacher> findByAge(Integer age) {
		List<Teacher> teas = teacherRepository.findByAge(age);
		return teas;
	}
	@Override
	public Page<Teacher> findAll(Pageable pageable) {
		Page findAll=null;
		Page findAll2=null;
//		 findAll2 = pagingAndSortingRepository.findAll( pageable);
		 findAll=teacherRepository.findAll( pageable);
		return findAll;
	}

}
