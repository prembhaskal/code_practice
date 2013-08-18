package practice.algorithm;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * segment tree.
 * following operations are supported for an array.
 * deleting an element at any index in O(log(n)) time.
 * getting new index for an old index in O(log(n)).. NOTE a normal array would take O(n) for this operation.
 * in normal array, after deletion we would need to shift the whole array left...so O(n)
 *
 * In short this tree supports multiple deletes and querying in O(log(n)) time.
 *
 * since we are only interested in the indices, this tree will be based on only the size of the array irrespective
 * of the actual elements in the array.
 *
 * 0 based index.
 */
public class SegmentDeleteTree {

	int size;
	Node rootNode;

	public SegmentDeleteTree(int size) {
		this.size = size;
		if (size==0)
			return;
		formTree();
	}

	private void formTree() {
		// form the top-node.
		rootNode = new Node(0, size-1);
		rootNode.elementsToLeft = (rootNode.high - rootNode.low + 1)/2;
		rootNode.totalElementsBelow = (rootNode.high - rootNode.low + 1);

		formSubTree(rootNode);
	}

	private int formSubTree(Node parentNode) {
		if (parentNode.low == parentNode.high) // STOP AT LEAF
			return 1; // count only the leaves.

		int L = parentNode.low;
		int H = parentNode.high;
		int mid = L + (H-L)/2;

		Node leftNode = new Node(L, mid);
		int nodesOnLeft = formSubTree(leftNode);
		Node rightNode = new Node(mid + 1, H);
		int nodesOnRight = formSubTree(rightNode);

		int totalNodes = nodesOnLeft + nodesOnRight;

		parentNode.leftNode = leftNode;
		parentNode.rightNode = rightNode;
		parentNode.elementsToLeft = nodesOnLeft;
		parentNode.totalElementsBelow = totalNodes;

		return totalNodes;
	}

	public void printSegmentTree(PrintStream out) {
		printNode(this.rootNode, out);
	}

	private void printNode(Node node, PrintStream out) {
		if (node==null)
			return;

		// print root node.
		out.println("--> '" + node.low + "' -- '" + node.high + "' "
				+ " onleft-" + node.elementsToLeft + " below-" + node.totalElementsBelow + " <--");
		printNode(node.leftNode, out);
		printNode(node.rightNode, out);
	}
}

class Node {
	int low;
	int high;

	Node leftNode;
	Node rightNode;

	int elementsToLeft;
	int totalElementsBelow;

	public Node(int low, int high) {
		this.low = low;
		this.high = high;
	}
}
