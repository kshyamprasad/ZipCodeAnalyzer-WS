package com.zip.code.filter.analyser;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zip.code.filter.analyser.intf.Validator;

@Component
public class ZipCodeValidator implements Validator<String, ZipCodeRange> {

	private static final Logger log = LoggerFactory.getLogger(ZipCodeValidator.class);
	private final static String regex = "^(\\[\\d{5}\\,\\d{5}\\]){1,}$";
	private final static Pattern pattern = Pattern.compile(regex);
	private final static int MIN_RANGE = 10000;
	private final static String RANGE_DELIMITER = ",";

	/**
	 * This method validates whether the provided string meets the ZipCode Range patter.
	 * Returns true if the pattern matches else returns false 
	 */
	public boolean validate(String zipCodeRangeString) {
		Matcher matcher = pattern.matcher(zipCodeRangeString);
		return matcher.matches();
	}

	/**
	 * This method a discrete ZipCode Range validates for range conditions and returns ZipCodeRange
	 * Upon Validation Failure throws ZipCodeRangeValidationException
	 */
	public ZipCodeRange validateRange(String zipCodeRangeString) throws ZipCodeRangeValidationException {
		StringTokenizer token = new StringTokenizer(zipCodeRangeString, RANGE_DELIMITER);
		String minString = token.nextToken();
		String maxString = token.nextToken();
		int min = Integer.parseInt(minString);
		int max = Integer.parseInt(maxString);
		if (min < MIN_RANGE || max < MIN_RANGE || min > max) {
			log.error("Validation Error for Zip Code Range : {} ", zipCodeRangeString);
			throw new ZipCodeRangeValidationException("Invalid min and max range for : " + zipCodeRangeString);
		}
		ZipCodeRange range = new ZipCodeRange(Integer.parseInt(minString), Integer.parseInt(maxString));
		return range;
	}

}
