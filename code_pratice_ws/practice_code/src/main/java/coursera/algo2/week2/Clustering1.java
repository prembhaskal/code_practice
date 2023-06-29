package coursera.algo2.week2;

import common.util.InputReader;
import java.util.Arrays;

public class Clustering1 {

	Edge[] edges;
	int numberOfNodes;
	int numberOfEdges;
	int numberOfClusters;

	public int findMaxSpacing(InputReader in, int clusters) {
		numberOfNodes = in.nextInt();

		numberOfEdges = numberOfNodes * (numberOfNodes-1);
		numberOfEdges = numberOfEdges/2;

		edges = new Edge[numberOfEdges];

		readGraph(in);

		int maxSpacing = runClusteringAlgo(clusters);

		return maxSpacing;
	}

	private int runClusteringAlgo(int clusters) {
		// smallest edges are considered first.
		Arrays.sort(edges);

		numberOfClusters = numberOfNodes;

		NaiveUnionFind unionFind = new NaiveUnionFind(numberOfNodes);

		int i=0;
		while (numberOfClusters != clusters) {
			Edge edge = edges[i++];

			int node1 = edge.node1;
			int node2 = edge.node2;

			// if not in same cluster then merge
			if (unionFind.find(node1) != unionFind.find(node2)) {
				unionFind.merge(node1, node2);
				numberOfClusters--;
			}

		}

		// find max-spacing in remaining of edges
		int maxSpacing = 0;
		for (;i<edges.length;i++) {
			Edge edge = edges[i];
			int node1 = edge.node1;
			int node2 = edge.node2;

			if (unionFind.find(node1) != unionFind.find(node2)) {
				maxSpacing = edge.cost;
				break;
			}
		}

		return maxSpacing;
	}


	private void readGraph(InputReader in) {
		for (int i = 0; i < numberOfEdges; i++) {
			int node1 = in.nextInt();
			int node2 = in.nextInt();
			int cost = in.nextInt();
			edges[i] = new Edge(node1, node2, cost);
		}
	}



	// class Edge
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
