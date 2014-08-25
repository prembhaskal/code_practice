package com.twr.roman.validator;

import com.twr.roman.RomanChar;

/**
 * validates the max continuous repetition for any character is proper.
 */
public class MaxRepetitionValidator implements Validator {
	private int maxRepsAllowed;

	public MaxRepetitionValidator(int maxRepsAllowed) {
		this.maxRepsAllowed = maxRepsAllowed;
	}

	@Override
	public boolean isValid(RomanChar[] romanChars) {
		int reps = 1;

		RomanChar prefix = romanChars[0];

		for (int i = 1; i < romanChars.length; i++) {
			if (romanChars[i] == prefix) {
				reps++;
			}
			else {
				reps = 1;
			}
			prefix = romanChars[i];

			if (reps > maxRepsAllowed)
				return false;
		}

		return true;
	}
}
