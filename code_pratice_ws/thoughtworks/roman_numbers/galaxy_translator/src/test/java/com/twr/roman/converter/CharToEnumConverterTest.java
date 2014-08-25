package com.twr.roman.converter;

import com.twr.exception.ConversionException;
import com.twr.roman.RomanChar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharToEnumConverterTest {

	private CharToEnumConverter charToEnumConverter = new CharToEnumConverter();

	@Test
	public void testStringToRomanCharsProperInput() throws Exception {
		String input = "CMXIII";
		RomanChar[] romanChars = charToEnumConverter.convertStringToRomanChar(input);

		assertEquals("conversion is improper", romanChars.length, 6);

		boolean isProper = romanChars[0] == RomanChar.C;
		isProper &= (romanChars[1] == RomanChar.M);
		isProper &= (romanChars[2] == RomanChar.X);
		isProper &= (romanChars[3] == RomanChar.I);
		isProper &= (romanChars[4] == RomanChar.I);
		isProper &= (romanChars[5] == RomanChar.I);

		assertTrue("conversion is improper", isProper);
	}

	@Test(expected = ConversionException.class)
	public void testNonExistentCharConversion() throws Exception {
		String input = "CMB";
		charToEnumConverter.convertStringToRomanChar(input);
	}
}
