package practice.algorithm;


import common.util.InputReader;

import java.util.*;


/**
 * Note that the in the graph finally provided for Dijkstra, there should not be any negative
 * costs else dijkstra will rotate around that node itself, and never complete.
 *
 *
 * AStar algorithm
 * in Priority Queue, we store the heuristics cost
 * actual distance is tracked separately.
 *
 * 1 catch, in case of some special conditions depending on heuristic function, there might be a
 * need to reconsider the nodes already present in visited set. check wiki and other pages for that.
 */
public class AStarGraphSearch {

	public boolean isDebug = false;
	public int averageAccessCost = 2;

	private int totalNodes;
	private int mapLength;
	private int mapWidth;

	private Set<Position> visitedNodes;
	PriorityQueue<Position> priorityQueue;
	private Position[][] positionGrid;

	private Position[][] previousPosArray;

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

				int passingCost = reader.nextInt();
				if (passingCost == -1) {
					LOG("setting cost infinite for " + i + ":" + j);
					passingCost = Integer.MAX_VALUE;
				}
				positionGrid[i][j].passingCost = passingCost;
			}
		}

		Position fromPosition = positionGrid[x1][y1];
		Position toPosition = positionGrid[x2][y2];

		// update heuristic cost.
		for (int i = 0; i < mapLength; i++) {
			for (int j = 0; j < mapWidth; j++) {
				positionGrid[i][j].heuristicCost = heuristicCostEstimate(positionGrid[i][j], toPosition);
			}
		}

		findShortestPath(fromPosition, toPosition);
	}

	// prints list if there is a path.
	// sends empty list in case of no path.
	public List<Position> findShortestPath(Position fromPosition, Position toPosition){

		priorityQueue = new PriorityQueue<>(11, new HeuristicComparator());
		visitedNodes = new HashSet<>();
		previousPosArray = new Position[mapLength][mapWidth];

		int nodes = 0;
		fromPosition.reachCost = 0;
		LOG("putting previous position reached: " + fromPosition + " from: " + fromPosition);
		previousPosArray[fromPosition.x][fromPosition.y] = fromPosition;

		LOG("adding the node " + fromPosition + " to the queue");
		priorityQueue.add(fromPosition);

		while (nodes <= totalNodes) {
			Position minPos = priorityQueue.poll();
			if (minPos == null) {
				LOG("QUEUE is EMPTY, we CANNOT reach the toPosition.");
				break;
			}

			if (visitedNodes.contains(minPos)) {
				// priority queue can have duplicate nodes. (we are not doing decrease key of the algo.)
				continue;
			}

			LOG("got MINIMUM node position " + minPos);

			if (minPos.equals(toPosition)) {
				reComputePath(fromPosition, minPos);
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
			// check if neighbour is not accessible.
			if (neighbour.passingCost == Integer.MAX_VALUE) {
				LOG("skipping neighbour: " + neighbour + " we cannot pass through it");
				continue;
			}

			int newReachCost = currentPosition.reachCost + neighbour.passingCost;

			// integer overflow protection
			newReachCost = (newReachCost < 0) ? Integer.MAX_VALUE: newReachCost;

			LOG("analysing neighbour: " + neighbour);
			LOG("current position reach cost is " + currentPosition.reachCost);
			LOG("neighbour access cost is " + neighbour.passingCost);
			LOG("initial reach cost for the neighbour is " + neighbour.reachCost);
			LOG("newReachCost for the neighbour is " + newReachCost);
			if (neighbour.reachCost > newReachCost) {
				neighbour.reachCost = newReachCost;
				LOG("adding the neighbour " + neighbour + " to the priority queue");
				priorityQueue.add(neighbour);

				// update the previous node
				LOG("putting previous position reached: " + neighbour + " from: " + currentPosition);
				previousPosArray[neighbour.x][neighbour.y] = currentPosition;
			}
		}
	}

	private void reComputePath(Position fromPosition, Position toPosition) {
		System.out.println("total distance " + toPosition.reachCost);
		printShortestPath(fromPosition, toPosition);
	}

	private int heuristicCostEstimate(Position fromPos, Position toPos) {
		// we use heuristic_distance = totalStep * averageAccessCost;
		int totalSteps = Math.abs(fromPos.x - toPos.x) +
				Math.abs(fromPos.y - toPos.y);
		return totalSteps * averageAccessCost;
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

	private void printShortestPath(Position fromPosition, Position toPosition) {
		System.out.println("Printing Path: ");
		Position prevPosition = toPosition;
		while (prevPosition != null && !prevPosition.equals(fromPosition)) {
			System.out.print(prevPosition + " -- ");
			prevPosition = previousPosArray[prevPosition.x][prevPosition.y];
		}
	}

	private void LOG(String msg) {
		if (isDebug) {
			System.out.println("DEBUG: " + msg);
		}
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
		int heuristicCost = Integer.MAX_VALUE;

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

	private class HeuristicComparator implements Comparator<Position> {

		@Override
		public int compare(Position p1, Position p2) {
			return Integer.compare(p1.reachCost + p1.heuristicCost,
					p2.reachCost + p2.heuristicCost);
		}
	}


}
