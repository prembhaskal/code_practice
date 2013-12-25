package codechef.archives.may13.name_reduction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String A = in.next();
			String B = in.next();

			int childs = in.nextInt();
			List<String> childNames = new ArrayList<String>();

			for (int child = 0;child<childs;child++) {
				String str = in.next();
				childNames.add(str);
			}

			boolean possible = isNamePossible(A, B, childNames);

			if (possible)
				out.println("YES");
			else
				out.println("NO");
		}

	}

	public boolean isNamePossible(String A, String B, List<String> childNames) {
		String completeName = A + B;

		int[] freq = new int[26];
		int base = (int)'a';
		int value;

		char[] nameArray = completeName.toCharArray();
		// get all the frequencies
		for (char ch : nameArray) {
			value = (int)ch - base;
			freq[value]++;
		}

		for (String name : childNames) {
			char[] childNameArr = name.toCharArray();

			for (char ch : childNameArr) {
				value = ch - base;

				freq[value]--;

				if (freq[value]<0) {
					return false;
				}
			}
		}

		return true;
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
