package com.twr.query;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataProcessorHelperTest {

	@Test
	public void testIsSymbolInfo() throws Exception {
		String input = "glob is I";
		assertTrue("Parsing is improper", InputParserHelper.isSymbolInfo(input));

		input = "glob is not I";
		assertFalse("Parsing is improper", InputParserHelper.isSymbolInfo(input));
	}

	@Test
	public void testIsItemInfo() throws Exception {
		String input = "glob glob Silver is 34 Credits";
		assertTrue("Parsing is improper", InputParserHelper.isItemInfo(input));

		input = "glob glob Silver is 34 Credits and nothing";
		assertFalse("Parsing is improper", InputParserHelper.isItemInfo(input));
	}

	@Test
	public void testIsQuestionType() throws Exception {
		String input = "what is this?";
		assertTrue("Parsing is improper", InputParserHelper.isQuestionType(input));

		input = " not a question";
		assertFalse("Parsing is improper", InputParserHelper.isQuestionType(input));
	}

	@Test
	public void testIsSymbolValueQuestion() throws Exception {
		String input = "how much is pish tegj glob glob ?";
		assertTrue("Parsing is improper", InputParserHelper.isSymbolValueQuestion(input));

		input = " how much not a question";
		assertFalse("Parsing is improper", InputParserHelper.isSymbolValueQuestion(input));
	}

	@Test
	public void testIsItemPriceQuestion() throws Exception {

		String input = "how many Credits is glob prok Silver ?";
		assertTrue("Parsing is improper", InputParserHelper.isItemPriceQuestion(input));

		input = " how many is not a question";
		assertFalse("Parsing is improper", InputParserHelper.isItemPriceQuestion(input));
	}
}
