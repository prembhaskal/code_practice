package com.gdcom.elements;

import java.util.ArrayList;
import java.util.List;

public class XmlElement {
	private String name;
	private String value;
	private List<XmlAttribute> attributes;

	public XmlElement(String name, String value) {
		this(name, value, null);
	}

	public XmlElement(String name, String value, List<XmlAttribute> attributes) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;

		if (value == null)
			value = "";
		this.value = value;

		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public List<XmlAttribute> getAttributes() {
		if (attributes == null)
			attributes = new ArrayList<>();
		return attributes;
	}
}
