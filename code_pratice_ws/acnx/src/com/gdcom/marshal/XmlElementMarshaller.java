package com.gdcom.marshal;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.util.List;

public class XmlElementMarshaller {

	public String createTag(Object xmlElement) throws MarshalException{
		if (! (xmlElement instanceof XmlElement))
			throw new MarshalException("unknown object passed for marshaling");

		XmlElement element = (XmlElement) xmlElement;

		StringBuilder xmlString = new StringBuilder();
		xmlString.append(createStartingTag(element));
		xmlString.append(element.getValue());
		xmlString.append(createEndingTag(element));

		return xmlString.toString();
	}

	public String createStartingTag(XmlElement xmlElement) {
		if (xmlElement.getAttributes().isEmpty())
			return "<" + xmlElement.getName() + ">";
		else
			return createStartingTagWithAttributes(xmlElement.getName(), xmlElement.getAttributes());
	}

	private String createStartingTagWithAttributes(String tagName, List<XmlAttribute> attributes) {
		StringBuilder tag = new StringBuilder();
		tag.append("<" + tagName);

		for (XmlAttribute attribute : attributes) {
			tag.append(" " + marshalAttribute(attribute));
		}

		tag.append(">");

		return tag.toString();
	}

	private String marshalAttribute(XmlAttribute xmlAttribute) {
		return xmlAttribute.getName() + "=\"" + xmlAttribute.getValue() + "\"";
	}

	public String createEndingTag(XmlElement element) {
		return "</" + element.getName() + ">";
	}
}
