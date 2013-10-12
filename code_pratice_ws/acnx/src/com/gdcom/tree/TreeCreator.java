package com.gdcom.tree;

import java.util.List;
import java.util.Stack;

public class TreeCreator {

	// TODO add more error handling like the level are not proper. etc.

	/**
	 * Takes the raw nodes (in order) and links to them each other based on the level.
	 * thus forming a tree.
	 * @param nodes
	 * @return RootNode of the tree.
	 */
	public Node formTreeFromNodes(List<Node> nodes) throws TreeException {
		if (nodes.isEmpty())
			throw new TreeException("node list cannot be empty");

		Node rootNode = nodes.get(0);
		if (rootNode.getLevel() != 0)
			throw new TreeException("first node should be the root node");

		Stack<Node> rootNodes = new Stack<>();

		Node previousNode = null;
		int previousLevel = 0;

		for (Node node : nodes) {

		}

		return null;
	}
}
