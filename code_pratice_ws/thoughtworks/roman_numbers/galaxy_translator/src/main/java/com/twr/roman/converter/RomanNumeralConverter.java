package com.twr.roman.converter;

import com.twr.GalaxyConverter;
import com.twr.exception.ConversionException;
import com.twr.roman.RomanChar;
import com.twr.roman.evaluator.RomanEvaluator;
import com.twr.roman.validator.Validator;
import java.util.List;

public class RomanNumeralConverter implements GalaxyConverter {

	private List<Validator> validators;
	private RomanEvaluator romanEvaluator;
	private CharToEnumConverter charToEnumConverter;

	public RomanNumeralConverter(List<Validator> validators, RomanEvaluator romanEvaluator,
								 CharToEnumConverter charToEnumConverter) {
		this.validators = validators;
		this.romanEvaluator = romanEvaluator;
		this.charToEnumConverter = charToEnumConverter;
	}

	@Override
	public int convert(String input) throws ConversionException {
		RomanChar[] romanChars = charToEnumConverter.convertStringToRomanChar(input);

		// check if the input roman numeral is valid, if not fail and return.
		boolean isValid = validate(romanChars);
		if (!isValid)
			throw new ConversionException("improper roman numeral. validation failed");

		// evaluate the input now since it is a valid roman numeral.
		return romanEvaluator.evaluate(romanChars);
	}

	// run all validators on the input, fail if any one of it fails.
	private boolean validate(RomanChar[] romanChars) {
		for (Validator validator : validators) {
			if (!validator.isValid(romanChars))
				return false;
		}

		return true;
	}

}
