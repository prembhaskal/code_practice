package com.gdcom.rawdata;

import java.util.NoSuchElementException;

public class SpaceTokenizer implements Tokenizer {

	private String DEFAULT_DELIMITER = " ";
	private String[] tokens;

	private int currentToken;

	@Override
	public void tokenize(String line) {
		line = line.replaceAll("[\\ ]+", " "); // replace continuous spaces with a single one.
		tokens = line.split(" ");
		currentToken = -1;
	}

	@Override
	public String getNextToken() {
		int newToken = currentToken + 1;
		if (newToken > tokens.length)
			throw new NoSuchElementException();
		currentToken = newToken;
		return tokens[newToken];
	}

	@Override
	public String remainingToken() {
		StringBuilder sb = new StringBuilder();
		for (int i = currentToken+1; i < tokens.length; i++) {
			sb.append(tokens[i] + " ");
		}
		// remove last space.
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length()-1);

		return sb.toString();
	}
}
