package com.gdcom.marshal;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import java.io.OutputStream;
import java.util.List;

public class XmlElementMarshaller implements XmlMarshaller {

	@Override
	public void marshal(Object xmlElement, OutputStream os) {
	}

	@Override
	public String marshal(Object xmlElement) throws MarshalException{
		if (! (xmlElement instanceof XmlElement))
			throw new MarshalException("unknown object passed for marshaling");

		XmlElement element = (XmlElement) xmlElement;
		List<XmlAttribute> attributes = element.getAttributes();

		StringBuilder xmlString = new StringBuilder();

		if (attributes.isEmpty()) {
			xmlString.append(createEmptyStartingTag(element.getName()));
			xmlString.append(element.getValue());
			xmlString.append(createEndingTag(element.getName()));
		} else {
			xmlString.append(createTagWithAttributes(element.getName(), element.getAttributes()));
			xmlString.append(element.getValue());
			xmlString.append(createEndingTag(element.getName()));
		}

		return xmlString.toString();
	}

	private String createEmptyStartingTag(String tagName) {
		return "<" + tagName + ">";
	}

	private String createTagWithAttributes(String tagName, List<XmlAttribute> attributes) {
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

	private String createEndingTag(String tagName) {
		return "</" + tagName + ">";
	}
}
