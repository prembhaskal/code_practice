package coursera.algo2.week4;

import common.util.InputReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestShortestPath {

	int totalNodes;
	int totalEdges;

	Graph graph;

	public Integer getShortestShortestPath(InputReader in) {

		totalNodes = in.nextInt();
		totalEdges = in.nextInt();
		readGraph(in);

		Integer shortestPath = runFloydWarshall2Arrays();

		return shortestPath;
	}

	private int [][] previousArray;
	private int [][] presentArray;

	private Integer runFloydWarshall2Arrays() {
		previousArray = new int[totalNodes+1][totalNodes+1];
		presentArray = new int[totalNodes+1][totalNodes+1];

		calculateBaseValues();

		// run the DP algorithm
		for (int k = 1; k < totalNodes + 1; k++) {
			for (int i = 1; i < totalNodes + 1; i++) {
				for (int j = 1; j < totalNodes + 1; j++) {
					// stupid overflow. :( :(
					long sumVal = (long)previousArray[i][k] + previousArray[k][j];
					if (sumVal > Integer.MAX_VALUE)
						sumVal = Integer.MAX_VALUE;
					int minVal = Math.min(previousArray[i][j], (int)sumVal);
					presentArray[i][j] = minVal;

				}
			}

			// now throw away the previous array....make the present as previous
			previousArray = presentArray;
			presentArray = new int[totalNodes+1][totalNodes+1];
		}

		boolean isNegative = isNegativeCyclePresentFloydWarshall();
		if (isNegative) {
			return null;
		}

		// get the shortest path (ignore self connections).
		int shortestPath = Integer.MAX_VALUE;
		for (int i=1;i<totalNodes+1;i++) {
			for (int j = 1; j < totalNodes+1; j++) {
				if (i != j)
					shortestPath = Math.min(shortestPath, previousArray[i][j]);
			}
		}

		return shortestPath;
	}

	// check diagonal for the floyd warshall's algorithm to check if any node can be reached to itself in less than 0.
	private boolean isNegativeCyclePresentFloydWarshall() {
		boolean isNegative = false;

		for (int i = 1; i < totalNodes + 1; i++) {
			if (previousArray[i][i] < 0) {
				isNegative = true;
				break;
			}
		}

		return isNegative;
	}

	private void calculateBaseValues() {
		// assume nodes are not connected by default.
		for (int i = 1; i < totalNodes + 1; i++) {
			for (int j = 1; j < totalNodes+1; j++) {
				previousArray[i][j] = Integer.MAX_VALUE;
			}
		}

		// same nodes the distance is 0
		for (int i = 1; i < totalNodes+1; i++) {
			previousArray[i][i] = 0;
		}

		// read the graph and put appropriate values.
		for (int i = 1; i < totalNodes+1; i++) {
			List<Node> neighbours = graph.adjacencyList[i];
			if (neighbours != null) {
				for (Node neighbour : neighbours) {
					previousArray[i][neighbour.vertex] = neighbour.cost;
				}
			}
		}
	}

	private void readGraph(InputReader in) {
		List<Node>[] adjacencyList = new ArrayList[totalNodes+1];
		graph = new Graph(adjacencyList);

		for (int i = 0; i < totalEdges; i++) {
			int fromVertex = in.nextInt();
			int toVertex = in.nextInt();
			int cost = in.nextInt();
			Node fromNode = new Node(toVertex, cost);

			graph.addNeighbour(fromVertex, fromNode);
		}
	}



	private class Graph {
		List<Node>[] adjacencyList;

		public Graph(List<Node>[] adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		public void addNeighbour(Integer fromVertex, Node  node) {
			List<Node> neighbours = adjacencyList[fromVertex];
			if (neighbours == null) {
				neighbours = new ArrayList<>();
				adjacencyList[fromVertex] = neighbours;
			}

			neighbours.add(node);
		}
	}

	private class Node {
		int vertex;
		int cost;

		public Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

	}
}