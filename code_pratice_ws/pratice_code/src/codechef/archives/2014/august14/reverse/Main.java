package codechef.august14.reverse;

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

	private int n; // vertices
	private int m; // edges
	private List<Vertex>[] adjacencyList;
	private int[][] edges;

	private Graph graph;

	private int INFINITY = 1000_000_000;
	private boolean[] visited;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		n = in.nextInt();
		m = in.nextInt();

		edges = new int[m][2];
		for (int i = 0; i < m; i++) {
			edges[i][0] = in.nextInt();
			edges[i][1] = in.nextInt();
		}

		createGraph();

		int min = minReverseUsingBFS();
		out.println(min);
	}

	// converting the problem into shortest path search of a graph with 2 types of edges. 0 length and 1 length.
	// THIS is ACCEPTED ... simplest solution would be to run Dijkstra but it would be nlogn.
	
	private int minReverseUsingBFS() {
		visited = new boolean[n + 1];

		int[] hops = new int[n + 1];
		Arrays.fill(hops, INFINITY);


		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.add(1); // start from 1st node.
		hops[1] = 0;

		while (!queue.isEmpty()) {
			int node = queue.poll();
			int presentHop = hops[node];

			visited[node] = true;

			// get the connected nodes from this node.
			List<Integer> connectedNodes = getNonVisitedConnectedNodes(node);
			// the hop remains same for connected node.
			// add these nodes in queue.
			for (int sameDistNode : connectedNodes) {
				hops[sameDistNode] = presentHop;
//				queue.add(sameDistNode);
				// add same distance element in front of queue.
				queue.push(sameDistNode);
			}

			// now get the unvisited nodes which have a reverse edge from present node.
			List<Integer> reverseNodes = getNonVisitedReverseNodes(node);
			for (int reverseNode : reverseNodes) {
				// hop is 1 extra from present
				hops[reverseNode] = presentHop + 1;
				visited[reverseNode] = true;
				queue.add(reverseNode);

				// get any nodes connected to this reverse edge...check test case 4.
				// basically we want to treat all connected nodes as 1 and mark them with same distance.
				List<Integer> sameDistNodes = getNonVisitedConnectedNodes(reverseNode);
				for (int sameDistNode : sameDistNodes) {
					hops[sameDistNode] = hops[reverseNode];
					queue.add(sameDistNode);
				}
			}
		}

		if (hops[n] == INFINITY)
			return -1;

		return hops[n];
	}

	// node should better be marked visited before calling this method.
	private List<Integer> getNonVisitedConnectedNodes(int node) {
		List<Integer> sameDistNodes = new ArrayList<>();

		Stack<Integer> stack = new Stack<>();
		stack.push(node);

		// find connected nodes by DFS
		while (!stack.isEmpty()) {
			node = stack.pop();

			List<Vertex> list = graph.adjacencyList[node];
			if (list == null) return sameDistNodes;

			for (Vertex vertex : list) {
				if (!vertex.reverse && !visited[vertex.node]) {
					visited[vertex.node] = true;
					sameDistNodes.add(vertex.node);
					stack.push(vertex.node);
				}
			}
		}

		return sameDistNodes;
	}

	private List<Integer> getNonVisitedReverseNodes(int node) {
		List<Integer> reverseNodes = new ArrayList<>();
		List<Vertex> list = graph.adjacencyList[node];
		if (list == null) return reverseNodes;

		for (Vertex vertex : list) {
			if (!visited[vertex.node] && vertex.reverse) {
				reverseNodes.add(vertex.node);
			}
		}

		return reverseNodes;
	}

	private void createGraph() {
		adjacencyList = new ArrayList[n+1]; // starts from 1.
		graph = new Graph(adjacencyList);

		for (int i = 0; i < m; i++) {
			int from = edges[i][0];
			int to = edges[i][1];

			if (from == to) continue;

			graph.addNeighbour(from, to, false);
			// add a reverse node for tracking.
			graph.addNeighbour(to, from, true);
		}
	}
}

class Graph {
	List<Vertex> [] adjacencyList;

	public Graph(List<Vertex>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(int node, int neighbour, boolean reverse) {
		List<Vertex> list = adjacencyList[node];
		if (list == null) {
			list = new ArrayList<>();
			adjacencyList[node] = list;
		}
		list.add(new Vertex(neighbour, reverse));
	}
}

class Vertex {
	int node;
	boolean reverse;

	public Vertex(int node, boolean reverse) {
		this.node = node;
		this.reverse = reverse;
	}

	@Override
	public String toString() {
		return node + " --- " + (reverse? "REVERSE" : "FORWARD");
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
