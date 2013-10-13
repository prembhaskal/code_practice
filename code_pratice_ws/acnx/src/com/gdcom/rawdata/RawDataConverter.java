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
		Node node = null;

		tokenizer.tokenize(line);

		int level = getLevel(tokenizer.getNextToken());
		String token = tokenizer.getNextToken();
		if (isId(level, token)) {
			String id = token;
			String name = tokenizer.getNextToken();
			node = createIdNode(level, name, id);
		}
		else {
			String name = token;
			String value = tokenizer.remainingToken();
			node = createTagNode(level, name, value);
		}

		return node;
	}

	private Node createIdNode(int level, String name, String id) {
		XmlAttribute idAttribute = new XmlAttribute("id", id);
		XmlElement xmlElement = new XmlElement(name, null, Arrays.asList(idAttribute), true);
		Node idNode = new Node(xmlElement, level, null);
		return idNode;
	}

	private Node createTagNode(int level, String name, String value) {
		XmlElement xmlElement = new XmlElement(name, value);
		Node tagNode = new Node(xmlElement, level, null);
		return tagNode;
	}

	private int getLevel(String token) {
		return Integer.parseInt(token);
	}

	private boolean isId(int level, String token) {
		return level==0 && token.matches("^@.*@$");
	}

}
