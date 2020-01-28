package com.zip.code.filter.analyser.intf;

public interface Validator<S,Z> {

	public boolean validate(S s);
	
	public Z validateRange(S s) throws ValidationException ;

}
