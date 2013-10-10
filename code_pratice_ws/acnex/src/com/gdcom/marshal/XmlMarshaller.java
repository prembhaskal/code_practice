package com.gdcom.marshal;

import java.io.OutputStream;

public interface XmlMarshaller {

	void marshal(Object xmlElement, OutputStream os);

	String marshal(Object xmlElement);
}
