package codechef.august14.cletab;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
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

	private int N; //tables
	private int M; // orders.

	private int C = 400;

	private int[] tableOrders; // index is table no. storing the order.
	private int[] orders;

	private Queue<Integer>[] orderIndexes;

	private int INFINITY = 1000_000;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			M = in.nextInt();

			//
			tableOrders = new int[N + 1]; // 1 based index.
			orders = new int[M];

			for (int j = 0; j < M; j++) {
				orders[j] = in.nextInt();
			}

			int cleans = getMinCleaningGreedy();
			out.println(cleans);
		}

	}

	// this is accepted... greedy similar to NRU used in OS.
	// TODO try to remove the iteration part by a priority queue or something like that.
	private int getMinCleaningGreedy() {
		// tableOrders has initially 0 means all are unoccupied.

		// each queue will store the index of the orders in ascending order.
		orderIndexes = new Queue[C + 1]; // actual orders can have values till C;
		// fill each with empty.
		for (int i = 0; i < orderIndexes.length; i++) {
			orderIndexes[i] = new ArrayDeque<>();
		}

		// fill the orderIndexes based on index.
		for (int idx = 0; idx < orders.length; idx++) {
			int order = orders[idx];
			orderIndexes[order].add(idx);
		}

		// fill each of the orderIndexes with a large value to indicate that no further occurrence would be present.
		for (int i = 0; i < orderIndexes.length; i++) {
			orderIndexes[i].add(INFINITY);
		}

		int cleanRequired = 0;

		for (int i = 0; i < M; i++) {

			int presentOrder = orders[i];
			// for each order, try to find a table which has same order as this.
			// else try to find a table which is empty.
			// else find a table with a order which is the most distant ( index).

			int tableIdx;

			// check same order
			tableIdx = getSameOrderTable(presentOrder);
			if (tableIdx != -1) {
				removePresentOrderFromIndexesLookUp(presentOrder);
				continue;
			}

			// check empty table.
			tableIdx = getEmptyTable();
			if (tableIdx != -1) {
				tableOrders[tableIdx] = presentOrder;
				removePresentOrderFromIndexesLookUp(presentOrder);
				cleanRequired++;
				continue;
			}

			// else get the table with most distant order
			tableIdx = getMostDistantOrderTable();
			tableOrders[tableIdx] = presentOrder;
			removePresentOrderFromIndexesLookUp(presentOrder);
			cleanRequired++;
		}

		return cleanRequired;
	}

	private void removePresentOrderFromIndexesLookUp(int presentOrder) {
		Queue<Integer> indexQueue = orderIndexes[presentOrder];
		indexQueue.poll();
	}

	private int getSameOrderTable(int presentOrder) {
		for (int i = 1; i < N + 1; i++) {
			if (tableOrders[i] == presentOrder)
				return i;
		}
		return -1;
	}

	private int getMostDistantOrderTable() {
		int maxIdx = -1;
		int maxTable = -1;

		for (int i = 1; i < N + 1; i++) {
			int orderOnTable = tableOrders[i];
			Queue<Integer> nextIndex = orderIndexes[orderOnTable];
			Integer idx = nextIndex.peek();
			if (idx > maxIdx) {
				maxIdx = idx;
				maxTable = i;
			}
		}

		return maxTable;
	}

	private int getEmptyTable() {
		// check for empty table
		for (int j = 1; j < N + 1; j++) {
			if (tableOrders[j] == 0) {
				return j;
			}
		}

		return -1;
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
