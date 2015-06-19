package codechef.august14.seashuf;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

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

	private int N; // array len
	private long[] nums;

	private LinkedList<Long> A; // 1st part
	private long sumA;
	private LinkedList<Long> B; // 2nd part.
	private long sumB;

	private int totalMoves;
	private int[][] moves;

	private int totalShifts;
	private int maxShifts;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();

		nums = new long[N];

		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		naiveMethodFindMoves();

		preCheck();

		out.println(totalMoves);
		for (int i = 0; i < totalMoves; i++) {
			out.println(moves[i][0] + " " + moves[i][1]);
		}
	}

	private void preCheck() {
		if (totalMoves == 0) // check added to see it we are getting 0 moves for one of the test case...WE ARE INFACT.
			throw new RuntimeException("ERROR");

		// count moves, shifts
		int sum = 0;
		for (int i = 0; i < totalMoves; i++) {
			sum += (moves[i][1] - moves[i][0] + 1);
		}
		if (sum > 2 * N)
			throw new RuntimeException("ERROR");
	}

	private void naiveMethodFindMoves() {
		totalMoves = 0;
		maxShifts = 2 * N;
		moves = new int[10000][2]; // init moves to big number.

		// init linked lists.
		A = new LinkedList<>();
		B = new LinkedList<>();

		// first list, push items into it.
		for (int i = 0; i < N / 2; i++) {
			A.push(nums[i]);
		}

		// second list, add items so that middle element is at top.
		for (int i = N -1 ; i >= N / 2; i--) {
			B.push(nums[i]);
		}

		// initialize sums.
		sumA = 0;
		for (int i = 0; i < N / 2; i++) {
			sumA += nums[i];
		}

		sumB = 0;
		for (int i = N / 2; i < N ; i++) {
			sumB += nums[i];
		}

		long INIT = Math.abs(sumA - sumB);

		while (true) {
			if (sumA > sumB) {
				// find x in A, such that (x > B[0]) and (x-B[0]) < INIT.
				long B0 = B.peek();
				Iterator<Long> iteratorA = A.iterator();
				long x = -1;
				int i = 0;
				boolean found = false;
				while (iteratorA.hasNext()) {
					x = iteratorA.next();
					if (x > B0 && (x - B0) < INIT) {
						iteratorA.remove();
						found = true;
						break;
					}
					i++;
				}

				if (found) {
					// push the B0 in A;
					A.push(B.poll());
					B.push(x);

					totalShifts += (i + 2);
					if (totalShifts > maxShifts)
						break;
					// store this move.
					moves[totalMoves] = new int[]{N/2 - i, N/2 + 1};
					// increase moves
					totalMoves++;


					// update sums
					sumA = (sumA - x + B0);
					sumB = (sumB - B0 + x);
				}
				else { // try to find y in B, such that y < A0 && (A0 - y) < INIT.

					// we CANNOT reverse shift, so first we need to swap (B0 with x) if they are different.
					Iterator<Long> iteratorB = B.iterator();
					long A0 = A.peek();
					int j = 0;
					long y = -1;
					while (iteratorB.hasNext()) {
						y = iteratorB.next();
						if (y < A0 && (A0 - y) < INIT) {
							iteratorB.remove();
							found = true;
							break;
						}
						j++;
					}

					if (!found)
						break; // we cannot optimise more.

					// swap B0 with y and then shift.SINCE WE CANNOT REVERSE SHIFT
//					B.push(y);

					totalShifts += ( j + 1);
					if (totalShifts > maxShifts)
						break;

					// note this move
					moves[totalMoves] = new int[]{N/2 + 1, N/2 + 1 + j};
					totalMoves++;

					// push the A0 in B;
					B.push(A.poll());
					A.push(y);

					totalShifts += (2);
					if (totalShifts > maxShifts)
						break;

					// store this move.
					moves[totalMoves] = new int[]{N/2, N/2 + 1};
					// increase moves
					totalMoves++;


					// update sums
					sumA = (sumA + y - A0);
					sumB = (sumB + A0 - y);
				}

				long newINIT = Math.abs(sumA - sumB);
				if (newINIT >= INIT)
					throw new RuntimeException("ERROR");

				INIT = newINIT;
			}
			else if (sumA < sumB ) {
				// find x in A, such that (x < B[0]) and (B[0] - x) < INIT.
				long B0 = B.peek();

				Iterator<Long> iteratorA = A.iterator();
				long x = 0;
				int i = 0;
				boolean found = false;
				while (iteratorA.hasNext()) {
					x = iteratorA.next();
					if (x < B0 && (B0 - x) < INIT) {
						iteratorA.remove();
						found = true;
						break;
					}
					i++;
				}

				if (found) {
					// push the B0 in A;
					A.push(B.poll());
					B.push(x);

					totalShifts += (i + 2);
					if (totalShifts > maxShifts)
						break;

					// store this move.
					moves[totalMoves] = new int[]{N/2 - i, N/2 + 1};
					// increase moves
					totalMoves++;


					// update sums
					sumA = (sumA - x + B0);
					sumB = (sumB - B0 + x);
				}

				else { // try to find y in B, such that y < A0 && (A0 - y) < INIT.
					Iterator<Long> iteratorB = B.iterator();
					long A0 = A.peek();
					int j = 0;
					long y = -1;
					while (iteratorB.hasNext()) {
						y = iteratorB.next();
						if (y > A0 && (y - A0) < INIT) {
							iteratorB.remove();
							found = true;
							break;
						}
						j++;
					}

					if (!found)
						break; // we cannot find more optimal answer

					// swap B0 with y, SINCE WE CANNOT REVERSE SHIFT so we swap and shift.
//					B.push(y);
					totalShifts += ( j + 1);
					if (totalShifts > maxShifts)
						break;

					// note this move
					moves[totalMoves] = new int[]{N/2 + 1, N/2 + 1 + j};
					totalMoves++;

					// push the A0 in B;
					B.push(A.poll());
					A.push(y);

					totalShifts += (2);
					if (totalShifts > maxShifts)
						break;
					// store this move.
					moves[totalMoves] = new int[]{N/2, N/2 + 1};
					// increase moves
					totalMoves++;


					// update sums
					sumA = (sumA + y - A0);
					sumB = (sumB + A0 - y);
				}

				long newINIT = Math.abs(sumA - sumB);
				if (newINIT >= INIT)
					throw new RuntimeException("ERROR");

				INIT = newINIT;
			}
			else
				break;

			// got accepted when it is 20, fails for higher moves, also gives lesser points ... Fails ok
			// but less points NOT SURE WHY??
			if (totalMoves > 20)
				break;

		}
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
