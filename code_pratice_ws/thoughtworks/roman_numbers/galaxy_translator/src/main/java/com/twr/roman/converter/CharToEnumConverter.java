package com.twr.roman.converter;

import com.twr.exception.ConversionException;
import com.twr.roman.RomanChar;
import java.util.HashMap;
import java.util.Map;

/**
 * converter from input string to RomanChar Enum Array.
 */
public class CharToEnumConverter {

	private Map<Character, RomanChar> charToRomanNumeralMap;

	public CharToEnumConverter() {
		initializeMap();
	}

	private void initializeMap() {
		charToRomanNumeralMap = new HashMap<>();

		for (RomanChar romanChar : RomanChar.values()) {
			charToRomanNumeralMap.put(romanChar.getName(), romanChar);
		}
	}

	public RomanChar[] convertStringToRomanChar(String input) throws ConversionException {
		RomanChar[] romanChars = null;
		try {
			char[] chars = input.toCharArray();
			romanChars = new RomanChar[chars.length];
			int i = 0;
			for (char ch : chars) {
				if (!charToRomanNumeralMap.containsKey(ch))
					throw new ConversionException("unexpected roman character " + ch);

				RomanChar romanChar = charToRomanNumeralMap.get(ch);
				romanChars[i++] = romanChar;
			}
		} catch (ConversionException e) {
			System.out.println("Error converting into roman array " + e.getMessage());
		}


		return romanChars;
	}
}
