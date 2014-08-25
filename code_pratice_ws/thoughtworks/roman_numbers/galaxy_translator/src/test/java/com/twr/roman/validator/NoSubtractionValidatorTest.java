package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoSubtractionValidatorTest {
	private RomanChar[] noSubtractChars = new RomanChar[]{RomanChar.D, RomanChar.L};
	private NoSubtractionValidator validator = new NoSubtractionValidator(Arrays.asList(noSubtractChars));

	@Test
	public void testWithProhibitedCharsPresent() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.D, RomanChar.M, RomanChar.I, RomanChar.I};
		boolean isValid = validator.isValid(romanChars);

		assertFalse("no subtraction validation is improper", isValid);
	}

	@Test
	public void testWithProhibitedCharsAbsent() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.C, RomanChar.M, RomanChar.I, RomanChar.X, RomanChar.I};
		boolean isValid = validator.isValid(romanChars);

		assertTrue("no subtraction validation is improper", isValid);
	}

}
