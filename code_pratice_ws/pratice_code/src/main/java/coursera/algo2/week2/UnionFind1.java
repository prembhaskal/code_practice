package coursera.algo2.week2;

public class UnionFind1 {

	private int[] leaders;
	private int count;
	private int[] sz;    // sz[i] = number of objects in subtree rooted at i

	public UnionFind1(int size) {
		leaders = new int[size+1];
		sz = new int[size+1];
		count = size;
		for (int i = 1; i < leaders.length; i++) {
			leaders[i] = i;
			sz[i] = 1;
		}
	}

	// hierarchical leader
	public int find(int node) {
		int cnt=0;
		while (node != leaders[node]) {
			node = leaders[node];
			cnt++;

			if (cnt>1000_000)
				throw new RuntimeException();
		}

		return node;
	}

	public void merge(int p, int q) {
		int i = find(p);
		int j = find(q);
		if (i == j) return;
		if (sz[i] < sz[j]) {
			leaders[i] = j;
			sz[j] += sz[i];
		} else {
			leaders[j] = i;
			sz[i] += sz[j];
		}
		count--;
	}

	public int getCountOfClusters() {
		return count;
	}
}
