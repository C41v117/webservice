package com.metamorf.eform.common.exception;

public class WebServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public WebServiceException(String message) {
		super(message);
	}
	
	
}
