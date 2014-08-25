package com.twr.roman.validator;

import com.twr.roman.RomanChar;

/**
 * validator to check that only valid subtractions are allowed.
 * It takes the precedence to validate against as the input.
 */
public class ProperPrecedenceValidator implements Validator {
	private RomanChar[][] validPrefixMainCharPair;

	public ProperPrecedenceValidator(RomanChar[][] validPrefixMainCharPair) {
		this.validPrefixMainCharPair = validPrefixMainCharPair;
	}

	@Override
	public boolean isValid(RomanChar[] romanChars) {
		RomanChar prefix = romanChars[0];

		for (int i = 1; i < romanChars.length; i++) {
			RomanChar main = romanChars[i];
			if (prefix.getValue() < main.getValue()) {
				if (!checkIfValidPrecedence(prefix, main)) {
					return false;
				}
			}

			prefix = main;
		}

		return true;
	}

	private boolean checkIfValidPrecedence(RomanChar prefix, RomanChar main) {
		for (RomanChar[] prefixMainChar : validPrefixMainCharPair) {
			if (prefix == prefixMainChar[0] && main == prefixMainChar[1])
				return true;
		}

		return false;

	}
}
