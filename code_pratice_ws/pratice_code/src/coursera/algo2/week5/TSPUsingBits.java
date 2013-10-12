package coursera.algo2.week5;

import common.util.InputReader;
import java.util.*;

public class TSPUsingBits {

	int totalCities;

	double [][] cityCoordinates;

	public double getMinTotalTravelPeriod(InputReader in) {

		totalCities = in.nextInt();
		cityCoordinates = new double[totalCities][2];

		for (int i = 0; i < totalCities; i++) {
			cityCoordinates[i][0] = in.nextDouble();
			cityCoordinates[i][1] = in.nextDouble();
		}

		double minDistance = getMinTotalTravelPeriod();

		return minDistance;

	}

	private Map<Integer, double[]> subGraphMap;

	private double getMinTotalTravelPeriod() {

		subGraphMap = new HashMap<>();

		initAndOrBits();
		initNearestNodes();

		// iterate for all sub graph sizes from 2 to n
		for (int size = 2; size <= totalCities; size++) {
			findForSubGraphs(0, 0, 0, size);
			System.out.println("completed level --> " + size);
		}

		// get minimum from full graph (all bits representing cities are set)
		int fullGraph = 1 << totalCities;
		fullGraph = fullGraph - 1;

		double[] lengths = subGraphMap.get(fullGraph);
		double min = Integer.MAX_VALUE;
		for (int i = 1; i < totalCities; i++) {
			double val1 = lengths[i];
			double val2 = getDistance(i, 0);
			min = Math.min(min, val1 + val2);
		}

		return min;
	}


	public void printCombinationOfBits(int setBits) {
		int bitMask = 0b0;

		initAndOrBits();
		findForSubGraphs(bitMask, 0, 0, setBits);
	}


	private int[] andBits;
	private int[] orBits;

	private void initAndOrBits() {
		andBits = new int[totalCities];
		orBits = new int[totalCities];

		int xorBit = 0b1;
		xorBit = xorBit << totalCities;
		xorBit = xorBit - 1;

		int start = 0b1;
		for (int i = 0; i < totalCities; i++) {
			andBits[i] = start ^ xorBit;
			orBits[i] = start;
			start = start << 1;
		}
	}

	// the initial bitMask should be 0000000000000, else it won't work.
	private void findForSubGraphs(int bitMask, int idx, int size, int maxSize) {
		// stop early, if we cannot meet the maxSize requirement.
		int remElements = totalCities - idx;
		if (size + remElements < maxSize)
			return;

		if (size == maxSize) {
//			doStuff(bitMask);
			doStuffLocalSearch(bitMask);
			return;
		}

		if (idx == totalCities)
			return;

		// choose the number.... set this bit.
		bitMask = bitMask | orBits[idx];
		findForSubGraphs(bitMask, idx + 1, size + 1, maxSize);

		// don't choose the number .... reset this bit.
		bitMask = bitMask & andBits[idx];
		findForSubGraphs(bitMask, idx + 1, size, maxSize);
	}

	// TODO do only local search in the otherSubGraph.... search only nearest nodes.
	private void doStuff(int mainSubGraph) {

		// loop over all selected nodes in the main sub graph.
		for (int node = 0; node < totalCities; node++) {
			int otherSubGraph = mainSubGraph & orBits[node];
			if (otherSubGraph == 0) // check if node 'i' is set , else continue.
				continue;

			otherSubGraph = mainSubGraph & andBits[node]; // reset this bit in the bitMask and use the resultant sub graph.
			double min = Integer.MAX_VALUE;
			for (int previousNode = 0; previousNode < totalCities; previousNode++) {
				if (node == previousNode)
					continue;
				// check if this node is set
				int res = otherSubGraph & orBits[previousNode];
				if (res == 0) // skip this bit is not set.
					continue;

				double val1 = getValueASubGraphk(otherSubGraph, previousNode);
				double val2 = getDistance(node, previousNode);

				double val = val1 + val2;
				min = Math.min(min, val);

				putValueAGraphK(mainSubGraph, node, min);
			}
		}

	}

	private void doStuffLocalSearch(int mainSubGraph) {

		// loop over all selected nodes in the main sub graph.
		for (int node = 0; node < totalCities; node++) {
			int otherSubGraph = mainSubGraph & orBits[node];
			if (otherSubGraph == 0) // check if node 'i' is set , else continue.
				continue;

			otherSubGraph = mainSubGraph & andBits[node]; // reset this bit in the bitMask and use the resultant sub graph.
			double min = Integer.MAX_VALUE;

			int nodesMatched = 0;

			for (int j = 0; j < totalCities; j++) {

				// get the nearest nodes
				Node nearNode = nearestNodes[node][j];
				int previousNode = nearNode.node;

				if (node == previousNode)
					continue;

				// check if this node is set
				int res = otherSubGraph & orBits[previousNode];
				if (res == 0) // skip this bit is not set.
					continue;

				if (nodesMatched == 1)
					break;

				nodesMatched++;

				double val1 = getValueASubGraphk(otherSubGraph, previousNode);
				double val2 = getDistance(node, previousNode);

				double val = val1 + val2;
				min = Math.min(min, val);

				putValueAGraphK(mainSubGraph, node, min);
			}
		}

	}


	// TODO try to cache this part.
	private double getDistance(int node1, int node2) {
		double xDiff = cityCoordinates[node1][0] - cityCoordinates[node2][0];
		double xDiff2 = xDiff * xDiff;

		double yDiff = cityCoordinates[node1][1] - cityCoordinates[node2][1];
		double yDiff2 = yDiff * yDiff;

		return Math.sqrt(xDiff2 + yDiff2);
	}

	private double getValueASubGraphk(int bitMask, int previousNode) {
		double[] lengths = subGraphMap.get(bitMask);

		if (lengths == null) {
			lengths = new double[totalCities];
			subGraphMap.put(bitMask, lengths);
			fillBaseCaseValues(bitMask, lengths);
		}
		return lengths[previousNode];
	}

	private void fillBaseCaseValues(int bitMask, double[] lengths) {
		if (bitMask == 1)
			lengths[0] = 0;
		else
			lengths[0] = Integer.MAX_VALUE;

		for (int i = 1; i < totalCities; i++) {
			lengths[i] = Integer.MAX_VALUE;
		}
	}

	private void putValueAGraphK(int bitMask, int thisNode, double val) {
		double [] lengths = subGraphMap.get(bitMask);
		if (lengths == null) {
			lengths = new double[totalCities];
			subGraphMap.put(bitMask, lengths);
			fillBaseCaseValues(bitMask, lengths);
		}

		lengths[thisNode] = val;
	}

	Node[][] nearestNodes;

	private void initNearestNodes() {
		nearestNodes = new Node[totalCities][totalCities];

		// fill the nearest nodes.
		for (int i = 0; i < totalCities; i++) {
			for (int j = 0; j < totalCities; j++) {
				double dist = getDistance(i, j);
				Node node = new Node(j, dist);
				nearestNodes[i][j] = node;
			}
		}

		// sort each of the nodes, so that the nearest nodes are nearer.
		for (int i = 0; i < totalCities; i++) {
			Arrays.sort(nearestNodes[i]);
		}
	}

}

class Node implements Comparable<Node>{
	double distance;
	int node;

	public Node(int node, double distance) {
		this.node = node;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		if (this.distance < o.distance)
			return -1;
		else if (this.distance > o.distance)
			return 1;
		else
			return 0;
	}
}
