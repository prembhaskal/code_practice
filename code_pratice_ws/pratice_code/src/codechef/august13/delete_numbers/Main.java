package codechef.august13.delete_numbers;

import java.io.*;
import java.util.Map;
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

	int size;
	int[] nums;

	int moves;
	int[] varray;
	int[] tarray;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		size = in.nextInt();
		nums = new int[size];

		for (int i = 0; i < size; i++) {
			nums[i] = in.nextInt();
		}

		// find the moves
		findMinMovesNaive();

		// print the moves;
		out.println(moves);

		for (int i = 0; i < moves; i++) {
			out.print(varray[i] + " ");
			out.println(tarray[i]);
		}

	}

	// just keep deleting the numbers from end. THIS IS STUPID METHOD TO DO. but still fetches you marks. ;) ;)
	private void findMinMovesNaive() {
		moves = size;

		varray = new int[moves];
		tarray = new int[moves];

		for (int i = moves; i > 0; i--) {
			varray[moves-i] = i;
			tarray[moves-i] = i;
		}
	}

	// store the indexes (in reverse order) of elements.
	private void findMinMoves() {
		// array to store that the element has been taken care of.
		boolean[] numDeletedArray = new boolean[size];

		// map of indexes for each number
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
