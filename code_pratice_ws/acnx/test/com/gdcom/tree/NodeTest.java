package com.gdcom.tree;

import com.gdcom.elements.XmlElement;
import org.junit.Test;

public class NodeTest {

	XmlElement xmlElement = new XmlElement("id", "1");
	Node node = new Node(xmlElement, 0, null);

	@Test (expected = IllegalArgumentException.class)
	public void testExceptionOnNullChildNodes() {
		node.addChildNode(null);
	}
}
