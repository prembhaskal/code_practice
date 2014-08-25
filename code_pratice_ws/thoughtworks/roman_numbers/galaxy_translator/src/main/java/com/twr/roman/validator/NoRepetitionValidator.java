package com.twr.roman.validator;

import com.twr.roman.RomanChar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * validates whether prohibited chars are repeated.
 * It takes the repetition prohibited chars as input.
 */
public class NoRepetitionValidator implements Validator {

	private Set<RomanChar> noRepeats;

	public NoRepetitionValidator(List<RomanChar> noRepeatsList) {
		this.noRepeats = new HashSet<>(noRepeatsList);
	}

	@Override
	public boolean isValid(RomanChar[] romanChars) {
		RomanChar prefix = romanChars[0];

		for (int i = 1; i < romanChars.length; i++) {
			if (romanChars[i] == prefix && isNoRepeatSymbol(prefix)) {
				return false;
			}

			prefix = romanChars[i];
		}

		return true;
	}

	private boolean isNoRepeatSymbol(RomanChar romanChar) {
		return noRepeats.contains(romanChar);
	}
}
