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

	public void deleteIndex(int index) {
		if (index >= size)
			return;

		deleteIndex(rootNode, index);
	}

	private void deleteIndex(Node parentNode, int deleteIdx) {
		if (parentNode.low == parentNode.high && deleteIdx == parentNode.low) {
			parentNode.isDeleted = true;
			return; // we stop here.
		}


		Node leftNode = parentNode.leftNode;
		Node rightNode = parentNode.rightNode;

		if (leftNode.low <= deleteIdx && leftNode.high >= deleteIdx) {
			parentNode.elementsToLeft--;
			parentNode.totalElementsBelow--;
			deleteIndex(leftNode, deleteIdx);
		} else {
			parentNode.totalElementsBelow--;
			deleteIndex(rightNode, deleteIdx);
		}

	}

	/**
	 * result is undefined if called for deleted index.
	 * @param oldIdx
	 * @return
	 */
	public int getActualIndex(int oldIdx) {
		return getActualIndex(rootNode, oldIdx, 0);
	}

	private int getActualIndex(Node parentNode, int oldIdx, int elementsAtLeft) {
		if (parentNode.low == parentNode.high && oldIdx==parentNode.low) {
			return elementsAtLeft;
		}

		Node leftNode = parentNode.leftNode;
		Node rightNode = parentNode.rightNode;

		if (leftNode.low <= oldIdx && leftNode.high >= oldIdx) {
			return getActualIndex(leftNode, oldIdx, elementsAtLeft);
		} else { // add elements at left only when we move to right.
			int elemAtLeft = parentNode.elementsToLeft;
			return getActualIndex(rightNode, oldIdx, elementsAtLeft + elemAtLeft);
		}
	}

	public void printSegmentTree(PrintStream out) {
		printNode(this.rootNode, out);
	}

	private void printNode(Node node, PrintStream out) {
		if (node==null)
			return;

		// print root node.
		out.println("--> '" + node.low + "' -- '" + node.high + "' "
				+ " onleft-" + node.elementsToLeft + " below-" + node.totalElementsBelow
				+ " isDeleted-" + node.isDeleted + " <--");
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

	boolean isDeleted;

	public Node(int low, int high) {
		this.low = low;
		this.high = high;
	}
}
