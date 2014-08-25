package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validator to check that the prohibited characters are not subtracted.
 */
public class NoSubtractionValidator implements Validator {

	private Set<RomanChar> noSubtractChars;

	public NoSubtractionValidator(List<RomanChar> noSubtractCharsList) {
		this.noSubtractChars = new HashSet<>(noSubtractCharsList);
	}

	@Override
	public boolean isValid(RomanChar[] romanChars) {
		RomanChar previous = romanChars[0];

		for (int i = 1; i < romanChars.length; i++) {
			RomanChar present = romanChars[i];
			if (previous.getValue() < present.getValue()) {
				if (isNoSubtractChar(previous))
					return false;
			}

			previous = present;
		}

		return true;
	}

	private boolean isNoSubtractChar(RomanChar romanChar) {
		return noSubtractChars.contains(romanChar);
	}
}
