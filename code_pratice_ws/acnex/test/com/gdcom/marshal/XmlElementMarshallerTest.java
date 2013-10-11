package com.gdcom.marshal;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class XmlElementMarshallerTest {

	XmlElementMarshaller elementMarshaller = new XmlElementMarshaller();
	String name = "name";
	String value = "prem kumar";
	XmlElement xmlElement = new XmlElement(name, value);

	@Test(expected = MarshalException.class)
	public void testMarshalExceptionOnPassingInvalidObject() throws Exception{
		Object object = new Object();
		elementMarshaller.createTag(object);
	}

	@Test
	public void createTagXmlElementSansAttributes() throws Exception {
		name = "name";
		value = "prem kumar";

		String expectedXmlString = "<" + name + ">" + value + "</" + name + ">";
		String actualXmlString = elementMarshaller.createTag(xmlElement);

		assertEquals("the encoded xml is not proper", expectedXmlString, actualXmlString);
	}

	@Test
	public void createStartingTag() throws Exception {
		String actualString = elementMarshaller.createStartingTag(xmlElement);
		String expectedString = "<" + xmlElement.getName() + ">";

		assertEquals("the encoded start tag is not proper", expectedString, actualString);
	}

	@Test
	public void createEmptyTag() throws Exception {
		String actualString  = elementMarshaller.createEndingTag(xmlElement);
		String expectedString = "</" + xmlElement.getName() + ">";

		assertEquals("the encoded end tag is not proper", expectedString, actualString);
	}

	@Test
	public void createXmlElementWithNullValue() throws Exception {
		name = "name";
		value = null;

		XmlElement xmlElement = new XmlElement(name, value);
		String expectedXmlString = "<" + name + "></" + name + ">";
		String actualXmlString = elementMarshaller.createTag(xmlElement);

		assertEquals("the encoded xml is not proper", expectedXmlString, actualXmlString);
	}

	@Test
	public void marshalXmlElementWithSingleAttribute() throws Exception {
		name = "name";
		value = "prem kumar";

		String attName = "id";
		String attValue = "32024";
		XmlAttribute xmlAttribute = new XmlAttribute(attName, attValue);
		List<XmlAttribute> attributes = new ArrayList<>();
		attributes.add(xmlAttribute);

		XmlElement xmlElement = new XmlElement(name, value, attributes);

		String expectedString = "<" + name + " " + attName + "=\"" + attValue + "\">";
		String actualString = elementMarshaller.createStartingTag(xmlElement);

		assertEquals("the encoded xml is not proper", expectedString, actualString);
	}
}
