package codeforces.task.m186.div2.task_c;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			Task solution = new Task();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int size = in.nextInt();

		int[] num  = new int[size];

		for (int i = 0; i < size; i++) {
			num[i] = in.nextInt();
		}

		long maxBeauty = getMaxBeauty(num, size);
		out.println(maxBeauty);

//		long maxBeautyFromEgor = getMaxBeautyByEgor(num, size);
//		out.println(maxBeautyFromEgor);

//		testTimeLimit();
	}

	// this damn method is exceeding time limit :( :(
	// Reason was due to pathological data for sorting Arrays.sort(int[]) uses quick sort hence the issue.
	// solution: shuffle before the sort, it gets accepted in java6 also.
	// :) :) Got accepted when used Java7 as compiler.
	private long getMaxBeauty(int[] num, int size) {
		if (size==1) {
			return num[0];
		}

		List<Integer> numList = arrayToList(num);

		Collections.sort(numList);

		num = listToArray(numList);

		int sizeForItr = 1;
		int mulFactor = getPower(size) + 1;
		int numUsed = 0;
		long beauty = 0;

		for (int i=mulFactor, j=0 ; i>0 ; i--) {
			int sizeForThis = sizeForItr - numUsed;

			long sumForLoop = 0;

			for (int k =0; k < sizeForThis; k++, j++) {
				sumForLoop += num[size-1-j];
				numUsed++;
			}

			beauty = beauty + (mulFactor * sumForLoop);

			mulFactor--;
			sizeForItr *=4;
		}

		return beauty;
	}

	private List<Integer> arrayToList(int[] num) {
		List<Integer> list = new ArrayList<Integer>(num.length);
		for (int i : num)
			list.add(i);

		return list;
	}

	private int[] listToArray(List<Integer> list) {
		int[] num = new int[list.size()];
		int i = 0;
		for (int no : list)
			num[i++] = no;

		return num;
	}

	// COPIED CODE from egor to test the cause of time limit error in java 6
	// this gets accepted only in JAVA 7.
	private long getMaxBeautyByEgor(int[] numbers, int count) {

		Arrays.sort(numbers);
		reverseArray(numbers);

		long[] sum = new long[count + 1];
		for (int i = 0; i < count; i++)
			sum[i + 1] = sum[i] + numbers[i];
		long answer = 0;
		for (int size = 1; size <= count; size *= 4)
			answer += sum[size];

		return answer;
	}

	private void reverseArray(int[] num) {
		for (int i = 0, j=num.length-1; i < num.length / 2; i++,j--) {
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
	}

	private int getPower(int size) {
		int power = 0;
		while (size > 1) {
			size /= 4;
			power++;
		}

		return power;
	}

	// added a test method to test the speed of our algo sans the reading part... this completes in 30millisecs.
	private void testTimeLimit() {
		int[] lotOfnums = new int[262144];

		for (int i = 0; i < lotOfnums.length; i++) {
			lotOfnums[i] = 262144-i;
		}

		long beauty = getMaxBeauty(lotOfnums, lotOfnums.length);

		System.out.println("beauty is " + beauty);
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
