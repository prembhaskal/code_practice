package practice.algorithm;

import java.util.Arrays;

//http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=lowestCommonAncestor#Segment_Trees
//http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
public class SegmentTree {

	int[] M;

	int[] array;

	public SegmentTree(int[] array) {
		this.array = array;
		initTree();
	}

	private void initTree() {
		int size = (int) (Math.log10(array.length)/ Math.log10(2));
		size = (int) Math.pow(2,size+1);
		size = size + size;

		M = new int[size];
		Arrays.fill(M, -1);

		initialize(0,0,array.length-1);
	}

	private void initialize(int node, int low, int high) {
		if (low == high) {
			M[node] = low;
		} else {
			int mid = (low + high)/2;
			initialize(2*node + 1, low, mid);
			initialize(2*node + 2, mid+1, high);

			if (array[M[2*node+1]] <= array[M[2*node+2]]) {
				M[node] = M[2*node+1];
			} else {
				M[node] = M[2*node+2];
			}
		}
	}


	public int query(int low, int high) {
		return query(0, 0, array.length-1, low, high);
	}

	// query to find minimum in range[i,j]
	private int query(int node, int low, int high, int i, int j) {
		if (i > high || j < low)
			return -1;

		if (i<=low && j >= high)
			return M[node];

		int mid = (low + high)/2;
		int p1 = query(2*node+1, low, mid, i, j);
		int p2 = query(2*node+2, mid + 1, high, i, j);

		if (p1==-1)
			return p2;
		if (p2==-1)
			return p1;
		if (array[p1]<=array[p2])
			return p1;

		return p2;
	}
}
