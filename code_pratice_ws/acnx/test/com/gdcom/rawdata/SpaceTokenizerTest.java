package com.gdcom.rawdata;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SpaceTokenizerTest {

	SpaceTokenizer spaceTokenizer = new SpaceTokenizer();
	String line = "word1 word2 word3 word4";

	@Test
	public void testNextTokensAreProper() throws Exception {
		spaceTokenizer.tokenize(line);

		assertEquals("token is not proper", "word1", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word2", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word3", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word4", spaceTokenizer.getNextToken());
	}

	@Test
	public void testRemainingTokenIsProper() throws Exception {
		spaceTokenizer.tokenize(line);

		assertEquals("token is not proper", "word1", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word2", spaceTokenizer.getNextToken());
		assertEquals("remaining token is not proper", "word3 word4", spaceTokenizer.remainingToken());
	}

	@Test
	public void testVariableTokensAreParsedProperly() throws Exception {
		String lineWithVarSpace = "word1    word2   word3  word4";
		spaceTokenizer.tokenize(lineWithVarSpace);

		assertEquals("token is not proper", "word1", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word2", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "word3 word4", spaceTokenizer.remainingToken());
	}

	@Test
	public void testStringCombination1() throws Exception {
		String line = "1 BIRT";
		spaceTokenizer.tokenize(line);

		assertEquals("token is not proper", "1", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "BIRT", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "", spaceTokenizer.remainingToken());
	}

	@Test
	public void testStringCombination2() throws Exception {
		String line = "1 CONT Earl of Warwick, \"the King-Maker\"";
		spaceTokenizer.tokenize(line);

		assertEquals("token is not proper", "1", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "CONT", spaceTokenizer.getNextToken());
		assertEquals("token is not proper", "Earl of Warwick, \"the King-Maker\"", spaceTokenizer.remainingToken());
	}
}
