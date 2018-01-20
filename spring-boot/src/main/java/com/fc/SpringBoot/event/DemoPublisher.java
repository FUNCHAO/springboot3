package com.fc.SpringBoot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {
	@Autowired//用来发布事件
	private ApplicationContext applicationContext;
	public void publish(String msg){
		//发布方法
		applicationContext.publishEvent(new DemoEvent(this,msg));
	}
}
