package codechef.march14.seasort2;

import java.io.*;
import java.util.Arrays;
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

	private int N;
	private int[] A;

	private int[][] sortSteps;

	private int totalReverses;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		N = in.nextInt();
		A = new int[N];

		sortSteps = new int[N][2]; // N since max for a correct answer is N

		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
		}

//		findSortSteps1();
		findSortSteps2();

		out.println(totalReverses);

		for (int i = 0; i < totalReverses; i++) {
			out.print(sortSteps[i][0]);
			out.print(" ");
			out.println(sortSteps[i][1]);
		}
	}

	// this works good. :) :)
	private void findSortSteps2() {
		// sort the array and try to place each element at its required position by reversing
		// from required position to element's actual position.

		totalReverses = 0;

		int[] copy = Arrays.copyOf(A, A.length);
		Arrays.sort(copy);

		// try to place each element.
		for (int i = 0; i < copy.length; i++) {
			int num = copy[i];

			int actualElementPosition = getElementPosition(num, i);
			if (actualElementPosition == i)
				continue; // if element is already present, then continue with the next one.

			updateMovesAndSwap(totalReverses++, i, actualElementPosition);
		}

	}

	private int getElementPosition(int num, int startPosition) {
		for (int i = startPosition; i < A.length; i++) {
			if (A[i] == num)
				return i;
		}

		throw new RuntimeException("should never happen");
	}

	private void findSortSteps1() {

		totalReverses = 0;

		while (true) {
			boolean reverseFound = false;
			int start = 0;
			boolean foundRevPart = false;
			for (int i = 0; i < N - 1; i++) {
				if (A[i] < A[i + 1]) {
					// check if this is an end of previously found rev. part
					if (foundRevPart) {
						updateMovesAndSwap(totalReverses++, start, i);
						foundRevPart = false; // reset found status.
						i--;
						continue;
					}

				}
//				else  commented else to account for the above change.
				else if (A[i] > A[i+1]) {
					if (!foundRevPart) {
						start = i;
						foundRevPart = true;
					}

					reverseFound = true;
				}
			}

			if (foundRevPart) {
				updateMovesAndSwap(totalReverses++, start, N - 1);
			}

			if (!reverseFound)
				break; // we are done as no longer reverse numbers are found.
		}

	}

	private void updateMovesAndSwap(int idx, int left, int right) {
//		System.out.println("left -- " + left + " right -- " + right);
		sortSteps[idx][0] = left + 1; // index is 1 based.
		sortSteps[idx][1] = right + 1;

		reverseArray(left, right);
	}

	private void reverseArray(int start, int end) {
		while (start < end) {
			swap(start, end);
			start++;
			end--;
		}
	}

	private void swap(int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
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
