package codechef.june14.digjump;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	int[] nums;
	int totalDigs = 10;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		String input = in.next();

		char[] chars = input.toCharArray();
		nums = new int[chars.length];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = chars[i] - 48;
		}

		int minMoves;
//		minMoves = getMinimumMoves();
		minMoves = getMinMovesUsingBFS();

		out.println(minMoves);

	}

	// this got failed on rejudge with new test cases  :( :( . . . . it was improper.
	private int getMinimumMoves() {
		int len = nums.length;

		if (len <= 2)
			return len - 1;

		// NEAREST Array is required to help the reverse jump.
		// find the nearest array.
		int[][] nearest = new int[len][totalDigs];
		// initially all are at infinity.
		Arrays.fill(nearest[len - 1], 1000_000);

		// mark the last element.
		int lastNum = nums[len - 1];
		nearest[len-1][lastNum] = 0;

		for (int i = len - 2; i >= 0; i--) {
			for (int j = totalDigs - 1; j >= 0; j--) {
				nearest[i][j] = nearest[i + 1][j] + 1;
			}
			// mark this number as 0;
			nearest[i][nums[i]] = 0;
		}

		int[] costArray = new int[len];
		int[][] minCostArray = new int[len][totalDigs];

		// init array.
		Arrays.fill(minCostArray[len - 1], 1000_000);
		minCostArray[len - 1][lastNum] = 0;
		costArray[len - 1] = 0;

		// start filling from back to front.
		for (int i = len - 2; i >= 0; i--) {
			// copy previous values from min_cost
			for (int num = 0; num < totalDigs; num++) {
				minCostArray[i][num] = minCostArray[i + 1][num];
			}

			// update the cost
			int presentNum = nums[i];
			costArray[i] = Math.min(1 + costArray[i + 1], // jump next
									1 + minCostArray[i + 1][presentNum] // jump to same number ahead.
									);

			// update the min cost for this number
			minCostArray[i][presentNum] = Math.min(minCostArray[i][presentNum],
													costArray[i]);

			// if a reverse jump is beneficial, try to correct the previous digits. (we got only ten).
			// ERROR this part is incorrect...once we correct minCostArray , we need to correct costArray
			// and then minCost array...it gets ugly...
			// example check this input. . . 16663336663339455885588147770077009 the answer should be 5
			if (costArray[i] <= costArray[i + 1]) {
				for (int num = 0; num < totalDigs; num++) {
					minCostArray[i][num] = Math.min(minCostArray[i][num],
													costArray[i] + nearest[i][num]);
				}
			}
		}

		return costArray[0];
	}

	// Finally passed... this was again nice attempt.
	// converted the problem into a graph problem and solved it. BFS to find shortest path for unweighted graph.
	private int getMinMovesUsingBFS() {
		if (nums.length <= 2)
			return nums.length - 1;

		// array to store the positions of various nos.
		List<Integer>[] numsIndexes = new ArrayList[totalDigs];
		for (int i = 0; i < totalDigs; i++) {
			numsIndexes[i] = new ArrayList<>();
		}
		// store the positions.
		for (int idx = 0; idx < nums.length; idx++) {
			numsIndexes[nums[idx]].add(idx);
		}
		// to check if already visited all indexes of this number.
		boolean[] doneWithThisNum = new boolean[totalDigs];

		boolean[] visited = new boolean[nums.length + 1];

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(0);
		visited[0] = true;

		int[] dist = new int[nums.length];
		dist[0] = 0;

		// start the breadth first search #BFS
		done:
		while (!queue.isEmpty()) {
			int numIdx = queue.poll();
			int num = nums[numIdx];

			// get all neighbours.
			List<Integer> neighbours = new ArrayList<>();
			if (numIdx == 0) {
				neighbours.add(numIdx + 1);
			}
			else if (numIdx == nums.length - 1) {
				neighbours.add(numIdx - 1);
			}
			else {
				neighbours.add(numIdx - 1);
				neighbours.add(numIdx + 1);
			}
			// don't add the neighbours repeated neighbours again and again... this is what makes it efficient.
			if (!doneWithThisNum[num]) {
				neighbours.addAll(numsIndexes[num]);
				doneWithThisNum[num] = true;
			}

			for (Integer neighbour : neighbours) {
				if (!visited[neighbour]) {
					visited[neighbour] = true;
					dist[neighbour] = dist[numIdx] + 1;

					if (neighbour == nums.length - 1) {
						break done; // we reached end, so done.
					}
					queue.add(neighbour);
				}
			}
		}

		return dist[nums.length - 1];
	}


}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
