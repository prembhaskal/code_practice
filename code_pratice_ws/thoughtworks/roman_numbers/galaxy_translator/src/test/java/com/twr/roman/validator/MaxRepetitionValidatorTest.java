package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaxRepetitionValidatorTest {
	private int REPEATS = 3;
	private MaxRepetitionValidator maxRepetitionValidator = new MaxRepetitionValidator(REPEATS);

	@Test
	public void testWithRepeatedChars() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.X, RomanChar.I, RomanChar.I, RomanChar.I, RomanChar.I};
		boolean isValid = maxRepetitionValidator.isValid(romanChars);
		assertFalse("repetition validation is improper", isValid);
	}

	@Test
	public void testWithLesserRepeats() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.X, RomanChar.I, RomanChar.I, RomanChar.I};
		boolean isValid = maxRepetitionValidator.isValid(romanChars);
		assertTrue("repetition validation is improper", isValid);
	}

	@Test
	public void testWithNoRepeats() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.I, RomanChar.X, RomanChar.I};
		boolean isValid = maxRepetitionValidator.isValid(romanChars);
		assertTrue("repetition validation is improper", isValid);
	}
}
