package com.zip.code.filter.analyser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ZipCodeAnalyzerTest {

	@Autowired
	private ZipCodeCompacter compacter;

	@Test
	public void testCase1() {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		try {
			compactZipCodes = compacter.compactZipCodes("[95630,95637][95630,95670]");
			for (ZipCodeRange range : compactZipCodes) {
				builder.append(range.toString());
			}
			Assert.assertEquals("[95630,95670]", builder.toString());
		} catch (ZipCodeRangeValidationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCase2() {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		try {
			compactZipCodes = compacter.compactZipCodes("[95638,95670][95630,95637]");
			for (ZipCodeRange range : compactZipCodes) {
				builder.append(range.toString());
			}
			Assert.assertEquals("[95630,95670]", builder.toString());
		} catch (ZipCodeRangeValidationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCase3() {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		try {
			compactZipCodes = compacter.compactZipCodes("[95630,95637][95638,95670][95647,95649][95670,95690]");
			for (ZipCodeRange range : compactZipCodes) {
				builder.append(range.toString());
			}
			Assert.assertEquals("[95630,95690]", builder.toString());
		} catch (ZipCodeRangeValidationException e) {
			Assert.fail("Exception thrown while testing ZipCode ranges");
			e.printStackTrace();
		}
	}

	@Test
	public void testCase4() {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		try {
			compactZipCodes = compacter.compactZipCodes("[95630,95637][95639,95647][95650,95669][95673,95690]");
			for (ZipCodeRange range : compactZipCodes) {
				builder.append(range.toString());
			}
			Assert.assertEquals("[95630,95637][95639,95647][95650,95669][95673,95690]", builder.toString());
		} catch (ZipCodeRangeValidationException e) {
			Assert.fail("Exception thrown while testing ZipCode ranges");
			e.printStackTrace();
		}
	}

	@Test(expected = ZipCodeRangeValidationException.class)
	public void testCase5() throws ZipCodeRangeValidationException {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		compactZipCodes = compacter.compactZipCodes("[95630,95637][95639,95647][95650,95669][95673,abcde]");
		for (ZipCodeRange range : compactZipCodes) {
			builder.append(range.toString());
		}
	}
	
	@Test(expected = ZipCodeRangeValidationException.class)
	public void testCase6() throws ZipCodeRangeValidationException {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		compactZipCodes = compacter.compactZipCodes("");
		for (ZipCodeRange range : compactZipCodes) {
			builder.append(range.toString());
		}
	}
	
	@Test(expected = ZipCodeRangeValidationException.class)
	public void testCase7() throws ZipCodeRangeValidationException {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		compactZipCodes = compacter.compactZipCodes("[0,95637][95639,95647][95650,95669]");
		for (ZipCodeRange range : compactZipCodes) {
			builder.append(range.toString());
		}
	}
	
	@Test(expected = ZipCodeRangeValidationException.class)
	public void testCase8() throws ZipCodeRangeValidationException {
		List<ZipCodeRange> compactZipCodes;
		StringBuilder builder = new StringBuilder();
		compactZipCodes = compacter.compactZipCodes("[95630,95637][95639,95647][95650,95648]");
		for (ZipCodeRange range : compactZipCodes) {
			builder.append(range.toString());
		}
	}
}
