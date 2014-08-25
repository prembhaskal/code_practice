package com.twr;

import com.twr.exception.UnknownConverterException;
import com.twr.roman.converter.CharToEnumConverter;
import com.twr.roman.RomanChar;
import com.twr.roman.evaluator.RomanEvaluator;
import com.twr.roman.converter.RomanNumeralConverter;
import com.twr.roman.validator.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalaxyConverterBuilder {

	public GalaxyConverter getConverter(String type) throws UnknownConverterException {
		if (type.equals("ROMAN")) {
			return buildRomanNumeralConverter();
		}
		else {
			throw new UnknownConverterException("unknown converter type requested : " + type);
		}
	}

	private RomanNumeralConverter buildRomanNumeralConverter() {
		List<Validator> validators = getRomanValidators();
		RomanEvaluator romanEvaluator = new RomanEvaluator();
		CharToEnumConverter charToEnumConverter = new CharToEnumConverter();

		return new RomanNumeralConverter(validators, romanEvaluator, charToEnumConverter);
	}

	private List<Validator> getRomanValidators() {
		List<Validator> validators = new ArrayList<>();
		int MAX_REPETITION = 3;
		validators.add(new MaxRepetitionValidator(MAX_REPETITION));

		RomanChar[] noRepeatChars = new RomanChar[] {RomanChar.D, RomanChar.L, RomanChar.V};
		validators.add(new NoRepetitionValidator(Arrays.asList(noRepeatChars)));

		RomanChar[][] validPrefixMainChars = new RomanChar[6][2];
		validPrefixMainChars[0] = new RomanChar[] {RomanChar.I, RomanChar.V};
		validPrefixMainChars[1] = new RomanChar[] {RomanChar.I, RomanChar.X};
		validPrefixMainChars[2] = new RomanChar[] {RomanChar.X, RomanChar.L};
		validPrefixMainChars[3] = new RomanChar[] {RomanChar.X, RomanChar.C};
		validPrefixMainChars[4] = new RomanChar[] {RomanChar.C, RomanChar.D};
		validPrefixMainChars[5] = new RomanChar[] {RomanChar.C, RomanChar.M};
		validators.add(new ProperPrecedenceValidator(validPrefixMainChars));

		RomanChar[] noSubtractChars = new RomanChar[] {RomanChar.D, RomanChar.L, RomanChar.V};
		validators.add(new NoSubtractionValidator(Arrays.asList(noSubtractChars)));
		validators.add(new OneSubtractionValidator());

		return validators;
	}
}
