package coursera.algo2.week2;

import java.util.ArrayList;
import java.util.List;

public class NaiveUnionFind {

	int[] vertexVsLeaders;
	List<Integer>[] clusterVsMembers;

	public NaiveUnionFind(int size) {
		// 1 based storage
		vertexVsLeaders = new int[size+1];
		clusterVsMembers = new ArrayList[size+1];

		initLeadersAndClusters();
	}


	private void initLeadersAndClusters() {
		for (int i = 1; i < vertexVsLeaders.length; i++) {
			vertexVsLeaders[i] = i;
		}

		for (int i = 1; i < clusterVsMembers.length; i++) {
			clusterVsMembers[i] = new ArrayList<>();
			clusterVsMembers[i].add(i);
		}

	}

	public int find(int nodeNum) {
		return vertexVsLeaders[nodeNum];
	}

	public void merge(int node1, int node2) {
		// do not do anything if in same cluster
		int leader1 = find(node1);
		int leader2 = find(node2);

		if (leader1 == leader2)
			return;

		List<Integer> members1 = clusterVsMembers[leader1];
		List<Integer> members2 = clusterVsMembers[leader2];

		if (members1.size() >= members2.size()) {
			assignNewLeader(members1, leader2);
			members2.addAll(members1);
		} else {
			assignNewLeader(members2, leader1);
			members1.addAll(members2);
		}
	}

	private void assignNewLeader(List<Integer> nodes, int leader) {
		for (int node : nodes) {
			vertexVsLeaders[node] = leader;
		}
	}

}
