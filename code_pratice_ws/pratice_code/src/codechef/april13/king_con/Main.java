package codechef.april13.king_con;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();
	}

}

class TaskA {

	int[][] edges;

	int totalNodes;
	int totalEdges;
	int costOfShield;
	Graph graph;

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();


		for (int i=0;i<tests;i++) {
			totalNodes = in.nextInt();
			totalEdges = in.nextInt();
			costOfShield = in.nextInt();

			readGraph(in);

			formGraph();

			if (totalNodes == 2) {
				out.println(0);
			}
			else {
				int egdesToShield = getTotalEdgesToShield();
				out.println(egdesToShield*costOfShield);
			}

		}

	}

	// 0 based edges, first node index is 0
	private void readGraph(Scanner in) {
		edges = new int[totalEdges][2];
		for (int i=0;i<totalEdges;i++) {
			edges[i][0] = in.nextInt();
			edges[i][1] = in.nextInt();
		}
	}

	private void formGraph() {
		List<Integer>[] adjacencyList = new ArrayList[totalNodes]; // 0 based index, 0 is one of the node.
		graph = new Graph(adjacencyList);

		for (int i=0;i<totalEdges;i++) {
			graph.addNeighbour(edges[i][0], edges[i][1]);
			graph.addNeighbour(edges[i][1], edges[i][0]); // since an edge will be mentioned just once.
		}
	}

	//TODO this approach is slow and possibly wrong :( change it.
	private int getTotalEdgesToShield() {
		List<Integer> edgesToShield = new ArrayList<Integer>();

		for (int node=0;node<totalNodes;node++) {
			boolean [] isExplored = new boolean[totalNodes];

			// add this if connected to a lonelt node
			if (isConnectedToLonelyNode(node)) {
				edgesToShield.add(node);
				continue;// continue with the next one.
			}


			// we assume the city at 'node' is attacked, hence no road connected to it should be used,
			// do it by marking it explored in begining
			// start at city other than selected node.
			isExplored[node] = true;

			int nodeToStart = 0;
			if (node==0)
				nodeToStart = 1; // we have at least 2 totalNodes.

//			doDFS(nodeToStart, isExplored);
			doDFSStack(nodeToStart, isExplored);

			for (int i=0;i<totalNodes;i++) {
				if (isExplored[i]==false) {
					edgesToShield.add(node); // we will need to shield main node if anyone is left unconnected.
					break; // try next one
				}
			}
		}

//		System.out.print("edges to shield are ");
//		for (int toShield : edgesToShield)
//			System.out.print(toShield + " ");
//		System.out.println("");

		return edgesToShield.size();
	}

	private boolean isConnectedToLonelyNode(int node) {
		for (Integer neighbour : graph.adjacencyList[node]) {
			List<Integer> itsNeighbour = graph.adjacencyList[neighbour];
			if (itsNeighbour.size()==1) {
				return true;
			}
		}

		return false;
	}


	private void doDFSStack(int node, boolean [] isExplored) {
		Stack<Integer> dfsStack = new Stack<Integer>();

		isExplored[node] = true;
		dfsStack.push(node);

		while (!dfsStack.isEmpty()) {
			int vertex = dfsStack.pop();

//			isExplored[vertex] = true;

			for (Integer neighbour : graph.adjacencyList[vertex]) {
				if (isExplored[neighbour]==false) {
					isExplored[neighbour] = true;
					dfsStack.push(neighbour);
				}
			}
		}
	}

}

class Graph {
	List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}


	public void addNeighbour(Integer fromNode, Integer toNode) {
		List<Integer> neighbours = adjacencyList[fromNode];
		if (neighbours == null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[fromNode] = neighbours;
		}
		neighbours.add(toNode);
	}

}

