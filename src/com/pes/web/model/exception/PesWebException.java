package com.pes.web.model.exception;

public class PesWebException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public PesWebException(String message) {
		super(message);
	}

	public PesWebException() {
		super();
	}

	public PesWebException(String message, Throwable cause) {
		super(message, cause);
	}

	public PesWebException(Throwable cause) {
		super(cause);
	}
}
