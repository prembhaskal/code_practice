package com.gdcom.rawdata;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import com.gdcom.tree.Node;
import java.util.Arrays;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RawDataConverterTest {

	Tokenizer stringTokenizer = new SpaceTokenizer();
	RawDataConverter rawDataConverter = new RawDataConverter(stringTokenizer);

	@Test
	public void testIdParsedCorrectly() {
		String idLine = "0 @I1@ INDI";
		Node actualNode = rawDataConverter.convertToNode(idLine);

		XmlAttribute idAttribute = new XmlAttribute("id", "I1");
		XmlElement expIdElement = new XmlElement("INDI", null, Arrays.asList(idAttribute), true);
		Node expectedNode = new Node(expIdElement,0, null);

		assertEquals("id node is not properly created", expectedNode, actualNode);
	}

	@Test
	public void testNormalTagParsedCorrectly() {
		String tagLine = "2 GIVN Jamis Gordon";
		Node actualNode = rawDataConverter.convertToNode(tagLine);

		XmlElement tagElement = new XmlElement("GIVN", "Jamis Gordon");
		Node expectedNode = new Node(tagElement, 2, null);

		assertEquals("tag node is not properly created", expectedNode, actualNode);
	}

	@Test
	public void testTagCombination1() {
		String tagLine = "1 CONT ";
		Node actualNode = rawDataConverter.convertToNode(tagLine);

		XmlElement tagElement = new XmlElement("CONT", "");
		Node expectedNode = new Node(tagElement, 1, null);

		assertEquals("tag node is not properly created", expectedNode, actualNode);
	}

	@Test
	public void testTagCombination2() {
		String tagLine = "1 HUSB @I0226@";
		Node actualNode = rawDataConverter.convertToNode(tagLine);

		XmlElement tagElement = new XmlElement("HUSB", "@I0226@");
		Node expectedNode = new Node(tagElement, 1, null);

		assertEquals("tag node is not properly created", expectedNode, actualNode);
	}

	@Test
	public void testTagCombination3() {
		String tagLine = "1 NAME Margaret /Holand/";
		Node actualNode = rawDataConverter.convertToNode(tagLine);

		XmlElement tagElement = new XmlElement("NAME", "Margaret /Holand/");
		Node expectedNode = new Node(tagElement, 1, null);

		assertEquals("tag node is not properly created", expectedNode, actualNode);
	}


}
