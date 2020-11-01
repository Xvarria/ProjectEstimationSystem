package com.pes.web.model.exception;

public class ControllerException extends Exception  {
	
	private static final long serialVersionUID = 1L;
	private String errorMessageKey;
	
	
	public ControllerException(String errorMessageKey) {
		super("Controller Exception");
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}
}

