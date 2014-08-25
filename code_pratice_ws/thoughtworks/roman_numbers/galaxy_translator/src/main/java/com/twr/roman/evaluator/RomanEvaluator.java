package com.twr.roman.evaluator;

import com.twr.roman.RomanChar;

public class RomanEvaluator {

	/**
	 * evaluation is done by iterating the input chars in reverse order,
	 * adding to value if it is greater or equal to previous
	 * subtracting from value if it smaller than previous.
	 *
	 * @param romanChars
	 * @return
	 */
	public int evaluate(RomanChar[] romanChars) {
		int value;
		int len = romanChars.length;
		RomanChar main = romanChars[len - 1];

		value = main.getValue();

		// iterate from last to first
		for (int i = len - 2; i >= 0; i--) {
			RomanChar prefix = romanChars[i];

			// subtract value if prefix is a lower number,
			if (prefix.getValue() < main.getValue()) {
				value -= prefix.getValue();
			}
			else { // add if prefix is same or greater number.
				value += prefix.getValue();
			}

			main = prefix;
		}

		return value;
	}
}
