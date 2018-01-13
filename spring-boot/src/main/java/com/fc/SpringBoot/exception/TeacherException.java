package com.fc.SpringBoot.exception;
//不要继承exception否则不回滚事物
public class TeacherException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;

	public TeacherException(Integer code,String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
