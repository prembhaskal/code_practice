package coursera.algo.week4;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrongConnectedComp {

	private Graph graph;

	public void solve(Scanner in, PrintWriter out, int nodes) {
		List<Integer>[] adjacencyList = new ArrayList[nodes];
		graph = new Graph(adjacencyList);

		int fromNode, toNode;
		while (in.hasNext()) {
			fromNode = in.nextInt();
			toNode = in.nextInt();
			graph.addNeighbour(fromNode, toNode);
		}
		System.out.println("graph created");
	}
}

class Graph {
	public List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(Integer fromNode, Integer toNode) {
		List<Integer> neighbours = adjacencyList[fromNode-1];
		if (neighbours==null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[fromNode-1] = neighbours;
		}
		neighbours.add(toNode);
	}
}