package codechef.september13.leexams;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
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

		findProbability(0, "", 1.00);
	}

	private void findProbability(int level, String str, double prob) {
		if (level==n) {
//			System.out.println("str --> " + str + " has prob " + prob);
			if (checkifStrHasUniqueNos(str)) {
				totalProbability += prob;
			}
			return;
		}

		// choose A
		findProbability(level+1, str + A[level], prob*P[level]);
		// choose B
		findProbability(level+1, str + B[level], prob*(1.0-P[level]));
	}

	private boolean checkifStrHasUniqueNos(String str) {
		char [] chars = str.toCharArray();
		Set<Character> charSet = new HashSet<>();

		for (char ch : chars) {
			charSet.add(ch);
		}

		if (chars.length==charSet.size())
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
