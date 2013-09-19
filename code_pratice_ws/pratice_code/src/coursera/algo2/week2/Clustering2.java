package coursera.algo2.week2;

import common.util.InputReader;
import java.util.HashSet;

public class Clustering2 {

	int countOfNodes;
	int bits;
	String[] nodes;

	HashSet<String> lookup = new HashSet<>();

	public int getNoOfClusters(InputReader in) {
		countOfNodes = in.nextInt();
		nodes = new String[countOfNodes+1];
		bits = in.nextInt();

		readGraph(in);
		addInLookup();

	}

	private void addInLookup() {
		for (int i = 1; i < countOfNodes + 1; i++) {
			lookup.add(nodes[i]);
		}
	}

	private void readGraph(InputReader in) {
		for (int i = 0; i < countOfNodes+1; i++) {
			StringBuilder sb = new StringBuilder("");
			for (int j = 0; j < bits; j++) {
				sb.append(in.nextInt());
			}

			nodes[i+1] = sb.toString();
		}
	}
}
