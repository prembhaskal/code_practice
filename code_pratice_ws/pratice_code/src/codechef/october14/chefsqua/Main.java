package codechef.october14.chefsqua;

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

// accepted :) :) finally
// TODO a much simpler way using complex plane exists....check editorials.
class TaskA {

	private Set<Point> pointsSet;

	private int N;
	private int[] X;
	private int[] Y;

	private final double PRECISION = 0.001;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();

		X = new int[N];
		Y = new int[N];

		pointsSet = new HashSet<>();

		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();

			X[i] = x; Y[i] = y;

			pointsSet.add(new Point(x, y));
		}

		int pointReqd = getRequiredPoints();
		out.println(pointReqd);
	}

	private int getRequiredPoints() {
		if (N < 3) {
			return getRequiredPointsSmall();
		}

		int minPoints = 2;

		// iterate over each pair and try to find 2 more points which will form the square.
		STOP:
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {

				if (minPoints == 0)
					break STOP;

				int x1 = X[i]; int y1 = Y[i];
				int x2 = X[j]; int y2 = Y[j];

				if (x1 == x2 && y1 == y2)
					continue;

				if (x2 == x1) {
					// infinite slope.
					int dist = Math.abs(y2 - y1);

					// side 1
					int p1 = x1 + dist; int q1 = y1;
					int p2 = x2 + dist; int q2 = y2;

					int reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);

					// side 2
					p1 = x1 - dist; q1 = y1;
					p2 = x2 - dist; q2 = y2;

					reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);
				}
				else if (y2 == y1) {
					// horizontal line
					int dist = Math.abs(x2 - x1);

					// side 1
					int p1 = x1; int q1 = y1 + dist;
					int p2 = x2; int q2 = y2 + dist;

					int reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);

					// side 2
					p1 = x1; q1 = y1 - dist;
					p2 = x2; q2 = y2 - dist;

					reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);
				}
				else {
					// slope
					double m = ((double)y2 - y1)/ ((double)x2 - x1);
					double otherm = -m; // perpendicular to this.

					// below line was CULPRIT... getting out of int max...fixed by casting to long. :)
					double dist = Math.sqrt((((long)y2 - y1)*(y2 - y1) + ((long)x2 - x1) * (x2 - x1)) * 1.0);
					double factor = dist / Math.sqrt((1 + m * m));

					int adj = (int) Math.round(factor);
					int opposite  = (int) (Math.round(factor * otherm));

					// after rounding we don't want to include nos. like 3.4 rounded to 3 and match with something.
					if (Math.abs(adj - factor) > 0.0001) {
						continue;
					}

					if (Math.abs(opposite - factor * otherm) > 0.0001) {
						continue;
					}

					// SIDE 1
					int p1 = x1 + adj; int q1 = y1 + opposite;
					int p2 = x2 + adj; int q2 = y2 + opposite;

					int reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);


					// SIDE 2
					p1 = x1 - adj; q1 = y1 - opposite;
					p2 = x2 - adj; q2 = y2 - opposite;

					reqd = 2;
					if (pointsSet.contains(new Point(p1, q1)))
						reqd--;
					if (pointsSet.contains(new Point(p2, q2)))
						reqd--;

					minPoints = Math.min(minPoints, reqd);
				}
			}
		}

		return minPoints;
	}

	private int getRequiredPointsSmall() {
		if (N == 0)
			return 4;
		if (N == 1)
			return 3;

		int x1 = X[0]; int y1 = Y[0];
		int x2 = X[1]; int y2 = Y[1];

		if (x1 == x2 && y1 == y2)
			return 3;

		else
			return 2;

	}

	private class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Point))
				return false;

			Point other = (Point) obj;
			return x == other.x && y == other.y;
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
