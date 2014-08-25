package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OneSubtractionValidatorTest {
	private OneSubtractionValidator validator = new OneSubtractionValidator();

	@Test
	public void testWithProperInput() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.C, RomanChar.M, RomanChar.X, RomanChar.I};
		boolean isValid = validator.isValid(romanChars);

		assertTrue("one subtraction validation is improper", isValid);
	}

	@Test
	public void testWithMultipleSubtraction() {
		RomanChar[] romanChars = new RomanChar[] { RomanChar.I, RomanChar.C, RomanChar.M, RomanChar.X, RomanChar.I};
		boolean isValid = validator.isValid(romanChars);

		assertFalse("one subtraction validation is improper", isValid);
	}

	@Test
	public void testWithMultipleSimilarSubtraction() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.I, RomanChar.I, RomanChar.X};
		boolean isValid = validator.isValid(romanChars);

		assertFalse("one subtraction validation is improper", isValid);
	}
}
