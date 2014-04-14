package codechef.april14.bintree;

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

class TaskA {

	private int[][] queries;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int N = in.nextInt();

		queries = new int[N][2];

		for (int i = 0; i < N; i++) {
			queries[i][0] = in.nextInt();
			queries[i][1] = in.nextInt();
		}

		for (int i = 0; i < N; i++) {
			int edges = getShortestPath(queries[i][0], queries[i][1]);
			out.println(edges);
		}

	}

	private int getShortestPath(int a, int b) {
		if (a == b)
			return 0;

		int smaller = a >= b ? b : a;
		int bigger = a > b ? a : b;

		Map<Integer, Integer> visitedNodeMap = new HashMap<>();
		int edges = 0;
		while (smaller >= 1) {
			visitedNodeMap.put(smaller, edges);
			edges++;
			smaller /= 2;
		}

		// now check the nodes visited by bigger number and check where we match.
		edges = 0;
		while (true) {
			if (visitedNodeMap.containsKey(bigger)) {
				edges = edges + visitedNodeMap.get(bigger);
				break;
			}

			edges++;
			bigger /= 2;
		}

		return edges;
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
