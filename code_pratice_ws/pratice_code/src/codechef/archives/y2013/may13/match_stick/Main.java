package codechef.archives.may13.match_stick;

import java.io.*;
import java.util.Arrays;
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

// this solution got accepted
class TaskA {

	int[] burnTimes;
	SegmentTree minTree;
	SegmentMaxTree maxTree;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int N = in.nextInt();

		burnTimes = new int[N];

		for (int i=0;i<N;i++) {
			burnTimes[i] = in.nextInt();
		}

		init();

		int queries = in.nextInt();

		for (int i=0;i<queries;i++) {
			int low = in.nextInt();
			int high = in.nextInt();

			String time = getBurnTime(low, high);
			out.println(time);
		}
	}

	private void init() {
		minTree = new SegmentTree(burnTimes);
		maxTree = new SegmentMaxTree(burnTimes);
	}


	private String getBurnTime(int low, int high) {
		// get max is burning area
		int maxBurn = getMaximum(low, high);
		// min in burning area
		int minBurn = getMinimum(low, high);
		// max in non burn area
		int maxNonBurn = getMaxInNonBurn(low, high);

		double time1 = minBurn + maxNonBurn;
		double time2 = minBurn + ((double)maxBurn-minBurn)/2;

		double time = Math.max(time1, time2);

		String resultTime = convertToDecimal(time);
		return resultTime;
	}

	private String convertToDecimal(double time) {
		int timeInt = (int)time;
		int doubleTime = (int)(time * 2);

		if (doubleTime==timeInt*2) {
			return timeInt + "" + ".0";
		} else {
			return timeInt + "" + ".5";
		}
	}

	private int getMaxInNonBurn(int low, int high) {
		int max = -1;
		if (low > 0) {
//			max = getMaximum(0, low-1);
			int maxIndex = maxTree.query(0,low-1);
			max = burnTimes[maxIndex];
		}

		if (high < burnTimes.length-1) {
			int maxIndex = maxTree.query(high+1, burnTimes.length-1);
			max = Math.max(max, burnTimes[maxIndex]);
//			max = Math.max(max, getMaximum(high+1,burnTimes.length-1));
		}

		return max;
	}

	// inclusive range
	private int getMaximum(int low, int high) {
		if (low == high)
			return burnTimes[low];
//
//		int max = -1;
//		for (int i=low;i<=high;i++) {
//			max = Math.max(burnTimes[i], max);
//		}

		int maxIndex = maxTree.query(low, high);
		int max = burnTimes[maxIndex];

		return max;
	}

	private int getMinimum(int low, int high) {
		if (low==high)
			return burnTimes[low];
//		int min = Integer.MAX_VALUE;
//
//		for (int i=low;i<=high;i++) {
//			min = Math.min(burnTimes[i], min);
//		}

		int minIndex = minTree.query(low, high);
		int min = burnTimes[minIndex];

		return min;
	}

}

class SegmentTree {

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
			return  p2;
		if (p2==-1)
			return p1;
		if (array[p1]<=array[p2])
			return p1;

		return p2;
	}
}

class SegmentMaxTree {

	int[] M;

	int[] array;

	public SegmentMaxTree(int[] array) {
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

			if (array[M[2*node+1]] >= array[M[2*node+2]]) {
				M[node] = M[2*node+1];
			} else {
				M[node] = M[2*node+2];
			}
		}
	}


	public int query(int low, int high) {
		return query(0, 0, array.length-1, low, high);
	}

	// query to find maximum in range[i,j]
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
		if (array[p1]>=array[p2])
			return p1;

		return p2;
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
