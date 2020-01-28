package com.zip.code.filter.analyser.intf;

import java.util.List;

public interface Parser<S,Z> {
	
	public List<Z> parseZipCodeRange(S s) throws ValidationException;

}
