package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProperPrecedenceValidatorTest {
	private RomanChar[][] validPrefixMainChars = new RomanChar[2][2];
	private ProperPrecedenceValidator validator = new ProperPrecedenceValidator(validPrefixMainChars);

	@Before
	public void setUp() {
		validPrefixMainChars[0] = new RomanChar[] {RomanChar.I, RomanChar.V};
		validPrefixMainChars[1] = new RomanChar[] {RomanChar.I, RomanChar.X};
	}

	@Test
	public void testWithProperPrecedence() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.M, RomanChar.M, RomanChar.I, RomanChar.X, RomanChar.I};

		boolean isValid = validator.isValid(romanChars);

		assertTrue("precedence validation is improper", isValid);
	}

	@Test
	public void testWithImproperPrecedence() {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.C, RomanChar.M, RomanChar.I};
		boolean isValid = validator.isValid(romanChars);

		assertFalse("precedence validation is improper", isValid);
	}

}
