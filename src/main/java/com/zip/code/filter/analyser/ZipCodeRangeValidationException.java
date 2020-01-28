package com.zip.code.filter.analyser;

import com.zip.code.filter.analyser.intf.ValidationException;

public class ZipCodeRangeValidationException extends ValidationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZipCodeRangeValidationException(Exception e) {
		super(e);
	}

	public ZipCodeRangeValidationException(String message) {
		super(message);
	}
}
