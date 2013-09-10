package codechef.september13.leexams;

import java.io.*;
import java.util.*;

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

	int n;
	double[] P;
	int[] A;
	int[] B;
	double totalProbability;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			n = in.nextInt();
			P = new double[n];
			A = new int[n];
			B = new int[n];
			totalProbability = 0;

			for (int j = 0; j < n; j++) {
				P[j] = in.nextInt();
				P[j] = P[j] * 0.01;
				A[j] = in.nextInt();
				B[j] = in.nextInt();
			}


			getUniqueProbability();

			out.println(totalProbability);
		}

	}


	private void getUniqueProbability() {

		if (n > 16)
			return;

		findProbability(0, new ArrayList<Integer>(), 1.00);
	}

	// TODO change the brute force to a DP or something faster.
	private void findProbability(int level, List<Integer> list, double prob) {
		if (level==n) {

			if (checkifStrHasUniqueNos(list)) {
				totalProbability += prob;
			}
			return;
		}

		// choose A
		List<Integer> list1 = new ArrayList<>();
		list1.addAll(list);
		list1.add(A[level]);
		findProbability(level+1, list1, prob * P[level]);

		// choose B
		List<Integer> list2 = new ArrayList<>();
		list2.addAll(list);
		list2.add(B[level]);
		findProbability(level + 1, list2, prob * (1.0 - P[level]));
	}

	private boolean checkifStrHasUniqueNos(List<Integer> list) {
		Set<Integer> set = new HashSet<>(list);

		if (set.size()==list.size())
			return true;
		return false;
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
