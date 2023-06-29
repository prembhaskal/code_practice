package coursera.algo2.week6;

import common.util.InputReader;
import java.util.*;
import javax.swing.ListModel;

public class TwoSATSCCReduction {

	int[][] clauses;
	Set<Integer> vertices;
	Graph graph;
	Graph reverseGraph;
	boolean isSAT;
	int[] finishArray;
	int finishTime;

	public boolean isSAT(InputReader in) {
		isSAT = true;
		GraphReduction graphReduction = new GraphReduction();
		clauses = graphReduction.getReducedClauses(in);

		formGraph();

		findStronglyConnectedComponent();

		return isSAT;
	}

	private void findStronglyConnectedComponent() {
		Set<Integer> visited = new HashSet<>();
		Set<Integer> tempVisited = new HashSet<>();
		finishTime = 0;
		finishArray = new int[vertices.size()+1];

		// first DFS to get the finish times.
		for (Integer node : vertices) {
			if (!visited.contains(node)) {
				firstDFS(node, visited, tempVisited);
			}
		}

		visited = new HashSet<>();
		for (int i = finishArray.length - 1; i > 0 ; i--) {
			Integer node = finishArray[i];
			if (!visited.contains(node)) {
				secondDFS(node, visited);
			}

			if (!isSAT)
				break;
		}

	}

	private void firstDFS(Integer node, Set<Integer> visited, Set<Integer> tempVisited) {
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> finishStack = new Stack<>();

		stack.push(node);
		finishStack.push(node);

		while (!stack.isEmpty()) {
			node = stack.pop();
			visited.add(node);
			List<Integer> neighbours = reverseGraph.adjacencyList.get(node);
			if (neighbours != null)
				for (Integer neighbour : neighbours) {
					if (!visited.contains(neighbour) && !tempVisited.contains(neighbour)) {
						stack.push(neighbour);
						finishStack.push(neighbour);
						tempVisited.add(neighbour);
					}
				}
		}

		while (!finishStack.isEmpty()) {
			node = finishStack.pop();
			finishTime++;
			finishArray[finishTime] = node;
		}
	}

	private void secondDFS(Integer node, Set<Integer> visited) {
		Stack<Integer> stack = new Stack<>();

		Set<Integer> SCCNodes = new HashSet<>();

		stack.push(node);

		while (!stack.isEmpty()) {
			node = stack.pop();
			visited.add(node);
			List<Integer> neighbours = graph.adjacencyList.get(node);
			if (neighbours != null)
				for (Integer neighbour : neighbours) {
					if (!visited.contains(neighbour)) {
						stack.push(neighbour);

						Integer absNode = Math.abs(neighbour);
						if (SCCNodes.contains(absNode)) {
							isSAT = false;
							break;
						}

						SCCNodes.add(absNode);
					}
				}

			if (!isSAT)
				break;
		}
	}

	private void formGraph() {
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
		graph = new Graph(adjacencyList);

		Map<Integer, List<Integer>> reverseList = new HashMap<>();
		reverseGraph = new Graph(reverseList);

		vertices = new HashSet<>();

		for (int i = 0; i < clauses.length; i++) {
			int node1 = clauses[i][0];
			int node2 = clauses[i][1];

			vertices.add(node1);
			vertices.add(-node1);
			vertices.add(node2);
			vertices.add(-node2);

			graph.addNeighbour(-node1, node2);
			graph.addNeighbour(-node2, node1);

			reverseGraph.addNeighbour(node2, -node1);
			reverseGraph.addNeighbour(node1, -node2);
		}
	}

	private class Graph {
		Map<Integer, List<Integer>> adjacencyList;

		public Graph(Map<Integer, List<Integer>> adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		public void addNeighbour(Integer node, Integer neighbour) {
			List<Integer> neighbours = adjacencyList.get(node);
			if (neighbours == null) {
				neighbours = new ArrayList<>();
				adjacencyList.put(node, neighbours);
			}

			neighbours.add(neighbour);
		}
	}
}


