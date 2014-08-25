package com.twr.roman.validator;

import com.twr.roman.RomanChar;

/**
* validates that the combination first - second - third is proper.
* that is only one small value is subtracted from the bigger value.
*/
public class OneSubtractionValidator implements Validator {

	@Override
	public boolean isValid(RomanChar[] romanChars) {
		int len = romanChars.length;

		if (len < 3)
			return true;

		RomanChar third = romanChars[len - 1];
		RomanChar second = romanChars[len - 2];

		for (int i = len - 3; i >= 0; i--) {
			RomanChar first = romanChars[i];
			// check if we have 2 consecutive subtraction.
			if (first.getValue() <= second.getValue() && second.getValue() < third.getValue())
				return false;

			third = second;
			second = first;
		}

		return true;
	}
}
