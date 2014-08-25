package com.twr.query;

public class InputParserHelper {

	public static boolean isSymbolInfo(String input) {
		String[] tokens = input.split(" ");
		if (tokens.length == 3 && tokens[1].equals("is") && tokens[2].length() == 1)
			return true;
		return false;
	}

	public static boolean isItemInfo(String input) {
		return input.endsWith("Credits");
	}

	// question if ends with ?
	public static boolean isQuestionType(String input) {
		return input.endsWith("?");
	}

	public static boolean isSymbolValueQuestion(String input) {
		return input.startsWith("how much is ");
	}

	public static boolean isItemPriceQuestion(String input) {
		return input.startsWith("how many Credits is ");
	}

	public static boolean isItemToItem(String input) {
		String[] token = input.split(" ");

		if (token.length > 6 && token[0].equals("how") && token[1].equals("many") && token[3].equals("is"))
			return true;
		return false;
	}
}
