package codechef.may14.chefbm;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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

// ACCEPTED..
// TODO change to somehow remove the map here. use set of something.
class TaskA {

	private int n, m, p;
	private int[][] points;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		n = in.nextInt();
		m = in.nextInt();
		p = in.nextInt();

		points = new int[p][2];

		for (int i = 0; i < p; i++) {
			points[i][0] = in.nextInt();
			points[i][0]--; // 1 based index provided
			points[i][1] = in.nextInt();
			points[i][1]--;
		}

		int[] cost = getCost();

		for (int i = 0; i < n; i++) {
			out.println(cost[i]);
		}

	}

	private int[] getCost() {
		Map<Point, Integer> pointMap = new HashMap<>();

		// Map so that less space is used
		for (int i = 0; i < p; i++) {
			Point point = new Point(points[i][0],points[i][1]);

			Integer val = pointMap.get(point);
			if (val == null) {
				val = 0;
			}
			val++;
			pointMap.put(point,val);
		}

		boolean[] notPossible = new boolean[n];

		for (int i = 0; i < p; i++) {
			Point point = new Point(points[i][0], points[i][1]);

			if (point.y == m - 1)
				continue; // if it is last point continue
			Integer presentValue, previousValue;
			// get value
			presentValue = pointMap.get(point);
			presentValue += point.y; // add col value to it.

			Point previousPoint = new Point(point.x, point.y + 1);
			previousValue = pointMap.get(previousPoint);
			if (previousValue == null)
				previousValue = 0;
			previousValue = previousValue + point.y + 1;

			if (presentValue > previousValue)
				notPossible[point.x] = true;
		}

		// get the values
		// cost = last - first;
		int[] cost = new int[n];

		for (int i = 0; i < n; i++) {
			if (notPossible[i])
				cost[i] = -1;
			else {
				Integer firstValue = pointMap.get(new Point(i, 0));
				if (firstValue == null)
					firstValue = 0;

				Integer lastValue = pointMap.get(new Point(i, m - 1));
				if (lastValue == null)
					lastValue = 0;

				lastValue += (m-1);

				cost[i] = lastValue - firstValue;

			}
		}

		return cost;
	}

	private class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;

			Point point = (Point) o;

			if (x != point.x) return false;
			if (y != point.y) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
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
