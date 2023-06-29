package coursera.algo2.week1;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PrimMinSpanTreeSansHeap {

	private Graph graph;
	private int nodes;

	private boolean[] explored;

	// 1 based Graph
	public void solve(InputReader in, PrintWriter out) {
		nodes = in.nextInt();
		int noOfEdges = in.nextInt();

		readGraph(noOfEdges, in);

		int minSpanCost = getMinSpanTreeCost();

		out.println(minSpanCost);
	}

	private int getMinSpanTreeCost() {
		explored = new boolean[nodes+1];

		// put first node as explored
		explored[1] = true;

		int minCost = 0;
		// we will need to do this only n-1 times, where n = no of nodes.
		for (int i=0;i<nodes-1;i++) {
			minCost += getMinCostForNextNode();
		}

		return minCost;
	}

	private int getMinCostForNextNode() {
		int minCost = Integer.MAX_VALUE;
		int nextNode = -1;

		for (int fromNode = 1; fromNode < nodes+1; fromNode++) {
			if (explored[fromNode]) { // from node is in explored area

				for (Node neighbour : graph.adjacencyList[fromNode]) {
					if (!explored[neighbour.vertex]) { // to node should be UNEXPLORED
						if (minCost > neighbour.cost) {
							minCost = neighbour.cost;
							nextNode = neighbour.vertex;
						}
					}
				}
			}
		}

		// put nextNode in explored area.
		explored[nextNode] = true;

		return minCost;
	}

	private void readGraph(int noOfEdges, InputReader in) {
		List<Node>[] adjacencyList = new ArrayList[nodes+1];
		graph = new Graph(adjacencyList);

		for (int i = 0; i < noOfEdges; i++) {
			int fromVertex = in.nextInt();
			int toVertex = in.nextInt();
			int cost = in.nextInt();

			// this is undirected graph, so add for both the side.
			Node fromNode = new Node(fromVertex, cost);
			Node toNode = new Node(toVertex, cost);

			graph.addNeighbour(fromNode, toNode);
			graph.addNeighbour(toNode, fromNode);
		}
	}


}


class Graph {
	List<Node>[] adjacencyList;

	public Graph(List<Node>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(Node fromNode, Node toNode) {
		List<Node> neighbours = adjacencyList[fromNode.vertex];
		if (neighbours == null) {
			neighbours = new ArrayList<>();
			adjacencyList[fromNode.vertex] = neighbours;
		}

		neighbours.add(toNode);
	}
}

class Node {
	int vertex;
	int cost;

	public Node(int vertex, int cost) {
		this.vertex = vertex;
		this.cost = cost;
	}
}
