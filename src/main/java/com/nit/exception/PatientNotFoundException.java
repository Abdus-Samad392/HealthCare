package com.nit.exception;

public class PatientNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public PatientNotFoundException() {
		super();
	}
	
	public PatientNotFoundException(String msg) {
		super(msg);
	}

}
