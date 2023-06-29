package coursera.algo2.week4;

import common.util.InputReader;
import coursera.algo1.week5.GenericHeap;
import coursera.algo1.week5.HeapEntry;
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
		runBellManFordAlgorithm();
		calculateNewEdgesCosts(previousBellMan);

		runDijkstraForAllNodes();
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

		// DP for the Bellman Ford.
		// for each of budget 1 to n-1, find the new values.
		// using only 2 1-D array for space optimisation.
		for (int budget = 1; budget < totalNodes - 1; budget++) {
			for (int vertex = 1; vertex < totalNodes-1; vertex++) {
				presentBellMan[vertex] = getBellManMinValue(vertex);
			}

			// throw old previous values
			previousBellMan = presentBellMan;
			presentBellMan = new int[totalNodes];
		}

	}

	private int getBellManMinValue(int vertex) {
		int val1 = previousBellMan[vertex];
		int val2 = Integer.MAX_VALUE;
		List<Node> inBoundNodes = inBoundGraph.inBoundList[vertex];
		if (inBoundNodes != null) {
			long valMin = Integer.MAX_VALUE;
			for (Node inBoundNode : inBoundNodes) {
				valMin = Math.min(previousBellMan[inBoundNode.vertex] + (long)inBoundNode.cost , valMin);
			}

			if (valMin > Integer.MAX_VALUE)
				valMin = Integer.MAX_VALUE;

			val2 = (int) valMin;
		}

		return Math.min(val1, val2);
	}

	private void calculateNewEdgesCosts(int[] bellManValues) {
		for (int vertex = 1; vertex < totalNodes - 1; vertex++) {
			List<Node> neighbours = normalGraph.adjacencyList[vertex];
			if (neighbours != null) {
				for (Node neighbour : neighbours) {
					int newCost = neighbour.cost + bellManValues[vertex] - bellManValues[neighbour.vertex];
					neighbour.cost = newCost;

					if (newCost < 0) {
						System.out.println("has negative");
					}
				}
			}
		}
	}

	private void runDijkstraForAllNodes() {

		for (int node = 1; node < totalNodes - 1; node++) {
			runDijkstraForNode(node);
		}
	}


	boolean[] isExplored;
	int[] minDijkstraScores;
	GenericHeap<HeapEntry> heap;

	private void runDijkstraForNode(int startNode) {
		isExplored = new boolean[totalNodes - 1];
		minDijkstraScores = new int[totalNodes - 1];
		isExplored[startNode] = true;// start with the first node.


		Arrays.fill(minDijkstraScores, Integer.MAX_VALUE);
		minDijkstraScores[startNode] = 0;

		initializeHeap(startNode);

		System.out.println("running for source node " + startNode);

		for (int i = 1; i <= totalNodes - 2; i++) {
			if ( i != startNode) {
				HeapEntry minNode = heap.extractMin();
				if (heap.getSize() == 0) {
					System.out.println("node --> " + i);
				}
				minDijkstraScores[minNode.value] = minNode.key;
				isExplored[minNode.value] = true;
				recomputeMinScoreFor(minNode.value);
			}
		}

	}


	private void recomputeMinScoreFor(int node) {
		if (normalGraph.adjacencyList[node]==null)
			return;

		for (Node neighbour : normalGraph.adjacencyList[node]) {
			if (isExplored[neighbour.vertex]==false) {
				heap.delete(heap.getIndexOf(neighbour.vertex));
				// min[Ui] = min(Luw + A[w], A[Ui])
				int minScore = Math.min(minDijkstraScores[node] + neighbour.cost,
						minDijkstraScores[neighbour.vertex]);
				heap.insert(new HeapEntry(minScore, neighbour.vertex));
				// need to keep track of minimum length for a node.
				minDijkstraScores[neighbour.vertex] = Math.min(minScore, minDijkstraScores[neighbour.vertex]);
			}
		}
	}


	private void initializeHeap(int startNode) {
		// add only edges starting from 0 to rest of graph.
		if (normalGraph.adjacencyList[startNode] != null)
			for (Node neighbour : normalGraph.adjacencyList[startNode]) {
				minDijkstraScores[neighbour.vertex] = neighbour.cost;
			}

		// TODO -3 -2 clean it up, its unnecessarily complex
		// store the min value for nodes other than start node.
		HeapEntry[] heapEntries = new HeapEntry[totalNodes-3];
		for (int i = 1, j = 0; i <= totalNodes - 2; i++) {
			if (i != startNode) {
				HeapEntry heapEntry = new HeapEntry(minDijkstraScores[i], i);
				heapEntries[j++] = heapEntry;
			}
		}

		heap = new GenericHeap<>(heapEntries, totalNodes-1);
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
