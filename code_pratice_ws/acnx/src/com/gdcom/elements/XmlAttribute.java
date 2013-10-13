package com.gdcom.elements;

public class XmlAttribute {

	private String name;
	private String value;

	public XmlAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		XmlAttribute attribute = (XmlAttribute) o;

		if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
		if (value != null ? !value.equals(attribute.value) : attribute.value != null) return false;

		return true;
	}
}
