package coursera.algo2.week2;

import common.util.InputReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Clustering2 {

	int countOfNodes;
	int bits;
	String[] nodeBits;
	int numberOfClusters;

	List<Edge> edges = new ArrayList<>();

	HashMap<Integer, List<Integer>> lookup = new HashMap<>();

	public int getNoOfClusters(InputReader in) {
		countOfNodes = in.nextInt();
		nodeBits = new String[countOfNodes+1];
		bits = in.nextInt();

		readGraph(in);
		addInLookup();

		findAllEdgesWithinDistance2();

		System.out.println("total edges are " + edges.size());

		int maxClusters = runKruskalAlgo();
		return maxClusters;
	}

	private int runKruskalAlgo() {
		Collections.sort(edges);

		numberOfClusters = countOfNodes;

		UnionFind1 unionFind = new UnionFind1(countOfNodes);

		for (Edge edge : edges) {
			int node1 = edge.node1;
			int node2 = edge.node2;

			// if not same cluster then merge
			if (unionFind.find(node1) != unionFind.find(node2)) {
				unionFind.merge(node1, node2);
				numberOfClusters--;
			}
		}

		return numberOfClusters;
	}


	List<Integer> neighbourNodes;
	private void findAllEdgesWithinDistance2() {
		for (int i=1;i<countOfNodes+1;i++) {
			neighbourNodes = new ArrayList<>();
			int nodeRep = Integer.parseInt(nodeBits[i], 2);
			for (int dist = 0; dist < 3; dist++) {
				findAllEdgesAtDistance(dist, nodeRep ,i);
				for (int neighbour : neighbourNodes) {
					Edge edge = new Edge(i, neighbour, dist);
					edges.add(edge);
				}
			}

			neighbourNodes.clear();

		}
	}

	private int ONE_BIT_MASK = 0x0000001;

	private void findAllEdgesAtDistance(int distance, int nodeBits, int nodeNum) {
		if (distance==0) {
			getMatchedNodes(nodeBits, nodeNum);
		}
		else {
			int oneBitMask = ONE_BIT_MASK;
			for (int i=0;i<bits;i++) {
				int matchNodeReps = nodeBits ^ oneBitMask;
				findAllEdgesAtDistance(distance - 1, matchNodeReps, nodeNum);
				oneBitMask = oneBitMask << 1;
			}

		}

	}

	private void getMatchedNodes(int nodeReps, int nodeNum) {
		List<Integer> matchNodes = lookup.get(nodeReps);
		if (matchNodes!=null) {
			for (int matchedNode : matchNodes) {
				if (matchedNode > nodeNum) // we only search below this node, to avoid duplicates
					neighbourNodes.add(matchedNode);
			}
		}
	}

	private void addInLookup() {
		for (int i = 1; i < countOfNodes + 1; i++) {
			int intValue = Integer.parseInt(nodeBits[i], 2);
			List<Integer> nodes = lookup.get(intValue);
			if (nodes==null) {
				nodes = new ArrayList<>();
				lookup.put(intValue, nodes);
			}
			nodes.add(i);
		}
	}

	private void readGraph(InputReader in) {
		for (int i = 0; i < countOfNodes; i++) {
			StringBuilder sb = new StringBuilder("");
			for (int j = 0; j < bits; j++) {
				sb.append(in.nextInt());
			}

			nodeBits[i+1] = sb.toString();
		}
	}

	private class Edge implements Comparable<Edge>{

		int node1;
		int node2;
		int cost;

		public Edge(int node1, int node2, int cost) {
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.cost < o.cost)
				return -1;
			else if (this.cost > o.cost)
				return 1;
			else
				return 0;
		}
	}
}
