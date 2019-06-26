package com.revature.exception;
/**
 * Custom exception: thrown if user enters 'cancel' when entering
 * login in information
 * 
 */
public class CancelException  extends RuntimeException {

	private static final long serialVersionUID = -1203285787379028945L;
	private String errorCode="Unknown_Exception";
	
	public CancelException(String errorCode){
		super(errorCode);
		this.errorCode=errorCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
	
}
