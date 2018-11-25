package com.fc.SpringBoot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fc.SpringBoot.dao.TeacherRepository;
import com.fc.SpringBoot.entity.Teacher;



@Controller
public class MainController {
	@Autowired
	private TeacherRepository teacherRepository;
	
	@RequestMapping(value="/loginValidate",method=RequestMethod.GET)
	public String index(String name,String password,Model model){
//		model.addAttribute("name", name);
//		System.out.println(name);
//		Teacher teacher = teacherRepository.getByName(name);
//		if(null==teacher||!teacher.getPassword().equals(password))
//		{
//			return "error";
//		}
		return "index";
	}
	@RequestMapping(value="/angular",method=RequestMethod.GET)
	public String angular(Model model){
		return "angularTest";
	}
}
