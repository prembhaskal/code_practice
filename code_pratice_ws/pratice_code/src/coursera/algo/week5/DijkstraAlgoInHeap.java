package coursera.algo.week5;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DijkstraAlgoInHeap {

	String[] file;
	int nodes;
	Graph graph;

	boolean[] explored;

	HeapAsArray heap;

	public void solve(Scanner in, PrintWriter out, int nodes) {
		this.nodes = nodes;

		readFile(in);
		formGraph();
	}

	private void initialize() {

	}

	private void formGraph() {
		List<Node>[] adjacencyList = new ArrayList[nodes+1];
		graph = new Graph(adjacencyList);

		for (String str : file) {
			String[] neighbours = str.split(" ");

			int vertex = Integer.parseInt(neighbours[0]);

			for (int i=1;i<neighbours.length;i++) {
				int nodeNo = Integer.parseInt(neighbours[i].split(",")[0]);
				int length = Integer.parseInt(neighbours[i].split(",")[1]);
				Node node = new Node(nodeNo, length);
				graph.addNeighbour(vertex, node);
			}
		}
	}

	private void readFile(Scanner in) {
		file = new String[nodes];

		for (int i=0;i<nodes;i++) {
			file[i] = in.nextLine();
		}
	}


	private class Graph {
		List<Node>[] adjacencyList;

		public Graph(List<Node>[] adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		public void addNeighbour(int fromNode, Node toNode) {
			List<Node> neighbours = adjacencyList[fromNode];
			if (neighbours == null) {
				neighbours = new ArrayList<Node>();
				adjacencyList[fromNode] = neighbours;
			}
			neighbours.add(toNode);
		}
	}

	private class Node {
		int vertex;
		int edgeLength;

		public Node(int vertex, int edgeLength) {
			this.vertex = vertex;
			this.edgeLength = edgeLength;
		}
	}
}


