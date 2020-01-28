package com.zip.code.filter.analyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zip.code.filter.analyser.intf.Analyzer;

@Component
public class ZipCodeCompacter implements Analyzer<String,ZipCodeRange>{

	private static final Logger log = LoggerFactory.getLogger(ZipCodeCompacter.class);

	@Autowired
	private ZipCodeParser parser;

	/**
	 * This method accepts ZipCode Ranges as String and returns the list of compacted ZipCodeRange objects!!
	 */
	public List<ZipCodeRange> compactZipCodes(String zipCodes) throws ZipCodeRangeValidationException{
		log.info("Started compacting ZipCodes for : {}", zipCodes);
		List<ZipCodeRange> rangeList = null;
		try {
		rangeList = parser.parseZipCodeRange(zipCodes);
		}catch(Exception e) {
			log.error("Error while parsing zipCode Ranges : {}",zipCodes,e);
			throw new ZipCodeRangeValidationException(e);
		}
		log.info("Parsing complete for ZipCodes : {}", rangeList);
		Collections.sort(rangeList);
		log.info("Sorting complete for ZipCodes : {}", rangeList);
		List<ZipCodeRange> compactedRange = getAdjustedRange(rangeList);
		log.info("Compacted ZipCodes : {}", compactedRange);
		return Collections.unmodifiableList(compactedRange);
	}

	/**
	 * This method compacts the ZipCodes
	 * @param rangeList
	 * @return
	 */
	private List<ZipCodeRange> getAdjustedRange(List<ZipCodeRange> rangeList) {

		List<ZipCodeRange> adjustedRangeList = new ArrayList<ZipCodeRange>();
		ZipCodeRange adjustedRange = new ZipCodeRange(rangeList.get(0));
		adjustedRangeList.add(adjustedRange);
		for (int i = 1; i < rangeList.size(); i++) {
			ZipCodeRange startRange = rangeList.get(i);
			if (startRange.getMin() <= adjustedRange.getMax() || adjustedRange.getMax() + 1 == startRange.getMin()
					|| startRange.getMin() == adjustedRange.getMin()) {
				if (adjustedRange.getMax() < startRange.getMax()) {
					adjustedRange.setMax(startRange.getMax());
				}
			} else {
				adjustedRange = new ZipCodeRange(startRange);
				adjustedRangeList.add(adjustedRange);
			}
		}
		return adjustedRangeList;

	}

}
