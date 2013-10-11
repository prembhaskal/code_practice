package coursera.algo2.week5;

import common.util.InputReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TravellingSalesMan1 {

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

	Map<Set<Integer>, double[]> subGraphMap;

	private double getMinTotalTravelPeriod() {
		subGraphMap = new HashMap<>();

		int[] nodes = new int[totalCities];

		// just number the cities.
		for (int i = 0; i < totalCities; i++) {
			nodes[i] = i;
		}

		// put the base case values first.
		initBaseCases();

		// iterate for all subgraph sizes from 2 to n
		for (int size = 2; size <= totalCities; size++) {
			boolean[] mask = new boolean[totalCities];
			findforSubGraphs(nodes, mask, 0, 0, size);
		}

		// get the minimum from the full graph.

		Set<Integer> fullGraph = new HashSet<>();
		for (int i = 0; i < totalCities; i++) {
			fullGraph.add(i);
		}

		double[] lengths = subGraphMap.get(fullGraph);
		double min = Integer.MAX_VALUE;
		for (int i = 1; i < totalCities; i++) {
			double val1 = lengths[i];
			double val2 = getDistance(i, 0);
			min = Math.min(min, val1 + val2);
		}

		return min;
	}

	private void initBaseCases() {
		Set<Integer> baseNode = new HashSet<>();
		baseNode.add(0);
	}

	private void findforSubGraphs(int[] nodes, boolean[] mask, int idx, int size, int maxSize) {
		// stop early, if we cannot meet the maxSize requirement.
		int remElements = nodes.length - idx;
		if (size + remElements < maxSize)
			return;

		if (size == maxSize) {
			doStuff(nodes, mask);
			return;
		}

		if (idx == nodes.length)
			return; // no more elements to select from.

		if (idx == 0) {
			// always choose the first node.
			mask[idx] = true;
			findforSubGraphs(nodes, mask, idx + 1, size + 1, maxSize);
		}
		else {
			// choose this number.
			mask[idx] = true;
			findforSubGraphs(nodes, mask, idx + 1, size + 1, maxSize);

			// don't choose this number.
			mask[idx] = false;
			findforSubGraphs(nodes, mask, idx + 1, size, maxSize);
		}
	}

	// loop over the the possible sub-graphs and find the minimum of those here.
	private void doStuff(int[] nums, boolean[] mask) {
		Set<Integer> mainSubGraph = new HashSet<>();

		// make the main sub graph first.
		for (int i = 0; i < nums.length; i++) {
			if (mask[i])
				mainSubGraph.add(i);
		}

		for (int node : mainSubGraph) {
			Set<Integer> otherSubGraph = new HashSet<>(mainSubGraph);
			otherSubGraph.remove(node);

			double min = Integer.MAX_VALUE;

			for (int previousNode : mainSubGraph) {
				if (previousNode == node)
					continue;

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

	private double getValueASubGraphk(Set<Integer> subGraph, int previousNode) {
		double[] lengths = subGraphMap.get(subGraph);

		if (lengths == null) {
			lengths = new double[totalCities];
			subGraphMap.put(subGraph, lengths);
			fillBaseCaseValues(subGraph, lengths);
		}
		return lengths[previousNode];
	}

	private void fillBaseCaseValues(Set<Integer> subGraph, double[] lengths) {
		if (subGraph.contains(0) && subGraph.size()==1)
			lengths[0] = 0;
		else
			lengths[0] = Integer.MAX_VALUE;

			for (int i = 1; i < totalCities; i++) {
				lengths[i] = Integer.MAX_VALUE;
			}
	}

	private void putValueAGraphK(Set<Integer> graph, int thisNode, double val) {
		double [] lengths = subGraphMap.get(graph);
		if (lengths == null) {
			lengths = new double[totalCities];
			subGraphMap.put(graph, lengths);
			fillBaseCaseValues(graph, lengths);
		}

		lengths[thisNode] = val;
	}
}
