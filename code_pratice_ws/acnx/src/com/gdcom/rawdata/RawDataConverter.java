package com.gdcom.rawdata;

import com.gdcom.tree.Node;

public class RawDataConverter {

	private Tokenizer tokenizer;

	public RawDataConverter(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public Node convertToNode(String line) {
		tokenizer.tokenize(line);
		return null;
	}

	private int getLevel() {
		return Integer.parseInt(tokenizer.getNextToken());
	}
}
