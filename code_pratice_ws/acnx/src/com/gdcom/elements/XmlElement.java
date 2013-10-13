package com.gdcom.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlElement {
	private String name;
	private String value;
	private boolean isIdElement;
	private List<XmlAttribute> attributes;

	public XmlElement(String name, String value) {
		this(name, value, null, false);
	}

	public XmlElement(String name, String value, List<XmlAttribute> attributes, boolean isIdElement) {
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;

		if (value == null)
			value = "";
		this.value = value;

		this.attributes = attributes;
		this.isIdElement = isIdElement;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void nullifyValue() {
		value = null;
	}

	public List<XmlAttribute> getAttributes() {
		if (attributes == null)
			attributes = new ArrayList<>();
		return Collections.unmodifiableList(attributes);
	}

	public void addAttribute(XmlAttribute xmlAttribute) {
		if (xmlAttribute != null) {
			if (attributes == null)
				attributes = new ArrayList<>();
			attributes.add(xmlAttribute);
		}
	}

	public boolean isIdElement() {
		return isIdElement;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		XmlElement that = (XmlElement) o;

		if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (value != null ? !value.equals(that.value) : that.value != null) return false;

		return true;
	}

}
