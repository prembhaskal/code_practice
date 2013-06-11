package topcoder.srm.s150.div1;

import java.util.*;

public class RoboCourier {
	public Graph graph;

	int[] xDir = new int[]{0, 1, 1, 0,-1,-1};
	int[] yDir = new int[]{2, 1,-1,-2,-1, 1};

	public int timeToDeliver(String[] path) {
		int time = 0;

		createGraph(path);

		printGraph();

		return time;
	}

	private void printGraph() {
		Map<Node, Set<Node>> adjacencyList = graph.adjacencyList;

		int i=0;
		for (Map.Entry<Node, Set<Node>> nodeSetEntry : adjacencyList.entrySet()) {
			System.out.println("node number --> " + i++);
			Node node = nodeSetEntry.getKey();
			System.out.println("node co-ordinates x -->" + node.x + " -->" + node.y);
		}
	}

	// logic copied from editorial, but not the direction array. (it is different in editorial)
	private void createGraph(String[] path) {
		graph = new Graph();

		for (String scoutPath : path) {
			int x, y, dir;
			x = y = dir = 0;
			char[] pathArray = scoutPath.toCharArray();

			Node fromNode = new Node(x,y);

			for (char ch : pathArray) {
				switch (ch) {
					case 'R':
						dir = (dir + 1)%6;
						break;
					case 'L':
						dir = (dir + 5)%6; // dir = (dir - 1 + 6)%6;(add mod when adding negative nos.)
						break;
					case 'F':
						// move the scout to next node.
						x += xDir[dir];
						y += yDir[dir];

						Node toNode = new Node(x,y);
						graph.addNeighbour(fromNode, toNode);
						// present node is now the new fromNode;
						fromNode = toNode;
				}
			}
		}
	}

}

class Graph {
	Map<Node, Set<Node>> adjacencyList = new HashMap<>();

	public void addNeighbour(Node fromNode, Node toNode) {
		addNodeToGraph(fromNode);
		addNodeToGraph(toNode);

		Set<Node> nodeList = adjacencyList.get(fromNode);
		nodeList.add(toNode);
	}

	private void addNodeToGraph(Node node) {
		Set<Node> nodeList= adjacencyList.get(node);

		if (nodeList==null) {
			adjacencyList.put(node, new HashSet<Node>());
		}
	}
}

// node has the co-ordinate corresponding to the hexagonal system.
class Node {
	int x;
	int y;
	// time variable to compare, init to max
	int timeToReach = Integer.MAX_VALUE;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;

		Node node = (Node) o;

		if (x != node.x) return false;
		if (y != node.y) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}
}
