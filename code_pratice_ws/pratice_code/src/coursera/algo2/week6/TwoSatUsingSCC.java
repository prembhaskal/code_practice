package coursera.algo2.week6;

import common.util.InputReader;

import java.util.*;

public class TwoSatUsingSCC {

	Graph graph;
	Graph reverseGraph;

	int clauses;
	int vertices;

	int finishTime;

	boolean isSAT;

	public boolean isSatisfiable(InputReader in) {
		isSAT = true;
		clauses = in.nextInt();

		vertices = clauses * 2 + 2; // x and !x ... +2 for starting from 1 and x to !x constitutes 2x+1.
		// eg. -3 to 3... clauses 3... so we want from 1 (=-3+3+1) to 7 (=3+3+1)
		// total vertices = 3*2 + 2 = 8
		readGraph(in);

		findStronglyConnectedComponents();

		return isSAT;
	}

	private void findStronglyConnectedComponents() {
		int[] finishArray = new int[vertices];
		boolean[] visited = new boolean[vertices];

		boolean[] tempVisited = new boolean[vertices];

		finishTime = 0;

		// run the DFS on the reverse graph... thus establishing the finish times.
		for (int node = 1; node < vertices; node++) {
			if (!visited[node])
				firstDfs(node, visited, finishArray, tempVisited);
		}

		visited = new boolean[vertices];

		// run the second DFS on nodes based on the decreasing order of their finish times.
		for (int finishTime = vertices - 1; finishTime > 0; finishTime--) {
			int node = finishArray[finishTime];
			if (!visited[node]) {
				secondDfs(node, visited);
			}

			if (!isSAT) {
				break;
			}
		}
	}


	private void firstDfs(int node, boolean [] visited, int[] finishArray, boolean[] tempVisited) {
		// need another stack to record the finish time, since we are not using recursion.
		Stack<Integer> finishTimeStack = new Stack<>();

		Stack<Integer> stack = new Stack<>();
		stack.push(node);
		finishTimeStack.push(node);

		while (!stack.isEmpty()) {
			node = stack.pop();
			visited[node] = true;
			List<Integer> neighbours = reverseGraph.adjacencyList[node];
			if (neighbours != null) {
				for (int neighbour : neighbours) {
					if (!visited[neighbour] && !tempVisited[neighbour]) {
						stack.push(neighbour);
						finishTimeStack.push(neighbour);
						tempVisited[neighbour] = true; // added to filter out duplicate edges.
					}
				}
			}
		}

		while (!finishTimeStack.isEmpty()) {
			finishTime++;
			node = finishTimeStack.pop();
			finishArray[finishTime] = node;
		}
	}

	private void secondDfs(int node, boolean[] visited) {
		// check if we have seen this earlier in the DFS
		Set<Integer> set = new HashSet<>();
		int checkNode = Math.abs(node - clauses - 1);
		set.add(checkNode);

		Stack<Integer> stack = new Stack<>();
		stack.push(node);

//		System.out.println(" --- START SCC ----");

		while (!stack.isEmpty()) {
			node = stack.pop();
			visited[node] = true;
//			System.out.println("node in SCC -->" + node);
			List<Integer> neighbours = graph.adjacencyList[node];
			if (neighbours != null) {
				for (int neighbour : neighbours) {
					if (!visited[neighbour]) {
						stack.push(neighbour);

						checkNode = Math.abs(neighbour - clauses - 1);
						if (set.contains(checkNode)) {
							isSAT = false;
							break;
						}
						set.add(checkNode);
					}
				}
			}

			if (!isSAT)
				break;
		}

//		System.out.println(" ----- END SCC ----");
	}

	// since graph has negative vertices to represent the complement of a variable, we shall add
	// a constant.. no_of_clauses to make everything positive.
	// for a edge a --> b, add two edges !a -->b & !b --> a
	private void readGraph(InputReader in) {
		List<Integer>[] adjacencyList = new ArrayList[vertices];
		List<Integer>[] reverseList = new ArrayList[vertices];
		graph = new Graph(adjacencyList);
		reverseGraph = new Graph(reverseList);

		for (int i = 0; i < clauses; i++) {
			int node1 = in.nextInt();
			int node2 = in.nextInt();

			int compNode1 = -node1;
			int compNode2 = -node2;

			graph.addNeighbour(compNode1 + clauses + 1, node2 + clauses + 1);
			graph.addNeighbour(compNode2 + clauses + 1, node1 + clauses + 1);

			reverseGraph.addNeighbour(node2 + clauses + 1, compNode1+clauses+1);
			reverseGraph.addNeighbour(node1 + clauses + 1, compNode2 + clauses + 1);
		}
	}
}

class Graph {
	List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(Integer node, Integer neighbour) {
		List<Integer> neighbours = adjacencyList[node];

		if (neighbours == null) {
			neighbours = new ArrayList<>();
			adjacencyList[node] = neighbours;
		}

		neighbours.add(neighbour);
	}
}
