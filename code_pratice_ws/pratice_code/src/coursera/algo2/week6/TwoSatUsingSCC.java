package coursera.algo2.week6;

import common.util.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TwoSatUsingSCC {

	Graph graph;
	Graph reverseGraph;

	int clauses;
	int vertices;

	int finishTime;

	public boolean isSatisfiable(InputReader in) {
		clauses = in.nextInt();

		vertices = clauses * 2 + 1; // x and !x ... +1 for starting from 0
		readGraph(in);

		findStronglyConnectedComponents();

		return false;
	}

	private void findStronglyConnectedComponents() {
		int[] finishArray = new int[vertices];
		boolean[] visited = new boolean[vertices];
		finishTime = 0;

		// run the DFS on the reverse graph... thus establishing the finish times.
		for (int node = 1; node < vertices; node++) {
			if (!visited[node])
				firstDfs(node, visited, finishArray);
		}

		visited = new boolean[vertices];

		// run the second DFS on nodes based on the decreasing order of their finish times.
		for (int finishTime = vertices - 1; finishTime > 0; finishTime--) {
			int node = finishArray[finishTime];
			if (!visited[node]) {
				secondDfs(node, visited);
			}
		}
	}


	private void firstDfs(int node, boolean [] visited, int[] finishArray) {
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
					if (!visited[neighbour]) {
						stack.push(neighbour);
						finishTimeStack.push(neighbour);
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
		Stack<Integer> stack = new Stack<>();
		stack.push(node);

		System.out.println(" --- START SCC ----");

		while (!stack.isEmpty()) {
			node = stack.pop();
			visited[node] = true;
			System.out.println("node in SCC -->" + node);
			List<Integer> neighbours = graph.adjacencyList[node];
			if (neighbours != null) {
				for (int neighbour : neighbours) {
					if (!visited[neighbour]) {
						stack.push(neighbour);
					}
				}
			}
		}

		System.out.println(" ----- END SCC ----");
	}

	// since graph has negative vertices to represent the complement of a variable, we shall add
	// a constant.. no_of_clauses to make everything positive.
	private void readGraph(InputReader in) {
		List<Integer>[] adjacencyList = new ArrayList[vertices];
		List<Integer>[] reverseList = new ArrayList[vertices];
		graph = new Graph(adjacencyList);
		reverseGraph = new Graph(reverseList);

		for (int i = 0; i < clauses; i++) {
			int node1 = in.nextInt();
			int node2 = in.nextInt();

//			node1 += clauses;
//			node2 += clauses;

			graph.addNeighbour(node1, node2);
			reverseGraph.addNeighbour(node2, node1);
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
