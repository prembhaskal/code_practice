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

		findMovesBruteForceMethod();

		out.println(totalMoves);
		for (int i=0;i<totalMoves-1;i++) {
			out.print(moves[i]);
			out.print(" ");
		}
		out.println(moves[totalMoves-1]);

	}

	// top-left --> 0,0   bottom-right --> M-1,N-1
	private static int MOVE_UP = 0;// 0 --> move up
	private static int MOVE_DOWN = 1;// 1 --> move down
	private static int MOVE_LEFT = 2;// 2 --> move left
	private static int MOVE_RIGHT = 3;// 3 --> move right

	// WORKING BRUTE FORCE.
	private void findMovesBruteForceMethod() {
		moves = new int[MAX_MOVES];

		int headXPos = 0;
		int headYPos = 0;

		// single column, keep moving down in circle
		if (cols==1) {
			for (Position destination : apples) {

				while (!(destination.xpos==headXPos && destination.ypos==headYPos)) {
					headXPos = (headXPos+1)%rows;
					moves[totalMoves++] = MOVE_DOWN;
				}
			}
		}
		// single row, keep moving right in circle
		 else if (rows==1) {
			for (Position destination : apples) {
				while (!(destination.xpos==headXPos && destination.ypos==headYPos)) {
					headYPos = (headYPos+1)%cols;
					moves[totalMoves++] = MOVE_RIGHT;
				}
			}
		}
		// we reach back to the origin by climbing up, if columns are even.
		else if (cols%2==0) {
			int direction = MOVE_RIGHT;
			for (Position destination : apples) {
				while (!(destination.xpos==headXPos && destination.ypos == headYPos)) {
					// if we are on the top row
					if (headXPos==0) {
						// see if we can move right
						if (headYPos != cols-1) {
							headYPos++;
							direction = moves[totalMoves++] = MOVE_RIGHT;
						} else { // else move down, if we cannot move right anymore
							headXPos++;
							direction = moves[totalMoves++] = MOVE_DOWN;
						}
					}
					// LAST COLUMN if we have reached back the first column, keep moving up (without worrying much)
					else if (headYPos==0) {
						headXPos--;
						direction = moves[totalMoves++] = MOVE_UP;
					}

					 else {
						// if we are moving down, check if we reached last row, turn LEFT
						if (direction==MOVE_DOWN && headXPos==rows-1) {
							headYPos--;
							moves[totalMoves++] = MOVE_LEFT;
							direction = MOVE_UP; // reverse direction.
						}
						// if we are moving up, check if we reached 2nd row (no == 1) turn RIGHT
						else if (direction==MOVE_UP && headXPos==1) {
							headYPos--;
							moves[totalMoves++] = MOVE_LEFT;
							direction = MOVE_DOWN; // reverse direction.
						}
						// else keep doing what we were doing.
						else if (direction==MOVE_DOWN) {
							headXPos++;
							direction = moves[totalMoves++] = MOVE_DOWN;
						} else {
							headXPos--;
							direction = moves[totalMoves++] = MOVE_UP;
						}
					}
				}
			}
		}
		// we reach origin by circling if columns are odd.
		else {
			int direction = MOVE_RIGHT;
			for (Position destination : apples) {
				while (!(destination.xpos==headXPos && destination.ypos == headYPos)) {
					// if we are on the top row
					if (headXPos==0) {
						// see if we can move right
						if (headYPos != cols-1) {
							headYPos++;
							direction = moves[totalMoves++] = MOVE_RIGHT;
						} else { // else move down, if we cannot move right anymore
							headXPos++;
							direction = moves[totalMoves++] = MOVE_DOWN;
						}
					}
					// LAST COLUMN if we have reached back the first column, keep moving down (modular circle)
					else if (headYPos==0) {
						headXPos = (headXPos + 1) % rows;
						direction = moves[totalMoves++] = MOVE_DOWN;
					}

					else {
						// if we are moving down, check if we reached last row, turn LEFT
						if (direction==MOVE_DOWN && headXPos==rows-1) {
							headYPos--;
							moves[totalMoves++] = MOVE_LEFT;
							direction = MOVE_UP; // reverse direction.
						}
						// if we are moving up, check if we reached 2nd row (no == 1) turn RIGHT
						else if (direction==MOVE_UP && headXPos==1) {
							headYPos--;
							moves[totalMoves++] = MOVE_LEFT;
							direction = MOVE_DOWN; // reverse direction.
						}
						// else keep doing what we were doing.
						else if (direction==MOVE_DOWN) {
							headXPos++;
							direction = moves[totalMoves++] = MOVE_DOWN;
						} else {
							headXPos--;
							direction = moves[totalMoves++] = MOVE_UP;
						}
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
