package com.fc.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(String name,Model model){
		model.addAttribute("name", name);
		System.out.println(name);
		return "hello";
	}
	@RequestMapping(value="/angular",method=RequestMethod.GET)
	public String angular(Model model){
		return "angularTest";
	}
}
