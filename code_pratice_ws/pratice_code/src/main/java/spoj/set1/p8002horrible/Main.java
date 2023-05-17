package spoj.set1.p8002horrible;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	SegTreeRangeQueryNUpdate segTree;
	int size;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			size = in.nextInt();
			int queries = in.nextInt();

			init();

			for (int j = 0; j < queries; j++) {
				int cmd = in.nextInt();
				int low = in.nextInt();
				int high = in.nextInt();

				// input is 1 based.
				low--;
				high--;

				if (cmd == 0) {
					int val = in.nextInt();
					segTree.rangeUpdate(low, high, val);
				} else {
					long sum = segTree.rangeQuery(low, high);
					out.println(sum);
				}
			}
		}

	}

	private void init() {
		int[] nums = new int[size];
		segTree = new SegTreeRangeQueryNUpdate(nums);
	}

	private class SegTreeRangeQueryNUpdate {

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


}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
