package codechef.february14.drghts;

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

// NOTE input graph is a forest so no cycle would be present.
class TaskA {

	private int N;
	private int M;

	private boolean[] openArray;
	private Graph graph;
	private List<Integer>[] adjacencyList;

	private boolean[] isVisited;
	private boolean[] hasDraught;

	private int openNodesInSCC;

	private int totalPassingDraughts;
	private long totalPairs;

	// anyFurtherOpen[node] --> status of nodes below this node.
	private boolean[] anyFurtherOpen;
	// prevOpen[node] --> status of node previous to this. that is status of its parent.
	private boolean[] prevOpen;
	private int[] nodeParents;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();
		M = in.nextInt();

		openArray = new boolean[N + 1];

		for (int i = 1; i < openArray.length; i++) {
			int op = in.nextInt();
			if (op == 1)
				openArray[i] = true;
			else
				openArray[i] = false;
		}

		// form the graph
		createGraph(in);

		findSolution();

		out.print(totalPairs);
		out.print(" ");
		out.println(totalPassingDraughts);
	}

	private void findSolution() {
		// init stuff;
		totalPairs = 0;
		totalPassingDraughts = 0;

		isVisited = new boolean[N + 1];
		hasDraught = new boolean[N + 1];

		anyFurtherOpen = new boolean[N + 1];
		prevOpen = new boolean[N + 1];
		nodeParents = new int[N + 1];

		// go over all the graph...finding all the SCCs
		for (int node = 1; node < N + 1; node++) {
			// start only with a open node... its ok if we miss a SCC with just closed nodes.
			if (!isVisited[node] && openArray[node]) {
				openNodesInSCC = 0; // init the count for this SCC
//				dfs(node, false);
				dfsWithStack(node);

				// pairs are just nC2;
				long pairsInSCC = (long)openNodesInSCC * (openNodesInSCC - 1);
				pairsInSCC = pairsInSCC/2;
				totalPairs += pairsInSCC;
			}
		}

		// count the draughts
		for (int i = 1; i < N + 1; i++) {
			if (hasDraught[i])
				totalPassingDraughts++;
		}

	}

	// FIXED
	// recursive version of what happens in the method with the stack.
	/*
	Scenarios:
	O - B - B - O --> all 4 have draught passing. (condition 1)
	O - B - O   --> all 3 have draught passing. (condition 2)
	O - O   --> both will have draught passing. (condition 3)
	 */
	private boolean dfs(int node, boolean prevOpen) {
		boolean anyFurtherOpen = false;
		isVisited[node] = true;

		if (openArray[node])
			openNodesInSCC++;

		List<Integer> neighbours = graph.adjacencyList[node];
		if (neighbours != null) {
			for (int neighbour : neighbours) {
				if (!isVisited[neighbour]) {
					boolean nextOpen = prevOpen || openArray[node]; // pass the condition to next nodes.
					if (dfs(neighbour, nextOpen)) {
						anyFurtherOpen = true;
					}
				}
			}
		}

		// mark the draught for this node
		hasDraught[node] = prevOpen && anyFurtherOpen // condition 1
						|| openArray[node] && anyFurtherOpen // condition 2
						|| prevOpen && openArray[node]; // condition 3

		// return if this node is open or any thing further down is open.
		return openArray[node] || anyFurtherOpen;
	}

	// replicate the behaviour of recursive calls using stack.
	// THIS WORKS...
	private void dfsWithStack(int startNode) {
		Stack<Node> stack = new Stack<>();
		isVisited[startNode] = true;

		stack.push(new Node(startNode, false));

		while (!stack.isEmpty()) {
			Node nodeClass = stack.pop();
			int node = nodeClass.num;
			int parentNode = nodeParents[node];

			if (nodeClass.marked) { // means all the child nodes are processed.
				// mark this node
				hasDraught[node] = prevOpen[node] && anyFurtherOpen[node]
								|| openArray[node] && anyFurtherOpen[node]
								|| prevOpen[node] && openArray[node];
				// set the parent value
				boolean furtherOpen = openArray[node] || anyFurtherOpen[node];
				if (furtherOpen) // set parent to true, if any one child is good.
					anyFurtherOpen[parentNode] = furtherOpen;
			}
			else {
				if (openArray[node])
					openNodesInSCC++;

				List<Integer> neighbours = graph.adjacencyList[node];
				if (neighbours != null) {

					// mark parent and store it back
					nodeClass.marked = true;
					stack.push(nodeClass);

					// iterate over the neighbouring nodes.
					for (int neighbour : neighbours) {
						if (!isVisited[neighbour]) {
							isVisited[neighbour] = true;
							nodeParents[neighbour] = node;

					// update for the next nodes which are further down. based on previous node and present node
							boolean nextOpen = prevOpen[node] || openArray[node];
							prevOpen[neighbour] = nextOpen;

							Node neighbourNode = new Node(neighbour, false);
							stack.push(neighbourNode);
						}
					}
				}
			}

		}
	}

	private void createGraph(InputReader in) {
		adjacencyList = new ArrayList[N + 1];
		graph = new Graph(adjacencyList);

		for (int i = 0; i < M; i++) {
			int nodeA = in.nextInt();
			int nodeB = in.nextInt();
			graph.addNeighbour(nodeA, nodeB);
			graph.addNeighbour(nodeB, nodeA);
		}
	}

	private class Node {
		int num;
		boolean marked;

		public Node(int num, boolean marked) {
			this.num = num;
			this.marked = marked;
		}
	}

	private class Graph {
		List<Integer>[] adjacencyList;

		public Graph(List<Integer>[] adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		public void addNeighbour(int node, int neighbour) {
			List<Integer> neighbours = adjacencyList[node];
			if (neighbours == null) {
				neighbours = new ArrayList<>();
				adjacencyList[node] = neighbours;
			}

			neighbours.add(neighbour);
		}
	}

}
// fast reader nicked from egorK
class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			if (Character.isValidCodePoint(c))
				res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhitespace(c);
	}

	public static boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}

