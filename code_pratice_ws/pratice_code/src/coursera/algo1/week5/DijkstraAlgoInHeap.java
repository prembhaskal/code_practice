package coursera.algo1.week5;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DijkstraAlgoInHeap {

	String[] file;
	int nodes;
	Graph graph;

	boolean[] isExplored;
	int[] minDijkstraScores;
	GenericHeap<HeapEntry> heap;

	public void solve(Scanner in, PrintWriter out, int nodes) {
		this.nodes = nodes;

		readFile(in);
		formGraph();
		computeShortestPath();
		printShortestPaths(out);
		printCourseraResult(out);
	}

	private void computeShortestPath() {
		isExplored = new boolean[nodes+1];
		minDijkstraScores = new int[nodes+1];
		isExplored[1] = true;// start with the first node.

		Arrays.fill(minDijkstraScores, Integer.MAX_VALUE);
		minDijkstraScores[1] = 0;

		initializeHeap();

		for (int i=2; i<=nodes;i++) {
			HeapEntry minNode = heap.extractMin();
			minDijkstraScores[minNode.value] = minNode.key;
			isExplored[minNode.value] = true;
			recomputeMinScoreFor(minNode.value);
		}
	}


	private void recomputeMinScoreFor(int node) {
		if (graph.adjacencyList[node]==null)
			return;

		for (Node neighbour : graph.adjacencyList[node]) {
			if (isExplored[neighbour.vertex]==false) {
				heap.delete(heap.getIndexOf(neighbour.vertex));
				// min[Ui] = min(Luw + A[w], A[Ui])
				int minScore = Math.min(minDijkstraScores[node] + neighbour.edgeLength,
										minDijkstraScores[neighbour.vertex]);
				heap.insert(new HeapEntry(minScore, neighbour.vertex));
				// need to keep track of minimum length for a node.
				minDijkstraScores[neighbour.vertex] = Math.min(minScore, minDijkstraScores[neighbour.vertex]);
			}
		}
	}


	private void initializeHeap() {
		// add only edges starting from 0 to rest of graph.
		for (Node neighbour : graph.adjacencyList[1]) {
			minDijkstraScores[neighbour.vertex] = neighbour.edgeLength;
		}

		// store the min value from 2 to last one
		HeapEntry[] heapEntries = new HeapEntry[nodes-1];
		for (int i=2;i<=nodes;i++) {
			HeapEntry heapEntry = new HeapEntry(minDijkstraScores[i], i);
			heapEntries[i-2] = heapEntry;
		}
		heap = new GenericHeap<HeapEntry>(heapEntries, nodes);
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

	private void printCourseraResult(PrintWriter out) {
		if (nodes >= 200) {
			int[] nodesToTest = new int[]{7,37,59,82,99,115,133,165,188,197};


			for (int nd : nodesToTest) {
				out.print(minDijkstraScores[nd] + ",");
			}
		}

	}

	private void printShortestPaths(PrintWriter out) {
		for (int i=1;i<nodes+1;i++) {
			out.println(i + " " + minDijkstraScores[i]);
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


