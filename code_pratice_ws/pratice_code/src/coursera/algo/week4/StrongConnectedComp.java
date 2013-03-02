package coursera.algo.week4;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StrongConnectedComp {

	private Graph graph;

	private int finishTime;
	private int sccSize;
	private int[] finishTimeList;

	private int[] helperArray;

	int nodes;

	List<Integer> sccSizes = new ArrayList<Integer>();

	public void solve(Scanner in, PrintWriter out, int nodes) {
		this.nodes = nodes;
		List<Integer>[] adjacencyList = new ArrayList[nodes+1];
		graph = new Graph(adjacencyList);

		int fromNode, toNode;
		while (in.hasNext()) {
			fromNode = in.nextInt();
			toNode = in.nextInt();
			graph.addNeighbour(fromNode, toNode);
		}

		fixNullList(graph);

		findStronglyConnComp(out);

		System.out.println("graph created");
	}

	private void fixNullList(Graph graph) {
		for (int i=1;i<=nodes;i++) {
			if (graph.adjacencyList[i]==null) {
				graph.adjacencyList[i] = new ArrayList<Integer>();
			}
		}
	}

	private void findStronglyConnComp(PrintWriter out) {
		// TODO how do reverse the graph easily, without recreating it again or whatever
		Graph reverseGraph = getReverseGraph(graph);
		int length = reverseGraph.adjacencyList.length;

		boolean[] explored = new boolean[length];
		helperArray = new int[length];
		finishTimeList = new int[length];

		for (int node = 1; node < length; node++) {
			if (explored[node]==false) {
				firstDFS(node, explored, reverseGraph);
			}
		}

		boolean[] newExplored = new boolean[length];

		for (int i = length-1; i > 0; i--) {
			int node = helperArray[i];
			if (newExplored[node]==false) {
				sccSize = 0;
				secondDFS(node, newExplored, graph);
//				System.out.println("found SCC - " + sCCNodes++ + " of size - " + sccSize);
				if (sccSize > 1) {
					sccSizes.add(sccSize);
				}
			}
		}

		Collections.sort(sccSizes);

		for (int i=0;i<5;i++) {
			out.println(sccSizes.get(sccSizes.size()-i-1));
		}

	}


	private Graph getReverseGraph(Graph normalGraph) {
		List<Integer>[] normalList = normalGraph.adjacencyList;
		List<Integer>[] newlist = new ArrayList[normalList.length];
		Graph reverseGraph = new Graph(newlist);
		int length = normalList.length;

		for (int node=1;node<length;node++) {
			if (normalList[node]==null) {
				System.out.println("check this out");
			}
			for (int neighbour : normalList[node]) {
				reverseGraph.addNeighbour(neighbour, node);
			}
		}

		fixNullList(reverseGraph);

		return reverseGraph;
	}


	private void firstDFS(int node, boolean[] explored, Graph graph) {
		explored[node] = true;
		for (int neighbour : graph.adjacencyList[node]) {
			if (explored[neighbour]==false) {
				firstDFS(neighbour, explored, graph);
			}
		}

		finishTime++;
		finishTimeList[node] = finishTime;
		helperArray[finishTime] = node;
	}

	private void secondDFS(int node, boolean[] explored, Graph graph) {
		explored[node] = true;
		for (int neighbour : graph.adjacencyList[node]) {
			if (explored[neighbour]==false) {
				secondDFS(neighbour, explored, graph);
			}
		}
		sccSize++;
	}

}

class Graph {
	public List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addNeighbour(Integer fromNode, Integer toNode) {
		List<Integer> neighbours = adjacencyList[fromNode];
		if (neighbours==null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[fromNode] = neighbours;
		}
		neighbours.add(toNode);
	}
}