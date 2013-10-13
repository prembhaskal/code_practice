package com.gdcom.marshal;

import com.gdcom.tree.Node;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class NodeMarshaller implements XmlMarshaller {

	XmlElementMarshaller xmlElementMarshaller = new XmlElementMarshaller();

	String lineSeparator = "\r\n";

	@Override
	public void marshal(Object xmlElement, PrintWriter out) throws MarshalException {
		String encodedXml = marshal(xmlElement);
		out.print(encodedXml);
	}

	@Override
	public String marshal(Object xmlElement) throws MarshalException {
		if (!(xmlElement instanceof Node))
			throw new MarshalException("unknown object passed for marshaling");

		Node rootNode = (Node) xmlElement;
		if (!isRootNode(rootNode))
			throw new MarshalException("node is not the root node");

		return encodeNode(rootNode);
	}

	private boolean isRootNode(Node node) {
		return node.getParentNode() == null;
	}

	private String encodeNode(Node node) throws MarshalException {

		StringBuilder xml = new StringBuilder();
		List<Node> childNodes = node.getChildNodes();

		if (childNodes.isEmpty()) {
			String tag = xmlElementMarshaller.createTag(node.getXmlElement());
			xml.append(tag);
		}
		else {
			String startTag = xmlElementMarshaller.createStartingTag(node.getXmlElement());
			xml.append(startTag);
			xml.append(lineSeparator);

			for (Node childNode : childNodes) {
				String childXml = encodeNode(childNode);
				xml.append(childXml);
				xml.append(lineSeparator);
			}

			String endTag = xmlElementMarshaller.createEndingTag(node.getXmlElement());
			xml.append(endTag);
		}

		return xml.toString();
	}


}
