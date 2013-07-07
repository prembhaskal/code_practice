package codechef.july13.galactik;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

// TODO solve this thing. below is the logic
// find planet with min tax in a SCC(if min < 0, then stop everything, return -1)
// if only one SCC, return 0
// else suppose we have n SCC, then OPTIMISE == all connect to SCC with min tax.
// so add all other (n-1)*(SCCmin) + SCC1 + SCC2 (all other than the min SCC)
class TaskA {

	int noOfPlanets;
	int pairs;
	int neighbours[][];
	int tax[];

	public void solve(InputReader in, PrintWriter out) throws IOException {
		noOfPlanets = in.nextInt();
		pairs = in.nextInt();

		neighbours = new int[pairs][2];
		for (int i = 0; i < pairs; i++) {
			neighbours[i][0] = in.nextInt();
			neighbours[i][1] = in.nextInt();
		}

		tax = new int[noOfPlanets];
		for (int i = 0; i <noOfPlanets; i++) {
			tax[i] = in.nextInt();
		}

	}

	private int getMinAmount() {
		throw new RuntimeException();
	}

	private Graph graph;



}

class Graph {
	List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(Integer fromNode, Integer toNode) {
		List<Integer> neighbours = adjacencyList[fromNode];
		if (neighbours == null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[fromNode] = neighbours;
		}
		neighbours.add(toNode);
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
