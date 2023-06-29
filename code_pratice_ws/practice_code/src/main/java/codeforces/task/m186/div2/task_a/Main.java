package codeforces.task.m186.div2.task_a;

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

		int num = in.nextInt();

		int bankData = getBankAccount(num);

		out.println(bankData);
	}

	private int getBankAccount(int num) {
		if (num >= 0)
			return num;

		if (num > -11)
			return 0;

		num = num * (-1);

		char[] numStr = (num + "").toCharArray();

		int len = numStr.length;

		String str1 = "";
		for (int i = 0; i < len-1; i++) {
			str1 = str1 + numStr[i];
		}

		int num1 = Integer.parseInt(str1);
		num1 = num1 * (-1);

		String str2 = "";
		for (int i = 0; i < len; i++) {
			if (i==len-2)
				continue;
			str2 = str2 + numStr[i];
		}

		int num2 = Integer.parseInt(str2);
		num2 = num2 * (-1);

		return Math.max(num1, num2);

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
