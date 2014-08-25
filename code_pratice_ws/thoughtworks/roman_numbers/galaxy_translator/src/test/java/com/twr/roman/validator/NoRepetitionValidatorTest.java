package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoRepetitionValidatorTest {

	private RomanChar[] noRepeatChars = new RomanChar[]{RomanChar.D, RomanChar.L};
	private NoRepetitionValidator noRepetitionValidator = new NoRepetitionValidator(Arrays.asList(noRepeatChars));

	@Test
	public void testWithRepeatedChars() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.M, RomanChar.D, RomanChar.D, RomanChar.I, RomanChar.I};
		boolean isValid = noRepetitionValidator.isValid(romanChars);

		assertFalse("no-repeat validation is improper", isValid);
	}

	@Test
	public void testWithNoRepeatChars() {
		RomanChar[] romanChars = new RomanChar[]{RomanChar.D, RomanChar.I, RomanChar.X};
		boolean isValid = noRepetitionValidator.isValid(romanChars);

		assertTrue("no-repeat validation is improper", isValid);
	}
}
