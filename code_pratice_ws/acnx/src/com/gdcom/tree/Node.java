package com.gdcom.tree;

import com.gdcom.elements.XmlElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Node of a tree.
 */
public class Node {

	private XmlElement xmlElement;
	private List<Node> childNodes;
	private int level;
	private Node parentNode;

	public Node(XmlElement xmlElement, int level, Node parentNode) {
		this.xmlElement = xmlElement;
		this.level = level;
		this.parentNode = parentNode;
		childNodes = new ArrayList<>();
	}

	public int getLevel() {
		return level;
	}

	public XmlElement getXmlElement() {
		return xmlElement;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node node) {
		parentNode = node;
	}

	public List<Node> getChildNodes() {
		return Collections.unmodifiableList(childNodes);
	}

	public void addChildNode(Node childNode) {
		if (childNode == null) {
			throw new IllegalArgumentException("child node cannot be null");
		}
		childNodes.add(childNode);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Node node = (Node) o;

		if (level != node.level) return false;
		if (childNodes != null ? !childNodes.equals(node.childNodes) : node.childNodes != null) return false;
		if (parentNode != null ? !parentNode.equals(node.parentNode) : node.parentNode != null) return false;
		if (xmlElement != null ? !xmlElement.equals(node.xmlElement) : node.xmlElement != null) return false;

		return true;
	}
}
