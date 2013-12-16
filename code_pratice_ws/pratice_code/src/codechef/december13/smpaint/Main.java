package codechef.december13.smpaint;

import java.io.*;
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
	int[][] actualImage;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		size = in.nextInt();
		actualImage = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				actualImage[i][j] = in.nextInt();
			}
		}

		getInstToDrawImage();

		//print actual total instructions, note that it is 1 based.
		out.println(totalIns);
		for (int i = 0; i < totalIns; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 4; j++) {
				sb.append(actualInstructions[i][j]);
				sb.append(" ");
			}
			sb.append(actualInstructions[i][4]);

			out.println(sb.toString());
		}
	}

	// the index for the image is 1 based.

	int[][] actualInstructions;
	int totalIns;

	private void getInstToDrawImage() {
		// init the instructions.
		actualInstructions = new int[size * size][5];

		// now start finding the instructions to draw the image
		getNaiveWayToFind();
	}

	// keep drawing each pixel.
	private void getNaiveWayToFind() {
		totalIns = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int color = actualImage[i][j];

				if (color == 0) // no need to draw if it is zero.
					continue;

				actualInstructions[totalIns][0] = color;
				actualInstructions[totalIns][1] = i + 1;
				actualInstructions[totalIns][2] = i + 1;
				actualInstructions[totalIns][3] = j + 1;
				actualInstructions[totalIns][4] = j + 1;
				totalIns++;
			}
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
