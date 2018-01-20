package com.fc.SpringBoot.event;

import org.springframework.context.ApplicationEvent;

//自定义事件，继承 ApplicationEvent
public class DemoEvent extends ApplicationEvent{
	private String msg;
	public DemoEvent(Object source) {
		super(source);
	}
	public DemoEvent(Object source,String msg) {
		super(source);
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "DemoEvent [msg=" + msg + "]";
	}

}
