package com.zip.code.filter.analyser.intf;

import java.util.List;

public interface Analyzer<S,Z> {
	public List<Z> compactZipCodes(S s) throws ValidationException;

}
