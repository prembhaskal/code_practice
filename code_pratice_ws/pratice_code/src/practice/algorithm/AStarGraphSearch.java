package practice.algorithm;


import common.util.InputReader;

import java.util.*;

public class AStarGraphSearch {

	public boolean isDebug = false;

	private int totalNodes;
	private int mapLength;
	private int mapWidth;

	private Set<Position> visitedNodes;
	PriorityQueue<Position> priorityQueue;
	private Position[][] positionGrid;

	public void solve(InputReader reader) {
		// length (n) width(m)
		// fromPosition (x1 y1)
		// toPosition (x2 y2)
		// next n lines each have m numbers. on the (Ith_line, Jth_num)= passingCost

		mapLength = reader.nextInt();
		mapWidth = reader.nextInt();

		totalNodes = mapLength * mapWidth;

		int x1 = reader.nextInt();
		int y1 = reader.nextInt();

		int x2 = reader.nextInt();
		int y2 =reader.nextInt();

		positionGrid = new Position[mapLength][mapWidth];
		for (int i = 0; i < mapLength; i++) {
			for (int j = 0; j < mapWidth; j++) {
				positionGrid[i][j] = new Position(i, j);
				positionGrid[i][j].passingCost = reader.nextInt();
			}
		}

		Position fromPosition = positionGrid[x1][y1];
		Position toPosition = positionGrid[x2][y2];

		findShortestPath(fromPosition, toPosition);
	}


	// prints list if there is a path.
	// sends empty list in case of no path.
	public List<Position> findShortestPath(Position fromPosition, Position toPosition){

		priorityQueue = new PriorityQueue<>(11, new PositionComparator());

		visitedNodes = new HashSet<>();

		int nodes = 0;
		fromPosition.reachCost = 0;
		priorityQueue.add(fromPosition);
		LOG("adding the node " + fromPosition + " to the queue");
		visitedNodes.add(fromPosition);

		while (nodes <= totalNodes) {
			Position minPos = priorityQueue.poll();
			LOG("got minimum node position " + minPos);
			if (minPos.equals(toPosition)) {
				reComputePath(minPos);
				break;
			}
			LOG("adding the position: " + minPos + " to the visited nodes");
			visitedNodes.add(minPos);
			updateNeighbours(minPos);
			nodes++;
		}

		return null;
	}

	private void updateNeighbours(Position currentPosition) {
		List<Position> neighbours = getNeighbours(currentPosition, mapLength, mapWidth);
		for (Position neighbour : neighbours) {
			if (visitedNodes.contains(neighbour)) {
				continue;
			}

			int newReachCost = currentPosition.reachCost + neighbour.passingCost;
			LOG("current position reachcost is " + currentPosition.reachCost);
			LOG("neighbour access cost is " + neighbour.passingCost);
			LOG("newReachCost for the neighbour is " + newReachCost);
			LOG("initial reach cost for the neighbour is " + neighbour.reachCost);
			if (neighbour.reachCost > newReachCost) {
				neighbour.reachCost = newReachCost;
				LOG("adding the neighbour " + neighbour + " to the priority queue");
				priorityQueue.add(neighbour);
			}

		}
	}

	private void LOG(String msg) {
		if (isDebug) {
			System.out.println("DEBUG: " + msg);
		}
	}

	private void reComputePath(Position toPosition) {
		System.out.println("total distance " + toPosition.reachCost);
	}

	public List<Position> getNeighbours(Position position, int maxlen, int maxwidth) {
		List<Position> neighbours = new ArrayList<>();

		// get the neighbours from 4 directions.
		int x = position.x;
		int y = position.y;

		if (isPointInBound(x + 1, y, maxlen, maxwidth)) {
			neighbours.add(positionGrid[x + 1][y]);
		}
		if (isPointInBound(x - 1, y, maxlen, maxwidth)) {
			neighbours.add(positionGrid[x - 1][y]);
		}

		if (isPointInBound(x, y - 1, maxlen, maxwidth)) {
			neighbours.add(positionGrid[x][y - 1]);
		}

		if (isPointInBound(x, y + 1, maxlen, maxwidth)) {
			neighbours.add(positionGrid[x][y + 1]);
		}

		LOG("neighbours for position: " + position + " -- " + neighbours);
		return neighbours;
	}


	private boolean isPointInBound(int x, int y, int maxLen, int maxWidth) {
		return (x >= 0 && x < maxLen &&
				y >= 0 && y < maxWidth);
	}



	private class Position {
		int x;
		int y;

		int passingCost = 1;
		int reachCost = Integer.MAX_VALUE;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object other) {
			if (! (other instanceof Position)) return false;
			Position otherPos = (Position) other;
			return x == otherPos.x && y == otherPos.y;
		}

		@Override
		public int hashCode() {
			return x * 31 + y;
		}

		@Override
		public String toString() {
			return "x:" + x + " & y:" + y;
		}
	}

	private class PositionComparator implements Comparator<Position> {

		@Override
		public int compare(Position p1, Position p2) {
			return Integer.compare(p1.reachCost, p2.reachCost);
		}
	}


}
