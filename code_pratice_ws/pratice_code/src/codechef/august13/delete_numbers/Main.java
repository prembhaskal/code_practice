package codechef.august13.delete_numbers;

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

	int maxRange = 100000;

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
//		findMinMovesNaive();
		findMinMoves();

		// print the moves;
		out.println(moves);

		for (int i = 0; i < moves; i++) {
			out.print(varray[i] + " ");
			out.println(tarray[i]);
		}

	}

	// just keep deleting the numbers from end. THIS IS STUPID WAY TO DO. but still fetches you marks. ;) ;)
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
		Map<Integer,List<Integer>> numVsIndexes = new HashMap<>();

		// fill this map by traversing in reverse.
		for (int idx = size-1; idx >=0; idx--) {
			List<Integer> indexes = numVsIndexes.get(nums[idx]);
			if (indexes==null) {
				indexes = new LinkedList<>(); // linked list so that we can remove efficiently.
				numVsIndexes.put(nums[idx], indexes);
			}
			indexes.add(idx+1);
		}

		// start finding the moves, start from the last number
		moves = 0;
		varray = new int[maxRange]; // max range so that we avoid using an arraylist.
		tarray = new int[maxRange];

		for (int i = size-1; i >= 0; i--) {

			if (numDeletedArray[i]) {
				continue;
			}

			int num = nums[i];
			int[] v_and_t = getVAndTArray(numVsIndexes, num);

			int[] newIdxs = getNewIndexesAfterRemovingNumbers(v_and_t[0], v_and_t[1]);
			// store this in max range

			varray[moves] = newIdxs[0];
			tarray[moves] = newIdxs[1];

			// mark these num
			numDeletedArray[v_and_t[0]-1] = true;
			numDeletedArray[v_and_t[1]-1] = true;

			moves++;
		}
	}

	// TODO just implement storing the number of elements on left using Segment Tree.
	/*
	 *dynamic order indices
Once a delete operation is performed, you have to adjust the indices of all the numbers which are on the right of any number which was deleted.
This operation can be performed in O(N) after each delete operation. But, this might be too expensive.
    We can store the positions at the leaves of a segment tree and
    Store the number of items in the left subtree of each internal node.
deletion of a node
Delete Operation can be handled by updating all the O(log N) segments encountered on the way of the search for the number.
Now, any delete operation can be handled in O(log N) time, at the tradeoff of increasing the query time of the order index from O(1) to O(log N).
searching the order index
Since we only perform stabbing queries, we will encounter O(log N) internal nodes.
When going to the right child of a root, we must add up the count of nodes in the left subtree of the root, plus one, to count the root.
A large number of solutions use the following ideas along with data structure to maintain the dynamic order indices
    Store the list of positions that exist for each number in the array.
    Search for the next candidate number to be deleted
The next candidate for deletion may just be the largest AP of positions stored for that number
	 *
	 */
	private int[] getNewIndexesAfterRemovingNumbers(int lowIdx, int highIdx) {
		int[] idxs = new int[2];
		idxs[0] = lowIdx;
		idxs[1] = highIdx;
		return idxs;
	}

	private int[] getVAndTArray(Map<Integer,List<Integer>> numVsIndexes, int num) {
		int[] v_and_t = new int[2];

		List<Integer> indexes = numVsIndexes.get(num);

		if (indexes.size()==1) {
			v_and_t[1] = v_and_t[0] = indexes.get(0);
			return v_and_t;
		} else {
			Iterator<Integer> iterator = indexes.iterator();
			// bigger index will be second
			v_and_t[1] = iterator.next();
			iterator.remove();
			v_and_t[0] = iterator.next();
			iterator.remove();

			return v_and_t;
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
