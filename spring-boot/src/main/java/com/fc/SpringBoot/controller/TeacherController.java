package com.fc.SpringBoot.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fc.SpringBoot.entity.Teacher;
import com.fc.SpringBoot.exception.TeacherException;
import com.fc.SpringBoot.service.ITeacherService;
import com.fc.SpringBoot.util.ResultUtil;
import com.fc.SpringBoot.vo.Result;
@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private ITeacherService teacherService;
	/**
	 * 查所有
	 * @return
	 */
	@GetMapping("/getAllTeas")
	@ResponseBody
	public List<Teacher> getAllTeas(){
		List<Teacher> teas = teacherService.getAllTeas();
		return teas;
	}
	/**
	 * 增
	 * @param tea
	 * @return
	 */
	@GetMapping("/save")
	@ResponseBody
	public Result<Teacher> save(@Valid Teacher tea,BindingResult br,HttpServletResponse response){	
		if(br.hasErrors()){			
			
			System.out.println(br.getFieldError().getDefaultMessage());
			return ResultUtil.error(1,"年龄不能低于18");
		}
		Teacher teacher = teacherService.saveTea(tea);
		
		return ResultUtil.success(teacher);				
	}
	/**
	 * 删
	 * @param id
	 */
	@GetMapping("/delete/{id}")
	@ResponseBody
	public boolean delete(@PathVariable("id")Integer id){		
		try {
			teacherService.delete(id);
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}
	/**
	 * 改
	 * @param tea
	 * @return
	 */
	@GetMapping("/update")
	@ResponseBody
	public Teacher update(Teacher tea){		
		Teacher teacher = teacherService.update(tea);
		return teacher;
	}
	@GetMapping("/getTeaById/{id}")
	@ResponseBody
	public Teacher getTeaById(@PathVariable("id")Integer id){
		Teacher teacher = teacherService.getTea(id);
		return teacher;
	}
	@GetMapping("/findByAge/{age}")
	@ResponseBody
	public List<Teacher> findByAge(@PathVariable("age")Integer age){
		List<Teacher> teas = teacherService.findByAge(age);
		return teas;
	}
}
