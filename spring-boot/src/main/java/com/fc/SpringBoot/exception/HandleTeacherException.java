package com.fc.SpringBoot.exception;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fc.SpringBoot.util.ResultUtil;
import com.fc.SpringBoot.vo.Result;

@ControllerAdvice
public class HandleTeacherException {
	private final static Logger logger= LoggerFactory.getLogger(HandleTeacherException.class);
	@ResponseBody
	@ExceptionHandler(value=TeacherException.class)
	public Result handle(TeacherException e){
		logger.error("系统异常：{}",e);
		return ResultUtil.error(e.getCode(),e.getMessage());
	}

}
