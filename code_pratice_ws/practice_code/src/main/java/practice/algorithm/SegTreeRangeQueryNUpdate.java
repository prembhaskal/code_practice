package practice.algorithm;

public class SegTreeRangeQueryNUpdate {

	private int[] nums;
	private int size;
	private Node rootNode;

	public SegTreeRangeQueryNUpdate(int[] nums) {
		this.size = nums.length;
		this.nums = nums;
		init();
	}

	private void init() {
		rootNode = new Node(0, size - 1);
		addNode(rootNode);
	}

	private long addNode(Node parentNode) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low == high) { // stop at leaf.
			parentNode.sum = nums[low];
			return nums[low];
		}

		int mid = (low + high)/2;
		parentNode.leftNode = new Node(low, mid);
		parentNode.rightNode = new Node(mid + 1, high);

		parentNode.sum = addNode(parentNode.leftNode) + addNode(parentNode.rightNode);
		return parentNode.sum;
	}

	public void rangeUpdate(int low, int high, int value) {
		rangeUpdate(rootNode, low, high, value);
	}

	private void rangeUpdate(Node parentNode, int x, int y, int val) {
		int low = parentNode.low;
		int high = parentNode.high;

		// return if no overlapping.
		if (low > y || high < x) {
			return;
		}

		// check if node is part of the range.
		if (low >= x && high <= y) {
			parentNode.updateNode(val);
			return;
		}

		parentNode.splitNode();

		rangeUpdate(parentNode.leftNode, x, y, val);
		rangeUpdate(parentNode.rightNode, x, y, val);

		parentNode.mergeNode();
	}

	public long rangeQuery(int low, int high) {
		return rangeQuery(rootNode, low, high);
	}

	private long rangeQuery(Node parentNode, int x, int y) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low > y || high < x) // return if not overlapping.
			return 0;

		if (low >= x && high <= y) {
			return parentNode.sum;
		}

		parentNode.splitNode();

		long sum1 = rangeQuery(parentNode.leftNode, x, y);
		long sum2 = rangeQuery(parentNode.rightNode, x, y);

		return sum1 + sum2;
	}

	private class Node {
		long sum;// current sum which includes the pendingAdd.
		long pendingAdd; // pendingAdd, pending for the children.
		int low;
		int high;

		int numValues; // no. of nodes beneath this node.

		Node leftNode;
		Node rightNode;

		public Node(int low, int high) {
			this.low = low;
			this.high = high;
			numValues = high - low + 1;
		}

		public void updateNode(long val) {
			sum += (numValues * val);
			pendingAdd += val;
		}

		// push all the pending changes while recursing down. also update the sum for the descendants.
		public void splitNode() {
			leftNode.updateNode(pendingAdd);
			rightNode.updateNode(pendingAdd);
			pendingAdd = 0;
		}

		// update the parent node when returning from the recursion.
		public void mergeNode() {
			sum = leftNode.sum + rightNode.sum;
		}
	}
}
