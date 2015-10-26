package spoj.set1.P4TransformExp;

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

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String expression = in.next();
//			String reversePolishNot = getReversePolishNotation(expression);
			String reversePolishNot = getRPNUsingQueue(expression);
			out.println(reversePolishNot);
		}

	}

	private String getReversePolishNotation(String expression) {
		Stack<String> stack = new Stack<String>();
		char[] literals = expression.toCharArray();

		for (int i = 0; i < literals.length; i++) {
			char literal = literals[i];
			if (literal != ')') {
				stack.push(literal + "");
			} else {
				// remove from stack until we get a '('
				String[] strs = new String[3];
				int k = 0;
				String str = null;
				while (true){
					str = stack.pop();
					if (str.equals("("))
						break;
					strs[k++] = str;
				}

				str = strs[2] + strs[0] + strs[1];
				stack.push(str);
			}
		}

		String finalExp = "";
		// get the expression outside
		while (!stack.isEmpty()) {
			finalExp = stack.pop() + finalExp;
		}

		return finalExp;
	}

	// shunting yard algorithm,,,check wikipedia
	// push brackets, operators to stack, push variables to queue
	private String getRPNUsingQueue(String expression) {

		Stack<String> stack = new Stack<String>();
		Queue<String> queue = new LinkedList<>();
		char[] literals = expression.toCharArray();

		for (char literal : literals) {
			if (literal != ')') {
				// if variable, put to queue...we never remove anything from queue, till end
				if (literal >= 'a' && literal <= 'z') {
					queue.add(literal + "");
				}
				else {
					stack.push(literal + "");
				}
			}
			else { // remove until we hit '('
				while (true) {
					String operator = stack.pop();
					if (operator.equals("(")) {
						break;
					}

					queue.add(operator);
				}
			}
		}

		String finalExp = "";
		while (!queue.isEmpty()) {
			finalExp += queue.poll();
		}

		return finalExp;
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
