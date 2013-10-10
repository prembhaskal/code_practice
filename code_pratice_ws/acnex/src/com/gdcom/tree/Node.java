package com.gdcom.tree;

import com.gdcom.elements.XmlElement;
import java.util.List;

/**
 * Node of a tree.
 */
public class Node {

	private XmlElement xmlElement;
	private List<Node> nodes;
	private int level;
	private Node parentNode;
}
