package coursera.algo2.week4;

import common.util.InputReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestJohnsonAlgorithm {

	private int totalNodes;
	private int totalEdges;

	private Graph normalGraph;
	private GraphInBound inBoundGraph;

	public Integer getShortestShortestPath(InputReader in) {
		totalNodes = in.nextInt();
		totalNodes += 2; // +1 for 1 based index & +1 for extra node added.
		totalEdges = in.nextInt();

		readGraphWithAdditionalNode(in);

		return 0;
	}

	private int[] presentBellMan;
	private int[] previousBellMan;
	private void runBellManFordAlgorithm(){
		// the start node for this run will be the dummyNode --> (totalNodes-1);
		previousBellMan = new int[totalNodes];
		// base case A[0,start] = 0 else infinity.
		Arrays.fill(previousBellMan, Integer.MAX_VALUE);
		previousBellMan[totalNodes-1] = 0;

		presentBellMan = new int[totalNodes];

		for (int budget = 1; budget < totalNodes; budget++) {
			for (int vertex = 1; vertex < totalNodes-1; vertex++) {
				int val1 = previousBellMan[vertex];
				int val2 = Integer.MAX_VALUE;
				List<Node> inBoundNodes = inBoundGraph.inBoundList[vertex];
				if (inBoundNodes != null) {
					long valMin = Integer.MAX_VALUE;
					for (Node inBoundNode : inBoundNodes) {
						valMin = Math.min(previousBellMan[inBoundNode.vertex] + inBoundNode.cost , valMin);
					}

					if (valMin > Integer.MAX_VALUE)
						valMin = Integer.MAX_VALUE;

					val2 = (int) valMin;
				}

				presentBellMan[vertex] = Math.min(val1, val2);
			}

			// throw old previous values
			previousBellMan = presentBellMan;
			presentBellMan = new int[totalNodes];
		}
	}

	private void readGraphWithAdditionalNode(InputReader in) {
		List<Node>[] adjacencyList = new ArrayList[totalNodes];
		normalGraph = new Graph(adjacencyList);

		List<Node>[] inBoundList = new ArrayList[totalNodes];
		inBoundGraph = new GraphInBound(inBoundList);

		for (int i = 0; i < totalEdges; i++) {
			int fromVertex = in.nextInt();
			int toVertex = in.nextInt();
			int cost = in.nextInt();

			Node fromNode = new Node(fromVertex, cost);
			Node toNode = new Node(toVertex, cost);

			normalGraph.addNeighbour(fromVertex, toNode);
			inBoundGraph.addInBoundNode(fromNode, toNode);
		}

		// add 0 cost edges from the last dummy node to all other nodes.
		// this is needed only for bellman ford.
		int dummyVertex = totalNodes - 1;
		Node dummyNode = new Node(dummyVertex, 0);
		for (int i = 1; i < totalNodes - 1; i++) {
			Node toNode = new Node(i, 0);
			inBoundGraph.addInBoundNode(dummyNode, toNode);
		}
	}

	private class Graph {
		List<Node>[] adjacencyList;

		public Graph(List<Node>[] adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		public void addNeighbour(Integer fromVertex, Node toNode) {
			List<Node> neighbours = adjacencyList[fromVertex];
			if (neighbours == null) {
				neighbours = new ArrayList<>();
				adjacencyList[fromVertex] = neighbours;
			}

			neighbours.add(toNode);
		}
	}

	// in bound graph, so that in bound vertices could be found very easily.
	private class GraphInBound {
		List<Node>[] inBoundList;

		public GraphInBound(List<Node>[] adjacencyList) {
			this.inBoundList = adjacencyList;
		}

		public void addInBoundNode(Node fromNode, Node toNode) {
			List<Node> inboundNodes = inBoundList[toNode.vertex];

			if (inboundNodes == null) {
				inboundNodes = new ArrayList<>();
				inBoundList[toNode.vertex] = inboundNodes;
			}

			inboundNodes.add(fromNode);
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
