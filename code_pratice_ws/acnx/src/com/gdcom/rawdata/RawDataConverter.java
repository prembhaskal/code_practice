package com.gdcom.rawdata;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import com.gdcom.tree.Node;
import java.util.Arrays;

// TODO add validation for the data.
public class RawDataConverter {

	private Tokenizer tokenizer;

	public RawDataConverter(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	/**
	 * The xmlElement of the node created will not have an xmlAttribute unless it is an id element.
	 * This is because this will have no idea of the successive nodes of this node.
	 * @param line
	 * @return
	 */
	public Node convertToNode(String line) {
		Node node;

		tokenizer.tokenize(line);

		int level = getLevel(tokenizer.getNextToken());
		if (isIdNode(level, tokenizer.getNextToken())) {
			node = createIdNode(level, tokenizer.getNextToken(), tokenizer.getNextToken());
		}
		else {
			node = createTagNode(level, tokenizer.getNextToken(), tokenizer.remainingToken());
		}

		return node;
	}

	private Node createIdNode(int level, String name, String id) {
		XmlAttribute idAttribute = new XmlAttribute("id", id);
		XmlElement xmlElement = new XmlElement(name, null, Arrays.asList(idAttribute), true);
		return new Node(xmlElement, level, null);
	}

	private Node createTagNode(int level, String name, String value) {
		XmlElement xmlElement = new XmlElement(name, value);
		return new Node(xmlElement, level, null);
	}

	private int getLevel(String token) {
		return Integer.parseInt(token);
	}

	private boolean isIdNode(int level, String token) {
		return level==0 && token.matches("^@.*@$");
	}

}
