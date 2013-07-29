package topcoder.srm.s150.div1;

import java.io.PrintWriter;
import java.util.*;

public class RoboCourier {
	public Graph graph;

	int[] xDir = new int[]{0, 1, 1, 0,-1,-1};
	int[] yDir = new int[]{2, 1,-1,-2,-1, 1};

	public int timeToDeliver(String[] path, PrintWriter out) {
		int time = 0;

		createGraph(path);

//		printGraph();

		traverseWholeGraph();

		printForGraphViz(out);

		return time;
	}


	// starting from Node ZERO, what should be the INITIAL direction of the nodes?
	private void traverseWholeGraph() {
		// start from node 0
		Node nodeZero = graph.indexVsNode.get(0);
		nodeZero.timeToReach = 0;

		for (Node neighbour : graph.adjacencyList.get(nodeZero)) {
			int direction = getDirection(nodeZero, neighbour);
			traverseGraph(neighbour, 4, direction);
		}

	}

	// Traverse graph
	/*
	try to write logic to traverse graph without taking into account the inertia things.
	like assume moving take 4 unit of time, turning takes 3 of time.

	traverse(node, time, origDir) {
		if (node.time < time ) RETURN

	node.time = time;

	for (neighbour : node.getNeightBour) {
		newDir = getDirection(node, neighbour);

		dirDiff = ABS(newDir - origDir);

		newTime = time + dirDiff*3 + 4;

		traverse(neighbour, newtime, newDir);
	}

	}

	then change it to accommodate the stopping , starting and running in same directions stuff.s
	 */

	private void traverseGraph(Node node, int time, int oldDirection) {
		if(node.timeToReach < time) {
			return; // node already has a shorter path to reach.
		}

		node.timeToReach = time;

		for (Node neighbour : graph.adjacencyList.get(node)) {
			int newDirection = getDirection(node, neighbour);
			int directionDiff = getDirectionDiff(oldDirection, newDirection);
			int newTime = time + 4 + directionDiff*3;
			traverseGraph(neighbour, newTime, newDirection);
		}
	}

	private int getDirectionDiff(int oldDir, int newDir) {
		int dirDiff1 = Math.abs(newDir - oldDir);
		int dirDiff2 = 6 - dirDiff1;

		return Math.min(dirDiff1, dirDiff2);
	}

	private int getDirection(Node node, Node neighbour) {
		for (int i = 0; i < 6; i++) {
			int x = node.x + xDir[i];
			int y = node.y + yDir[i];

			if (neighbour.x==x && neighbour.y==y) {
				return i;
			}
		}

		throw new RuntimeException("the both nodes are not neighbour of each other");
	}

	private void printGraph() {
		Map<Node, Set<Node>> adjacencyList = graph.adjacencyList;

		List<Node> nodeList = graph.nodeList;

		for (Node node : nodeList) {
			System.out.println("node no. --> " + graph.nodeVsIndex.get(node));
			System.out.println("co-ordinates are x --> " + node.x + " y --> " + node.y);

			System.out.println("neighbours of the node are ");
			for (Node neighbour : adjacencyList.get(node)) {
				System.out.print(graph.nodeVsIndex.get(neighbour) + ", ");
			}
			System.out.println("");
		}
	}

	private void printForGraphViz(PrintWriter out) {

		out.println("strict graph E {");
		// print all the nodes and the positions.
		for (Node node : graph.nodeList) {
			int nodeNo = graph.nodeVsIndex.get(node);
			out.println("\"" + nodeNo + "\"" + " [label=\"" + nodeNo + " - time = " + node.timeToReach + " \" pos=\"" + (node.x * 50) + "," + (node.y * 50) + "!\"]");
		}

		// print all the edges
		for (Node node : graph.nodeList) {
			int fromIdx = graph.nodeVsIndex.get(node);

			for (Node neighbour : graph.adjacencyList.get(node)) {
				int toIdx = graph.nodeVsIndex.get(neighbour);

				out.println("\"" + fromIdx + "\"--\"" + toIdx + "\"");
			}
		}

		out.println("}");
	}


	// logic copied from editorial, but not the direction array. (it is different in editorial)

	// TODO refactor this crap.
	private void createGraph(String[] path) {
		graph = new Graph();

		int x, y, dir;
		x = y = dir = 0;
		for (String scoutPath : path) {
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
						graph.addNeighbour(toNode, fromNode);

						graph.addNodeToList(fromNode);
						graph.addNodeToList(toNode);

						// present node is now the new fromNode;
						fromNode = toNode;
				}
			}
		}

		// complete the graph.

//		for (Node fromNode : graph.nodeList) {
//			for (int i=0;i<6;i++) {
//				int newX = fromNode.x + xDir[i];
//				int newY = fromNode.y + yDir[i];
//
//				Integer idx = graph.nodeVsIndex.get(new Node(newX, newY));
//				if (idx != null) {
//					// retrieve the actual object.
//					Node toNode = graph.indexVsNode.get(idx);
//
//					graph.addNeighbour(fromNode, toNode);
//				}
//			}
//		}
	}

}

class Graph {
	List<Node> nodeList = new ArrayList<>();
	Set<Node> nodeSet = new HashSet<>();

	Map<Integer, Node> indexVsNode = new HashMap<>();
	Map<Node, Integer> nodeVsIndex = new HashMap<>();
	Map<Node, Set<Node>> adjacencyList = new HashMap<>();

	public void addNeighbour(Node fromNode, Node toNode) {
		int frIdx = addNodeToList(fromNode);
		int toIdx = addNodeToList(toNode);

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

	public int addNodeToList(Node node) {
		Integer idx = nodeVsIndex.get(node);
		if (idx==null) {
			nodeList.add(node);
			nodeSet.add(node);
			idx = nodeList.size()-1;
			nodeVsIndex.put(node, idx);
			indexVsNode.put(idx, node);
		}

		return idx;
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
