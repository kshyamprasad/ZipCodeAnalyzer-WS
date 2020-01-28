package com.zip.code.filter.analyser.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zip.code.filter.analyser.ZipCodeCompacter;
import com.zip.code.filter.analyser.ZipCodeRange;
import com.zip.code.filter.analyser.ZipCodeRangeValidationException;

/**
 * This main class is used to test the ZipCode Compacter
 * @author Shyam
 *
 */
public class ZipCodeCompacterMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ZipCodeCompacter compacter = context.getBean(ZipCodeCompacter.class);
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		try {
			compactZipCodes = compacter.compactZipCodes("[95630,95637][95638,95670][95647,95649][95670,95690][95700,95715][95800,95815]");
			for(ZipCodeRange range: compactZipCodes) {
				builder.append(range.toString());
			}
		} catch (ZipCodeRangeValidationException e) {
			e.printStackTrace();
		}
		System.out.println("Compacted ZioCode : "+builder);
	}

}
