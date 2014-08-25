package com.twr.roman;

import com.twr.roman.evaluator.RomanEvaluator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanEvaluatorTest {

	RomanEvaluator romanEvaluator = new RomanEvaluator();

	@Test
	public void testEvaluator() throws Exception {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.M, RomanChar.C, RomanChar.M, RomanChar.I, RomanChar.I, RomanChar.I};
		int actualValue = romanEvaluator.evaluate(romanChars);
		int expectedValue = 1903;
		assertEquals(" evaluated value is incorrect", expectedValue, actualValue);
	}

	@Test
	public void testRepeat() throws Exception {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.I, RomanChar.I, RomanChar.I};
		int actualValue = romanEvaluator.evaluate(romanChars);
		int expectedValue = 3;
		assertEquals(" evaluated value is incorrect", expectedValue, actualValue);
	}

	@Test
	public void testDoubleRepeat() throws Exception {
		RomanChar[] romanChars = new RomanChar[] {RomanChar.X, RomanChar.X, RomanChar.I, RomanChar.I};
		int actualValue = romanEvaluator.evaluate(romanChars);
		int expectedValue = 22;
		assertEquals(" evaluated value is incorrect", expectedValue, actualValue);
	}
}
