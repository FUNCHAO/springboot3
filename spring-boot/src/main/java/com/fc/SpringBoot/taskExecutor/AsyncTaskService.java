package com.fc.SpringBoot.taskExecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
//为了方便分类就将这个service写在这个包下
@Service
public class AsyncTaskService {
	//表示异步，写在类上则表示该类里面的方法都是异步
	@Async
	public void executeAsyncTask(Integer i){
		System.out.println("异步任务1111---------"+i);
	}
	@Async
	public void executeAsyncTask2(Integer i){
		System.out.println("异步任务2222---------"+i);
	}
}
