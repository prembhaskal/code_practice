package codechef.october13.helping_lira;

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

	private int points;
	private int[] x1;
	private int[] x2;
	private int[] x3;

	private int[] y1;
	private int[] y2;
	private int[] y3;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		points = in.nextInt();

		x1 = new int[points];
		x2 = new int[points];
		x3 = new int[points];

		y1 = new int[points];
		y2 = new int[points];
		y3 = new int[points];

		for (int i = 0; i < points; i++) {
			x1[i] = in.nextInt();
			y1[i] = in.nextInt();

			x2[i] = in.nextInt();
			y2[i] = in.nextInt();

			x3[i] = in.nextInt();
			y3[i] = in.nextInt();
		}

		int[] smallestLargest = getSmallestLargest();

		out.print(smallestLargest[0] + " ");
		out.println(smallestLargest[1]);
	}

	private int[] area;

	/*
	area of triangle mod(Ax(By - Cy) + Bx(Cy-Ay) + Cx(Ay - By))/2;
	 */
	private int[] getSmallestLargest() {
		area = new int[points];

		for (int i = 0; i < points; i++) {
			area[i] = x1[i] * ( y2[i] - y3[i]) + x2[i] * (y3[i] - y1[i]) + x3[i] * ( y1[i] - y2[i]);
			area[i] = Math.abs(area[i]);
		}

		// get the smallest and largest index
		int smallestArea = Integer.MAX_VALUE;
		int smallestIndex = 0;

		int largestArea = -1;
		int largestIndex = 0;

		for (int i = 0; i < points; i++) {
			if (area[i] <= smallestArea) {
				smallestIndex = i+1;
				smallestArea = area[i];
			}

			if (area[i] >= largestArea) {
				largestIndex = i+1;
				largestArea = area[i];
			}
		}

		int[] smallestLargest = new int[2];
		smallestLargest[0] = smallestIndex;
		smallestLargest[1] = largestIndex;

		return smallestLargest;
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
