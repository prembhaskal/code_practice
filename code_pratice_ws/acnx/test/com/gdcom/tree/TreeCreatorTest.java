package com.gdcom.tree;

import com.gdcom.elements.XmlElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TreeCreatorTest {

	TreeCreator treeCreator = new TreeCreator();

	@Test(expected = TreeException.class)
	public void testTreeExceptionOnEmptyList() throws Exception {
		treeCreator.formTreeFromNodes(Collections.<Node>emptyList());
	}

	@Test(expected = TreeException.class)
	public void testTreeExceptionWhenFirstNodeIsNotRoot() throws Exception {
		Node rootNode = new Node(new XmlElement("e1", null), 0, null);
		Node element1Node = new Node(new XmlElement("e2", null), 1, null);

		List<Node> nodeList = new ArrayList<>();
		nodeList.add(element1Node);
		nodeList.add(rootNode);

		treeCreator.formTreeFromNodes(nodeList);
	}

	@Test
	public void testNodesAreProperlyLinked1() throws Exception {
		Node rootNode = new Node(new XmlElement("e1", null), 0, null);
		Node element1Node = new Node(new XmlElement("e2", null), 1, null);
		Node nameNode = new Node(new XmlElement("e3", null), 2, null);
		Node surnNode = new Node(new XmlElement("e4", null), 3, null);
		Node middleNode = new Node(new XmlElement("e5", null), 3, null);
		Node sexNode = new Node(new XmlElement("e6", null), 2, null);

		List<Node> nodeList = new ArrayList<>();
		nodeList.add(rootNode);
		nodeList.add(element1Node);
		nodeList.add(nameNode);
		nodeList.add(surnNode);
		nodeList.add(middleNode);
		nodeList.add(sexNode);

		Node actualRootNode = treeCreator.formTreeFromNodes(nodeList);

		// check the nodes are properly linked.
		assertEquals("root node is not properly formed", actualRootNode, rootNode);

		// check the parent nodes are proper.
		assertEquals("parent is not proper", element1Node.getParentNode(), rootNode);
		assertEquals("parent is not proper", nameNode.getParentNode(), element1Node);
		assertEquals("parent is not proper", surnNode.getParentNode(), nameNode);
		assertEquals("parent is not proper", middleNode.getParentNode(), nameNode);
		assertEquals("parent is not proper", sexNode.getParentNode(), element1Node);

		// check the children are proper.

	}
}
