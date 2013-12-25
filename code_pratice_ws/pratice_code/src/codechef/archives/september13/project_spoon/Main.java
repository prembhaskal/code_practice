package codechef.archives.september13.project_spoon;

import java.io.*;
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

	// THIS IS ACCEPTED.
	public void solve(InputReader in, PrintWriter out) throws IOException {
		fillPascalsTriangle();

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			long spoons = in.nextLong();

			int cities = getNumberOfCities(spoons);

			out.println(cities);
		}

	}

	private int getNumberOfCities(long spoons) {

		for (int i=2;i<numberOfCities.length;i++) {
			if (numberOfCities[i] >= spoons)
				return i;
		}

		throw new RuntimeException("this should not happen");
	}

	int size = 65;
//	long max = 1000_000_000_000_000_000L;
	long [][] pascalsTriangle = new long[size][size];
	long[] numberOfCities = new long[size];

	// we can only select same number of cities...that is from N we can select m cities only....now it depends the value of m.
	// choose such that the N_C_m is maximum for that N.
	// eg for 3 cities, choose 12 13 23... similarly for 6 spoons choose 123 124 and so on... to get maximum permutations.
	private void fillPascalsTriangle() {
		pascalsTriangle[2][0] = 1;
		pascalsTriangle[2][1] = 2;
		pascalsTriangle[2][2] = 1;

		numberOfCities[2] = 2;

		for (int i=2;i<size;i++)
			pascalsTriangle[i][0] = 1;

		long max = 0;

		for (int i=3;i<size;i++) {
			max = -1;
			for (int j=1;j<=i;j++) {
				pascalsTriangle[i][j] = pascalsTriangle[i-1][j-1] + pascalsTriangle[i-1][j];

				max = Math.max(max, pascalsTriangle[i][j]);
			}

			numberOfCities[i] = max;
//			System.out.println(i + "--> " + max);
		}

	}


	private void printPascalsTriangle() {
		for (int i=2;i<9;i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(pascalsTriangle[i][j] + " ");
			}
			System.out.println("");
		}
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
