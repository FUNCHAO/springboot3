package com.fc.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.SpringBoot.event.DemoPublisher;

@RestController
@RequestMapping("/eventTestController")
public class EventTestController {
	@Autowired
	private DemoPublisher demoPublisher;
	@GetMapping("/testEvent")
	public String testEvent(){
		demoPublisher.publish("ni hao test msg");
		return "hello";
	}

}
