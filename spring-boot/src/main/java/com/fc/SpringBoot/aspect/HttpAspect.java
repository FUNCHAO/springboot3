package com.fc.SpringBoot.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fc.SpringBoot.entity.Teacher;
import com.fc.SpringBoot.exception.TeacherException;
import com.fc.SpringBoot.util.ResultUtil;


@Aspect
@Component
public class HttpAspect {
	
	private static final Logger log = LoggerFactory.getLogger(HttpAspect.class);
	@Before("execution (public * com.fc.SpringBoot.controller.*.*(..))")
	public void log(){
		log.info("你好哈哈哈哈哈哈哈哈哈哈");
	}
	//或者定义一个切入点的方法，在其他通知中使用，多个通知都可以使用，减少重复代码
	@Pointcut("execution (public * com.fc.SpringBoot.controller.*.save*(..))")
	public void pointCut(){
		
	}
	@After("pointCut()")
	public void after(){
		log.info("方法已执行完毕。。。。。。。。。。。。。。。");
	}
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint jp){
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();		
		log.info("request.getMethod:{}",request.getMethod());
		log.info("request.getRequestURI:{}",request.getRequestURI());
		log.info("request.getRemoteAddr,即ip:{}",request.getRemoteAddr());
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();//被拦截的方法
		String name = method.getName();//被拦截的方法名
		log.info("----methodName--{}---",name);
		String className = jp.getSignature().getDeclaringTypeName();//被拦截的类名
		log.info("----className--{}---",className);
		Object[] args = jp.getArgs();//方法中的参数
		log.info("----args--{}---",args);
		Object result=null;
		boolean flag=true;
		try {
			for(Object arg:args){
				if(arg instanceof Teacher){
					Teacher tea=(Teacher)arg;
					if(tea.getAge()>40){
						flag=false;
						log.info("当前年龄:{}已大于40",tea.getAge());
						//这里抛异常不会返回到页面，亲测用下面response方法可以
//						throw new TeacherException(1,"年龄大于40不要");
					
					}
				}
			}
			for(Object arg:args){
				if(arg instanceof HttpServletResponse){
					HttpServletResponse req=(HttpServletResponse)arg;
					if(!flag){
						req.setContentType("text/json");
						req.setCharacterEncoding("UTF-8");
						req.getOutputStream().write("大于40不要".getBytes("UTF-8")); 
						log.info("我是response方法");
					}
				}
			}
			if(flag){
				
				result=jp.proceed(args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		log.info("---------环绕通知结束---------");
		
		return result;
	}
	@AfterReturning(returning="object",pointcut="pointCut()")
	public void afterRetrun(Object object){
		log.info("response:{}",object);
	}

}
