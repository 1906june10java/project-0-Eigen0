package com.revature.exception;
/**
 *Custom Exception: thrown when  user enters invalid PIN 3 different times
 * 
 */
public class PinException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2706425814779819L;
	private String errorCode="Unknown_Exception";
	
	public PinException(String errorCode){
		super(errorCode);
		this.errorCode=errorCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
	
}
