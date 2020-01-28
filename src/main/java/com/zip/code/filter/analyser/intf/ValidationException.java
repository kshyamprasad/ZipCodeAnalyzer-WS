package com.zip.code.filter.analyser.intf;

public abstract class ValidationException extends Exception{

	public ValidationException(Exception e) {
		super(e);
	}

	public ValidationException(String message) {
		super(message);
	}
	
}
