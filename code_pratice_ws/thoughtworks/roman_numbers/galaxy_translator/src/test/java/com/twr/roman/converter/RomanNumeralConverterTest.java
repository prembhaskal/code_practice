package com.twr.roman.converter;

import com.twr.GalaxyConverterBuilder;
import com.twr.exception.ConversionException;
import com.twr.exception.UnknownConverterException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanNumeralConverterTest {

	private RomanNumeralConverter converter;

	@Before
	public void setUp() throws UnknownConverterException {
		GalaxyConverterBuilder builder = new GalaxyConverterBuilder();
		converter = (RomanNumeralConverter) builder.getConverter("ROMAN");
	}

	@Test
	public void testValidInput() throws Exception {
		String input = "IV";
		int actualValue = converter.convert(input);
		int expectedValue = 4;

		assertEquals("roman conversion is improper", actualValue, expectedValue);

		input = "XIII";
		actualValue = converter.convert(input);
		expectedValue = 13;

		assertEquals("roman conversion is improper", actualValue, expectedValue);

		input = "CMXII";
		actualValue = converter.convert(input);
		expectedValue = 912;

		assertEquals("roman conversion is improper", actualValue, expectedValue);
	}

	@Test(expected = ConversionException.class)
	public void testInValidInput() throws Exception {
		String input = "IMXII";

		converter.convert(input);
	}


}
