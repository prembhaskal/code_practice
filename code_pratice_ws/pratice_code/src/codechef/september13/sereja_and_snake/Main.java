package codechef.september13.sereja_and_snake;

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

	int rows;
	int cols;

	Position[] apples;

	int[] moves;
	int totalMoves;

	int MAX_MOVES = 900000;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		rows = in.nextInt();
		cols = in.nextInt();

		apples = new Position[rows*cols-1];
		for (int i = 0; i < rows*cols-1; i++) {
			int xpos = in.nextInt();
			int ypos = in.nextInt();

			Position position = new Position(xpos, ypos);
			apples[i] = position;
		}

		findTheMovesNaiveMethod();

		out.println(totalMoves);
		for (int i=0;i<totalMoves-1;i++) {
			out.print(moves[i]);
			out.print(" ");
		}
		out.println(moves[totalMoves-1]);

	}

	// top-left --> 0,0   bottom-right --> M-1,N-1
	// 0 --> move up
	// 1 --> move down
	// 2 --> move left
	// 3 --> move right
	private void findTheMovesNaiveMethod() {
		moves = new int[MAX_MOVES];

		int headXPos = 0;
		int headYPos = 0;

		int direction = 3; // we move to right at beginning.

		for (int i=0;i<apples.length;i++) {
			Position destination = apples[i];

			// do until we reach the apple.
			while (!(headXPos == destination.xpos && headYPos == destination.ypos)) {
				if (direction==3) { // we were moving right.
					if (headYPos < cols-1) { // move to right if there is a place
						headYPos++;
						moves[totalMoves++] = 3;
					} else {
						headXPos = (headXPos+1)%rows; // move down (or up if we cross boundary)
						moves[totalMoves++] = 1; // since we moved down. (modularly)
						direction = 2; // change direction, we move left now.
					}
				}

				else { // we were moving left
					if (headYPos > 0) {
						headYPos--;
						moves[totalMoves++] = 2;
					} else {
						headXPos = (headXPos+1)%rows;
						moves[totalMoves++] = 1; // we moved down.
						direction = 3; // we move right now.
					}
				}
			}
		}
	}


	// number when each cell is numbered from 0 to N*M-1;
	private int getEquivalentNumber(int xpos, int ypos) {
		return (xpos*cols + ypos);
	}


}

class Position {
	int xpos;
	int ypos;

	public Position(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
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
