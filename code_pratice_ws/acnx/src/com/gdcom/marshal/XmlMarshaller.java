package com.gdcom.marshal;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface XmlMarshaller {

	void marshal(Object xmlElement, PrintWriter out) throws MarshalException;

	String marshal(Object xmlElement) throws MarshalException;
}
