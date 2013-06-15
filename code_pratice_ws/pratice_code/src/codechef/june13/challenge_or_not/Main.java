package codechef.june13.challenge_or_not;

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

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int len = in.nextInt();
		int[] num = new int[len];

		for (int i = 0; i < len; i++) {
			num[i] = in.nextInt();
		}

		printNumbersNotInAPBruteForce(num, out);
	}

	private void printNumbersNotInAPBruteForce(int[] num, PrintWriter out) {
		int range = 4000;

		// if len = 2, print all and return
		if (num.length==2) {
			out.print(num[0]);
			out.print(" ");
			out.print(num[1]);
			return;
		}

		range = Math.min(num.length, range);

		List<Integer> nosSansAP = new ArrayList<Integer>();
		nosSansAP.add(num[0]);
		nosSansAP.add(num[1]);

		for (int k=2;k<range;k++) {
			int len = nosSansAP.size();

			boolean isInAP = false;

			for (int i=0;i<len;i++) {
				for (int j=i+1;j<len;j++) {
					int[] nos = new int[]{nosSansAP.get(i), nosSansAP.get(j), num[k]};
					Arrays.sort(nos);

					if (2*nos[1]==nos[0] + nos[2]) {
						isInAP = true;
						break;
					}
				}

				if (isInAP)
					break;
			}

			// add to list if the number is not AP with anyone
			if (!isInAP) {
				nosSansAP.add(num[k]);
			}
		}

		out.println(nosSansAP.size());

		// print all the numbers
		out.print(nosSansAP.get(0));
		for (int i = 1; i < nosSansAP.size(); i++) {
			out.print(" " + nosSansAP.get(i));
		}
//
//		double score = 100.0 * nosSansAP.size() / (num.length + 0.0);
//
//		out.print(" \n score is " + score);
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
