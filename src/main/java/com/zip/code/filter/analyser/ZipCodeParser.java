package com.zip.code.filter.analyser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zip.code.filter.analyser.intf.Parser;

@Component
public class ZipCodeParser implements Parser<String, ZipCodeRange> {

	private static final Logger log = LoggerFactory.getLogger(ZipCodeParser.class);

	@Autowired
	private ZipCodeValidator validator;
	
	private static final String RANGE_DELIMITER_END = "]";

	/**
	 * This method accepts the ZipCode Range String, parses the String to return list of ZipCodeRange
	 * In case of validation errors throws ZipCodeRangeValidationException
 	 */
	public List<ZipCodeRange> parseZipCodeRange(String zipCodes) throws ZipCodeRangeValidationException {

		if (!validator.validate(zipCodes)) {
			throw new ZipCodeRangeValidationException("Invalid Zip Code Range Format :  " + zipCodes);
		}
		List<String> invalidRanges = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(zipCodes, RANGE_DELIMITER_END);
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			try {
				ZipCodeRange range = validator.validateRange(token.substring(1));
				rangeList.add(range);
			} catch (Exception e) {
				invalidRanges.add(token);
				log.error("Validation Error for Zip Code Range : {} ", token.substring(1), e);
			}
		}
		if (!invalidRanges.isEmpty()) {
			throw new ZipCodeRangeValidationException("Invalid Zip Code Range Format :  " + invalidRanges);
		}
		return rangeList;
	}

}
