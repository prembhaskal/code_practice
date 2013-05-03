package practice.data_structures;

import java.util.Arrays;

/**
 * User: premkumar.bhaskal
 * Date: 5/4/13
 * Time: 1:58 AM
 *
 * for more info
 * see http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=lowestCommonAncestor
 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 *
 */
public class SegmentTree {

	int[] M;
	int[] array;
	int N;

	public SegmentTree(int[] array) {
		this.array = array;
		M = new int[array.length*2];
		Arrays.fill(M, -1);
	}

	private void initialize(int node, int low, int high) {
		if (low==high) {
			M[node] = low;
		} else {
			initialize(2*node+1, low, (low+high)/2);
			initialize(2*node+2, (low+high)/2 + 1, high);

			if (array[M[2*node+1]] <= array[M[2*node+2]]) {
				M[node] = M[2*node+1];
			} else {
				M[node] = M[2*node+2];
			}
		}
	}


	public int query()
}
