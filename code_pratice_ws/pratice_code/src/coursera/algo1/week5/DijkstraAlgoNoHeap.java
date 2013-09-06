package coursera.algo1.week5;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DijkstraAlgoNoHeap {

	String[] file;

	int nodes;
	Graph graph;
	boolean[] isExplored;
	int[] shortestDistances;


	public void solve(Scanner in, PrintWriter out, int nodes) {
		this.nodes = nodes;

		readFile(in);

		formGraph();

		initialise();

		computeShortestPath();

		printShortestPaths(out);

		printCourseraResult(out);

	}

	private void printCourseraResult(PrintWriter out) {
		if (nodes >= 200) {
			int[] nodesToTest = new int[]{7,37,59,82,99,115,133,165,188,197};


			for (int nd : nodesToTest) {
				out.print(shortestDistances[nd] + ",");
			}
		}

	}

	private void printShortestPaths(PrintWriter out) {
		for (int i=1;i<nodes+1;i++) {
			out.println(i + " " + shortestDistances[i]);
		}
	}

	private void computeShortestPath() {

		// mark first as explored
		isExplored[1] = true;
		shortestDistances[1] = 0;

		for (int i=2;i<=nodes;i++) {
			int nextNode = getNodeWithMinDijkstraScore();
			if (nextNode == -1) {
				break;
			}
			isExplored[nextNode] = true;
		}
	}


	private int getNodeWithMinDijkstraScore() {
		int min = Integer.MAX_VALUE;
		int minNode = -1;

		for (int i=1;i<=nodes;i++) {
			if (isExplored[i] == true) { // path with tail in explored area and head in unexplored.
				if (graph.adjacencyList[i]!=null) {
					for (Node neighbour: graph.adjacencyList[i]) {
						if (isExplored[neighbour.vertex]==false) {
							int minScore = getShortestDistance(i, neighbour.edgeLength);
							if (min > minScore) {
								min = minScore;
								minNode = neighbour.vertex; // this is vertex in unexplored area.
							}
						}
					}
				}

			}
		}

		if (minNode > 0)
			shortestDistances[minNode] = min;

		return minNode;
	}


	private int getShortestDistance(int fromNode, int distance) {
		return shortestDistances[fromNode] + distance;
	}


	private void initialise() {
		isExplored = new boolean[nodes + 1];
		shortestDistances = new int[nodes + 1];

		Arrays.fill(shortestDistances, Integer.MAX_VALUE);
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

}

class Graph {
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

class Node {
	int vertex;
	int edgeLength;

	public Node(int vertex, int edgeLength) {
		this.vertex = vertex;
		this.edgeLength = edgeLength;
	}
}
