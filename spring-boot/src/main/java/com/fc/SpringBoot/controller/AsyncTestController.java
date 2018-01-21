package com.fc.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fc.SpringBoot.taskExecutor.AsyncTaskService;
//多线程测试controller
@RequestMapping("/async")
@RestController
public class AsyncTestController {
	@Autowired
	private AsyncTaskService asyncTaskService;
	@GetMapping("/testAsync")
	public String testAsync(){
		for(int i=0;i<10;i++){
			
			asyncTaskService.executeAsyncTask(i);
			asyncTaskService.executeAsyncTask2(i);
		}
		
		return " wo shi async controller";
		
	}

}
