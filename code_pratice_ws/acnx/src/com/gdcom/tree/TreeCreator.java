package com.gdcom.tree;

import java.util.List;
import java.util.Stack;

public class TreeCreator {

	// TODO add more error handling like the level are not proper/ especially ordering etc.

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

		Stack<Node> rootStack = new Stack<>();

		Node previousNode = rootNode;
		int previousLevel = 0;

		for (int i = 1; i < nodes.size(); i++) {
			Node presentNode = nodes.get(i);
			int presentLevel = presentNode.getLevel();
			// if level increases push the previous element to root stack
			if (presentLevel > previousLevel) {
				rootStack.push(previousNode);
			}
			// if level decreases, pop the current root.
			else if (presentLevel < previousLevel) {
				rootStack.pop();
			}

			// get the current root.
			Node currentRoot = rootStack.peek();
			// set the root.
			presentNode.setParentNode(currentRoot);
			// add child to the root nodes.
			currentRoot.addChildNode(presentNode);

			previousLevel = presentLevel;
			previousNode = presentNode;
		}

		return rootNode;
	}
}
