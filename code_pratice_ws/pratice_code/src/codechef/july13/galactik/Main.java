package codechef.july13.galactik;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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

// below is the logic.
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

		tax = new int[noOfPlanets+1];
		for (int i = 1; i <noOfPlanets+1; i++) {
			tax[i] = in.nextInt();
		}

		long minTax = getMinAmount();
		out.println(minTax);
	}

	private Graph graph;
	private boolean []isVisited;

	private long getMinAmount() {
		formGraph();
		isVisited = new boolean[noOfPlanets+1];

		boolean notPossible = false;
		List<Integer> sccMinVals= new ArrayList<Integer>();

		for (int node=1;node<noOfPlanets+1;node++) {
			if (!isVisited[node]) {
				int sccTax = traverseGraphAndGetMin(node);
				if (sccTax < 0) {
					notPossible = true;
					if (sccMinVals.size() > 1) // TODO find why > 0 is giving wrong answer
						break;
				}
				sccMinVals.add(sccTax);
			}
		}

		if (sccMinVals.size()==1)
			return 0; // everyone is connected. no need to pay.

		if (notPossible) {
			return -1;
		} else {
			return getTaxToPay(sccMinVals);
		}
	}

	private long getTaxToPay(List<Integer> taxVals) {
		int size = taxVals.size();
		int min = Integer.MAX_VALUE;
		long total = 0;
		for (Integer tax : taxVals) {
			total += tax;
			min = Math.min(tax, min);
		}

		long tax = total + (size-2)*min; // all other SCC connect to the SCC with min value.
		return tax;
	}

	private int traverseGraphAndGetMin(Integer node) {
		int min = -1;
		isVisited[node] = true;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(node);

		while (!stack.isEmpty()) {
			Integer fromNode = stack.pop();
			min = getMinTax(tax[fromNode], min);
			List<Integer> neighbours = graph.adjacencyList[fromNode];
			if (neighbours!= null) {
				for (Integer neighbour : neighbours) {
					if (!isVisited[neighbour]) {
						isVisited[neighbour] = true;
						stack.push(neighbour);
					}
				}

			}
		}

		return min;
	}

	private int getMinTax(int a, int b) {
		int min = Math.min(a,b);
		int max = Math.max(a,b);

		if (min >= 0) // if both are positive
			return min;
		else if (max >= 0) // if one is negative
			return max;
		else // if both are negative,
			return -1;
	}

	private void formGraph() {
		List<Integer>[] adjacencyList = new ArrayList[noOfPlanets+1];
		graph = new Graph(adjacencyList);

		for (int i = 0; i < pairs; i++) {
			Integer fromNode = neighbours[i][0];
			Integer toNode = neighbours[i][1];
			graph.addNeighbour(fromNode, toNode);
			graph.addNeighbour(toNode, fromNode);
		}
	}

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
