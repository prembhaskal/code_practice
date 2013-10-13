package com.gdcom.tree;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.util.List;

/**
 * fix the nodes.
 * The XmlElement of the node having child nodes, should have the value as an attribute.
 * This class does that post creation of the Tree.
 */
public class FixAttributes {

	public void fixAttributeForTree(Node rootNode) {
		fixAttributesForNode(rootNode);
	}

	private void fixAttributesForNode(Node node) {
		List<Node> childNodes = node.getChildNodes();

		if (!childNodes.isEmpty()) {
			changeAttributeForNode(node);
			for (Node childNode : childNodes) {
				fixAttributesForNode(childNode);
			}
		}
	}

	private void changeAttributeForNode(Node node) {
		XmlElement xmlElement = node.getXmlElement();

		if (xmlElement.isIdElement())
			return;

		XmlAttribute xmlAttribute = new XmlAttribute("value", xmlElement.getValue());
		xmlElement.addAttribute(xmlAttribute);
		xmlElement.nullifyValue();
	}
}
