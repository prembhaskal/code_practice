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

		return 0;

	}

	Map<HashSet<Integer>, double[]> subGraphMap;

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
		for (int size = 2; size < totalCities; size++) {
			boolean[] mask = new boolean[totalCities];
			findforSubGraphs(nodes, mask, 0, 0, size);
		}

		return 0;
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

	private void doStuff(int[] nums, boolean[] mask) {

	}
}
