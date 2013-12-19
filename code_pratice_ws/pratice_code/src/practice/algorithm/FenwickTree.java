package practice.algorithm;

/**
 * this is also known as BIT - binary indexed tree.
 */
public class FenwickTree {

	private long[] tree;

	public FenwickTree(int[] nums) {
		tree = new long[nums.length + 1]; // BIT is 1 based tree.

		// initialize the tree. -- this happens in O(nlogn) times.
		for (int i = 0; i < nums.length; i++) {
			long val = nums[i];

			// build the responsibilty tree
			updateTree(i, val);
		}
	}

	public void updateTree(int idx, long val) {
		idx ++;

		// update this node and all the other nodes which depend on this.
		while (idx < tree.length) {
			tree[idx] += val;
			idx += (idx & -idx); // move up the tree.
		}
	}

	public long getSum(int low, int high) {
		low++;
		high++; // convert to 1 based index.

		long sum1 = getSum(low - 1);
		long sum2 = getSum(high);

		return sum2 - sum1;
	}

	// returns the sum from 1 to range. ... this needs the 1 based range.
	private long getSum(int range) {
		long sum = 0;
		int idx = range;
		// sum this nodes plus the nodes on which we depend upon.
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx); // move down the tree.
		}

		return sum;
	}
}
