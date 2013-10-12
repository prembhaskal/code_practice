package com.gdcom.rawdata;

public interface Tokenizer {

	void tokenize(String str);
	String getNextToken();
	String remainingToken();
}
