package com.twr.query;

import com.twr.GalaxyConverter;
import com.twr.exception.ConversionException;
import com.twr.exception.UnknownInputException;
import java.util.HashMap;
import java.util.Map;

/**
 * DataProcessor parses the english, maintains the symbol vs value,
 * maintains item vs price and sends the appropriate part for roman conversion.
 * And prints the output to console for questions.
 * Throws appropriate exception when input cannot be parsed.
 */
public class DataProcessor {
	private Map<String, Character> keyWordVsSymbol = new HashMap<>();
	private Map<String, Integer> itemVsPrice = new HashMap<>();

	private GalaxyConverter galaxyConverter;

	public DataProcessor(GalaxyConverter galaxyConverter) {
		this.galaxyConverter = galaxyConverter;
	}

	public void processInput(String input) throws Exception {
		try {
			// try to check what kind of input is and act accordingly
			if (InputParserHelper.isQuestionType(input)) {
				processQuestionInput(input);
			}
			else {
				processInfoInput(input);
			}

		}
		catch (Exception e) {
			System.out.println("Unable to parse the input: " + input);
		}
	}

	private void processInfoInput(String input) throws Exception {
		if (InputParserHelper.isSymbolInfo(input)) {
			updateSymbolInfo(input);
		}
		else if (InputParserHelper.isItemInfo(input)) {
			updateItemInfo(input);
		}
		else {
			throw new UnknownInputException("unknown input");
		}
	}

	private void processQuestionInput(String input) throws Exception {
		if (InputParserHelper.isSymbolValueQuestion(input)) {
			processSymbolValueQuestion(input);
		}
		else if (InputParserHelper.isItemPriceQuestion(input)) {
			processItemPriceQuestion(input);
		}
		else if (InputParserHelper.isItemToItem(input)) {
			processItemToItemQuestion(input);
		}
		else {
			throw new UnknownInputException("unknown input");
		}
	}

	private void updateSymbolInfo(String input) {
		String[] tokens = input.split(" ");
		String word = tokens[0];
		Character ch = tokens[2].charAt(0);

		keyWordVsSymbol.put(word, ch);
	}

	private void updateItemInfo(String input) throws ConversionException {
		String[] tokens = input.split(" ");
		int len = tokens.length;
		int price = Integer.parseInt(tokens[len - 2]);

		String item = tokens[len - 4];

		String symbol = convertWordToSymbol(tokens, 0, len - 1 - 4);
		int value = galaxyConverter.convert(symbol);

		int itemPrice = price/value;
		itemVsPrice.put(item, itemPrice);
	}

	private void processSymbolValueQuestion(String input) throws ConversionException {
		String[] tokens = input.split(" ");
		int len = tokens.length;

		String symbol = convertWordToSymbol(tokens, 3, len - 1 - 1);
		int value = galaxyConverter.convert(symbol);
		String actualWord = getWordFromInput(tokens, 3, len - 1 - 1);
		System.out.println(actualWord + "is " + value);
	}

	private void processItemPriceQuestion(String input) throws ConversionException {
		String[] tokens = input.split(" ");
		int len = tokens.length;

		String item = tokens[len - 2];
		String symbol = convertWordToSymbol(tokens, 4, len - 1 - 2);
		int totalItems = galaxyConverter.convert(symbol);

		int totalPrice = itemVsPrice.get(item) * totalItems;

		String actualWord = getWordFromInput(tokens, 4, len - 1 - 1);
		System.out.println(actualWord + "is " + totalPrice + " Credits");
	}

	private void processItemToItemQuestion(String input) throws ConversionException {
		String[] tokens = input.split(" ");
		int len = tokens.length;

		String queryItem = tokens[2];
		String givenItem = tokens[len - 2];

		int priceOfQueryItem = itemVsPrice.get(queryItem);
		int priceOfGivenItem = itemVsPrice.get(givenItem);

		String symbol = convertWordToSymbol(tokens, 4, len - 1 - 2);
//		String symbol = getWordFromInput(tokens, 4, len - 1 - 2);
		int givenItemQuantity = galaxyConverter.convert(symbol);

		int totalPriceOfGivenItem = priceOfGivenItem * givenItemQuantity;

		int queryItemQuantity = totalPriceOfGivenItem/priceOfQueryItem;

		System.out.println(queryItemQuantity);
	}

	private String getWordFromInput(String[] tokens, int start, int end) {
		String word = "";
		for (int i = start; i <= end; i++) {
			word += tokens[i] + " ";
		}
		return word;
	}

	private String convertWordToSymbol(String[] tokens, int start, int end) {
		String symbols = "";
		for (int i = start; i <= end; i++) {
			Character ch = keyWordVsSymbol.get(tokens[i]);
			symbols += ch;
		}

		return symbols;
	}

}
