package codechef.february14.colarr;

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

class TaskA {

	private int N;
	private int M;
	private int[] colors;

	// [cell_id][color_id]
	private int[][] costOfColors;
	private int[][] gainOfColors;

	private int repaints;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			M = in.nextInt();
			repaints = in.nextInt();

			// init colors
			colors = new int[N];
			for (int j = 0; j < N; j++) {
				colors[j] = in.nextInt();
				colors[j]--; // since input is 1 based.
			}

			// read gain
			gainOfColors = new int[N][M];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					gainOfColors[j][k] = in.nextInt();
				}
			}

			// read costs
			costOfColors = new int[N][M];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					costOfColors[j][k] = in.nextInt();
				}
			}

			int optimalGain = getOptimalPaintPossible();
			out.println(optimalGain);
		}

	}

	private int getOptimalPaintPossible() {
		// create array having points if the x is the color in the cell i.
		int[][] netGain = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cost = costOfColors[i][j];
				int gain = gainOfColors[i][j];

				netGain[i][j] = gain - cost;
			}
		}

		int[] existingPoints = new int[N];
		for (int i = 0; i < N; i++) {
			existingPoints[i] = gainOfColors[i][colors[i]];
		}

		// get the max points possible after repaint for each of the cell.
		int[] maxGainPoints = new int[N];
		for (int i = 0; i < N; i++) {
			int maxGain = Integer.MIN_VALUE;
			for (int j = 0; j < M; j++) {
				maxGain = Math.max(maxGain, netGain[i][j]);
			}

			maxGainPoints[i] = maxGain;
		}

		// greedy approach...choose first the cells with higher difference in max points and existing points.
		PointsGain[] pointsGains = new PointsGain[N];
		for (int i = 0; i < N; i++) {
			pointsGains[i] = new PointsGain(existingPoints[i], maxGainPoints[i], i);
		}

		Arrays.sort(pointsGains);

		int totalPoints = 0;

		int i;
		// read the K guys first.
		for (i = N - 1; i >= 0 && repaints > 0; i--, repaints--) {
			totalPoints += pointsGains[i].maxPoint;
		}

		// read the remaining which are not painted.
		for (; i >= 0; i--) {
			totalPoints += pointsGains[i].existPoint;
		}

		return totalPoints;
	}

	private class PointsGain implements Comparable<PointsGain>{
		int existPoint;
		int maxPoint;
		int index;

		public PointsGain(int existPoint, int maxPoint, int index) {
			this.existPoint = existPoint;
			this.maxPoint = maxPoint;
			this.index = index;
		}

		@Override
		public int compareTo(PointsGain o) {
			int thisDiff = maxPoint - existPoint;
			int otherDiff = o.maxPoint - o.existPoint;

			if (thisDiff < otherDiff) return -1;
			if (thisDiff > otherDiff) return 1;

			if (maxPoint < o.maxPoint) return -1;
			if (maxPoint > o.maxPoint) return 1;
			return 0;
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
