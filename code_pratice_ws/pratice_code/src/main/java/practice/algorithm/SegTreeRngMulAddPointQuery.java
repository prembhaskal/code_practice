package practice.algorithm;

public class SegTreeRngMulAddPointQuery {

	private int[] nums;
	private int size;

	private Node rootNode;

	public SegTreeRngMulAddPointQuery(int[] nums) {
		this.nums = nums;
		size = nums.length;
		init();
	}

	private void init() {
		rootNode = new Node(0, size - 1);
		addNode(rootNode);
	}

	private void addNode(Node parentNode) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low == high) { // we reached leaf.
			parentNode.value = nums[low];
			return;
		}

		int mid = (low + high)/2;
		parentNode.leftNode = new Node(low, mid);
		parentNode.rightNode = new Node(mid + 1, high);

		addNode(parentNode.leftNode);
		addNode(parentNode.rightNode);
	}


	// range multiply
	public void rangeMultiply(int M, int low, int high) {
		rngMultiplyAdd(rootNode, M, 0, low, high);
	}

	// range add
	public void rangeAdd(int A, int low, int high) {
		rngMultiplyAdd(rootNode, 1, A, low, high);
	}

	private void rngMultiplyAdd(Node parentNode, int M, int A, int i, int j) {
		int low = parentNode.low;
		int high = parentNode.high;

		if (low > j || high < i) // non overlapping.
			return;

		if (low >= i && high <= j) {
			parentNode.updateNode(M, A);
			return;
		}

		parentNode.splitNode();

		rngMultiplyAdd(parentNode.leftNode, M, A, i, j);
		rngMultiplyAdd(parentNode.rightNode, M, A, i, j);
	}

	public int pointQuery(int point) {
		return pointQuery(rootNode, point);
	}

	private int pointQuery(Node parentNode, int point) {
		if (parentNode.low == parentNode.high && parentNode.low == point)
			return parentNode.getValue();

		parentNode.splitNode();

		Node leftNode = parentNode.leftNode;
		Node rightNode = parentNode.rightNode;

		if (leftNode.low <= point && leftNode.high >= point) {
			return pointQuery(leftNode, point);
		} else {
			return pointQuery(rightNode, point);
		}
	}

	private class Node {
		int low;
		int high;

		int value; // this is only for leaf.

		Node leftNode;
		Node rightNode;

		int mul = 1;
		int add = 0;

		public Node(int low, int high) {
			this.low = low;
			this.high = high;
		}

		// push changes in a recursive nature
		// mul[i] = mul[i] * mul[i-1]
		// add[i] = add[i] * mul[i-1] + add[i-1]
		public void updateNode(int M, int A) {
			mul = mul * M;
			add = add*M + A;
		}

		public int getValue() {
			return value * mul + add;
		}

		public void splitNode() {
			leftNode.updateNode(mul, add);
			rightNode.updateNode(mul, add);
			mul = 1;
			add = 0;
		}


	}
}
