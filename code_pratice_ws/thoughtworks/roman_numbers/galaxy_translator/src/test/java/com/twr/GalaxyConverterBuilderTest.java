package com.twr;

import com.twr.exception.UnknownConverterException;
import com.twr.roman.converter.RomanNumeralConverter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GalaxyConverterBuilderTest {

	GalaxyConverterBuilder builder = new GalaxyConverterBuilder();

	@Test
	public void testRomanConverterIsReturned() throws UnknownConverterException {
		GalaxyConverter galaxyConverter = builder.getConverter("ROMAN");

		assertTrue("returned converter is proper", galaxyConverter instanceof RomanNumeralConverter);
	}

	@Test(expected = UnknownConverterException.class)
	public void exceptionOnUnknownConverter() throws UnknownConverterException {
		builder.getConverter("UNKNOWN");
	}
}
