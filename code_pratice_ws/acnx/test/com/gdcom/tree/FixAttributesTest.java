package com.gdcom.tree;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

public class FixAttributesTest {
	FixAttributes fixAttributes = new FixAttributes();

	@Test
	public void testAttributesFixedAreProper1() throws Exception {
		XmlAttribute idAttribute = new XmlAttribute("id", "1");
		List<XmlAttribute> list = new ArrayList<>();
		list.add(idAttribute);
		XmlElement idElement = new XmlElement("elem1", null, list, true);
		XmlElement nameElement = new XmlElement("name", "prem");
		XmlElement surnElement = new XmlElement("surn", "bhaskal");
		XmlElement sexElement = new XmlElement("sex", "M");

		Node idNode = new Node(idElement, 0, null);
		Node nameNode = new Node(nameElement, 1, null);
		Node surnNode = new Node(surnElement, 2, null);
		Node sexNode = new Node(sexElement, 1, null);

		List<Node> nodeList = new ArrayList<>();
		nodeList.add(idNode);
		nodeList.add(nameNode);
		nodeList.add(surnNode);
		nodeList.add(sexNode);

		TreeCreator treeCreator = new TreeCreator();
		treeCreator.formTreeFromNodes(nodeList);

		// fix the attributes
		fixAttributes.fixAttributeForTree(idNode);

		// check attributes should be present and proper.
		List<XmlAttribute> attributes = nameElement.getAttributes();
		assertTrue("attributes are not proper", attributes.size() == 1);
		XmlAttribute actualAttribute = attributes.get(0);
		XmlAttribute expectedAttribute = new XmlAttribute("value", "prem");
		assertEquals("attribute is not proper", expectedAttribute, actualAttribute);

		// the value in the element should be nullified
		assertNull("xml-element value is not nullified", nameElement.getValue());

		// attribute of an id element should not change.
		attributes = idElement.getAttributes();
		assertTrue("id attributes are not proper", attributes.size()==1);
		actualAttribute = attributes.get(0);
		expectedAttribute = new XmlAttribute("id", "1");
		assertEquals("attribute is not proper", expectedAttribute, actualAttribute);

		// no where else we should have attributes added
		assertEquals("no attributes should be added", surnElement.getAttributes().size(), 0);
		assertEquals("no attributes should be added", sexElement.getAttributes().size(), 0);
	}
}
