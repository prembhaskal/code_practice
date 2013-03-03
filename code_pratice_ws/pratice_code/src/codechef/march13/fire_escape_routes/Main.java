package codechef.march13.fire_escape_routes;

import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();

		solution.solve(in,writer);

		writer.close();
		in.close();

	}

}

class TaskA {

	Graph graph;
	boolean[] hasFriend;
	int[][] friends;
	int people;

	boolean[] isExplored;

	int[] fireExits;

	int friendGroupSize;

	int MOD = 1000000007;

	int friendGroupIndex;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());

		for (int i=0;i<tests;i++) {
			people = in.nextInt();
			int friendships = in.nextInt();
			readTheGraph(in, friendships);
			formTheGraph();
			findFireExits(out);
			cleanForGarbageCollection();
		}

	}

	private void readTheGraph(Scanner in, int friendships) {
		friends = new int[friendships][2];
		for (int i=0;i<friendships;i++) {
			friends[i][0] = in.nextInt();
			friends[i][1] = in.nextInt();
		}
	}

	private void formTheGraph() {
		hasFriend = new boolean[people+1];
		isExplored = new boolean[people+1];
		List<Integer>[] adjacencyList = new ArrayList[people+1];// 1 based index, hence extra 1
		graph = new Graph(adjacencyList);

		for (int i=0;i<friends.length;i++) {
			graph.addFriend(friends[i][0], friends[i][1]);
			graph.addFriend(friends[i][1], friends[i][0]);// added twice since input file will contain friendship only once
			hasFriend[friends[i][0]] = true;
			hasFriend[friends[i][1]] = true;
		}
	}

	private void cleanForGarbageCollection() {
		for (int i=0;i<graph.adjacencyList.length;i++) {
			graph.adjacencyList[i] = null;
		}
	}

	private void findFireExits(PrintWriter out) {
		fireExits = new int[people];// maximum there can be N fire exits if none is friend to each other.

		friendGroupIndex = 0;
		for (int i=1;i<=people;i++) {
			friendGroupSize = 0; // init for each group
			if (isExplored[i]==false) {
				if (hasFriend[i]==true) {
					findConnectedFriendsStack(i);
					fireExits[friendGroupIndex++] = friendGroupSize;
				} else {
					fireExits[friendGroupIndex++] = 1;
				}
			}
		}

		int leaderWays = getLeaderWays();
		out.println(friendGroupIndex + " " + leaderWays);
	}

	private int getLeaderWays() {
		long ways = 1;

		for (int i=0;i<friendGroupIndex;i++) {
			ways = ( ways * fireExits[i])%MOD;
		}

		return (int) ways;
	}

	private void findConnectedFriendsStack(int node) {
		isExplored[node] = true;
		friendGroupSize++;

		Stack<Integer> stack = new Stack<Integer>();
		for (int friend : graph.adjacencyList[node]) {
			stack.push(friend);
		}

		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			if (isExplored[vertex]== false) {
				isExplored[vertex] = true;
				friendGroupSize++;
			}


			for (int newVertex : graph.adjacencyList[vertex]) {
				if (isExplored[newVertex]==false) {
					isExplored[newVertex] = true;
					stack.push(newVertex);
					friendGroupSize++;
				}
			}
		}

	}



}

class Graph {
	public List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public void addFriend(Integer fromNode, Integer toNode) {
		List<Integer> neighbours = adjacencyList[fromNode];
		if (neighbours==null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[fromNode] = neighbours;
		}
		neighbours.add(toNode);
	}
}

