package practice.algorithm;

import org.omg.CORBA._IDLTypeStub;

public class SegmentAddMinusTree {
	int size;
	int[] nums;
	Node rootNode;

	public SegmentAddMinusTree(int[] nums) {
		this.nums = nums;
		this.size = nums.length;
		initTree();
	}

	private void initTree() {
		rootNode = new Node(0, size - 1);
		addNodes(rootNode);
	}

	// create nodes both the left and right...also return the sum from the child nodes for initialization.
	private long addNodes(Node parentNode) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low == high)  {  // return when we hit a leaf. before storing the actual marbles in it.
			parentNode.marblesOnIt = nums[low];
			return nums[low];
		}

		int mid = (low + high)/2;
		// attach the left node and get the marbles from it.
		Node leftNode = new Node(low, mid);
		long marblesOnLeft = addNodes(leftNode);
		parentNode.leftNode = leftNode;
		parentNode.marblesOnLeft = marblesOnLeft;

		// attach the right node and get the marbles from it.
		Node rightNode = new Node(mid + 1, high);
		long marblesOnRight = addNodes(rightNode);
		parentNode.rightNode = rightNode;
		parentNode.marblesOnRight = marblesOnRight;

		return marblesOnLeft + marblesOnRight;
	}

	public long getSumInRange(int low, int high) {
		return getSumInRange(rootNode, low, high);
	}

	private long getSumInRange(Node parentNode, int i, int j) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low > j || high < i) // no overlapping in the ranges.
			return 0;

		if (low == high) { // return if we hit a leaf.
			return parentNode.marblesOnIt;
		}

		if (low >= i && high <= j) // return if range completely overlaps. (this is what makes the tree efficient.
			return parentNode.marblesOnLeft + parentNode.marblesOnRight;

		// else search below the tree.
		long marblesLeft = getSumInRange(parentNode.leftNode, i, j);
		long marblesRight = getSumInRange(parentNode.rightNode, i, j);

		return marblesLeft + marblesRight;
	}

	public void addToIndex(int value, int index) {
		addToIndex(rootNode, value, index);
	}

	public void removeFromIndex(int value, int index) {
		addToIndex(-value, index);
	}

	private void addToIndex(Node parentNode, int value, int idx) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low == high && low == idx) { // add if we hit the leaf.
			parentNode.marblesOnIt += value;
			return;
		}

		if (low > idx || high < idx) // no overlapping here.
			return;

		// check if we can add to the left node of it.
		Node leftNode = parentNode.leftNode;
		Node rightNode = parentNode.rightNode;
		if (leftNode.low <= idx && leftNode.high >= idx) {
			addToIndex(leftNode, value, idx);
			parentNode.marblesOnLeft += value;
		}
		else {
			addToIndex(rightNode, value, idx);
			parentNode.marblesOnRight += value;
		}

	}


	private class Node {
		int low;
		int high;
		long marblesOnLeft;
		long marblesOnRight;
		Node leftNode;
		Node rightNode;
		long marblesOnIt;

		public Node(int low, int high) {
			this.low = low;
			this.high = high;
		}
	}

}
