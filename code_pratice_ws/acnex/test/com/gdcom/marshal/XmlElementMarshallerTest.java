package com.gdcom.marshal;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class XmlElementMarshallerTest {

	XmlMarshaller xmlMarshaller = new XmlElementMarshaller();
	String name;
	String value;

	@Test(expected = MarshalException.class)
	public void testMarshalExceptionOnPassingInvalidObject() throws Exception{
		Object object = new Object();
		xmlMarshaller.marshal(object);
	}

	@Test
	public void marshalXmlElementSansAttributes() throws Exception {
		name = "name";
		value = "prem kumar";
		XmlElement xmlElement = new XmlElement(name, value);
		String expectedXmlString = "<" + name + ">" + value + "</" + name + ">";
		String actualXmlString = xmlMarshaller.marshal(xmlElement);

		assertEquals("the encoded xml is not proper", expectedXmlString, actualXmlString);
	}

	@Test
	public void marshalXmlElementWithNullValue() throws Exception {
		name = "name";
		value = null;

		XmlElement xmlElement = new XmlElement(name, value);
		String expectedXmlString = "<" + name + "></" + name + ">";
		String actualXmlString = xmlMarshaller.marshal(xmlElement);

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

		String expectedXmlString = "<" + name + " " + attName + "=\"" + attValue + "\">" + value + "</" + name + ">";
		String actualXmlString = xmlMarshaller.marshal(xmlElement);

		assertEquals("the encoded xml is not proper", expectedXmlString, actualXmlString);
	}
}
