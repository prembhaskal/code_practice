package practice.algorithm;

public class BITRangeUpdatePointQuery {

	private int[] fenTree;

	/*
	imagine that the Fenwick tree is storing the differences between two nos.
	so  fenTree[1] = fenTree[1] - 0;
	    fenTree[2] = fenTree[2] - fenTree[1]
	    and so on.

	update(a,b) --> so basically we need to update the fenwick tree for only 2 indexes.
	                update(a, +val) and update(b+1, -val)

	query(a) --> since we store the differences, we need to add from a to 1.
	             a = (a-a-1) + (a-1 - a-2) + (a-2 - a-3) ...

	*/
	public BITRangeUpdatePointQuery(int[] nums) {
		fenTree = new int[nums.length + 1];

		// initialize the tree.
		for (int i = 0; i < nums.length; i++) {
			rangeUpdate(i, i, nums[i]);
		}
	}

	public void rangeUpdate(int low, int high, int val) {
		low++;
		high++;

		updateRange(low, val);
		updateRange(high + 1, -val);
	}

	// this method will update from fromIdx to N.
	private void updateRange(int fromIdx, int val) {
		while (fromIdx < fenTree.length) {
			fenTree[fromIdx] += val;
			fromIdx += (fromIdx & -fromIdx);
		}
	}

	public int pointQuery(int idx) {
		idx++;
		return query(idx);
	}

	// this will add the difference from idx to 1.
	private int query(int idx) {
		int sum = 0;
		while (idx > 0) {
			sum += fenTree[idx];
			idx -= (idx & -idx);
		}

		return sum;
	}
}
