package com.gdcom.parser;

import com.gdcom.marshal.MarshalException;
import com.gdcom.marshal.XmlMarshaller;
import com.gdcom.rawdata.RawDataConverter;
import com.gdcom.tree.FixAttributes;
import com.gdcom.tree.Node;
import com.gdcom.tree.TreeCreator;
import com.gdcom.tree.TreeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

	private RawDataConverter dataConverter;
	private TreeCreator treeCreator;
	private FixAttributes fixAttributes;
	private XmlMarshaller xmlMarshaller;

	public DataProcessor(RawDataConverter dataConverter, TreeCreator treeCreator, FixAttributes fixAttributes,
						 XmlMarshaller xmlMarshaller) {
		this.dataConverter = dataConverter;
		this.treeCreator = treeCreator;
		this.fixAttributes = fixAttributes;
		this.xmlMarshaller = xmlMarshaller;
	}

	public void processData(BufferedReader reader, PrintWriter out) throws IOException, ParserException, TreeException, MarshalException {
		Node node;
		String line;
		List<Node> subTreeNodes = new ArrayList<>();

		line = reader.readLine();
		node = dataConverter.convertToNode(line);

		if (!isStartOfNextSubTree(node))
			throw new ParserException("data is not starting with an id element");

		while ((line = reader.readLine()) != null) {
			subTreeNodes.add(node);

			if (line.isEmpty())
				continue;

			node = dataConverter.convertToNode(line);
			if (isStartOfNextSubTree(node)) { // if next tree starts, process the present tree.
				parseSubTree(subTreeNodes, out);
			}

			subTreeNodes.clear();
		}

		// take care of remaining lines.(if any)
		if (!subTreeNodes.isEmpty())
			parseSubTree(subTreeNodes, out);
	}

	private boolean isStartOfNextSubTree(Node node) {
		return node.getXmlElement().isIdElement();
	}

	private void parseSubTree(List<Node> subTreeNodes, PrintWriter out) throws TreeException, MarshalException {
		Node rootNode = treeCreator.formTreeFromNodes(subTreeNodes);
		fixAttributes.fixAttributeForTree(rootNode);

		for (Node node : subTreeNodes) {
			xmlMarshaller.marshal(node, out);
		}
	}
	
}
