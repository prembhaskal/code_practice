package practice.algorithm;

/**
 * this is also known as BIT - binary indexed tree.
 */
public class FenwickTree {

	private int[] tree;

	public FenwickTree(int[] nums) {
		tree = new int[nums.length + 1]; // BIT is 1 based tree.

		// initialize the tree. -- this happens in O(nlogn) times.
		for (int i = 1; i < tree.length; i++) {

			int idx = i;
			int val = nums[i-1];
			// build the responsibilty tree
			while ( idx < tree.length) {
				tree[idx] += val;
				idx += (idx & -idx); // (idx & -idx) will give you the number with only the last 1 digit set.
									 // adding it back to number give the next number which depends on us.
			}
		}
	}

	public void updateTree(int idx, int val) {
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

		long sum1 = 0;
		if (low  > 0) {
			sum1 = getSum(low - 1); // get the sum from 1 to low-1
		}

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
