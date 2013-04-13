package google_code_jam.cjam_13;

import java.io.PrintWriter;
import java.util.Scanner;

public class TicTacToeTomek {

	char[][] grid;

	String XWON = "X won";
	String OWON = "O won";
	String DRAW = "Draw";
	String INCOMPLETE = "Game has not completed";

	boolean isDotPresent;

	int[] XleftToRight;
	int[] OleftToRight;
	int[] TleftToRight;
	int[] XtopToBottom;
	int[] OtopToBottom;
	int[] TtopToBottom;

	int Xdiag1;
	int Odiag1;
	int Tdiag1;

	int Xdiag2;
	int Odiag2;
	int Tdiag2;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());

		for (int testNo=1;testNo<=tests;testNo++) {
			readGrid(in);
			if (testNo!=tests) {
				String emptyLine = in.nextLine();
			}

			String result = getResult();
			out.println("Case #" + testNo + ": " + result);
		}
	}

	private String getResult() {

		XleftToRight = new int[4];
		OleftToRight = new int[4];
		TleftToRight = new int[4];
		XtopToBottom = new int[4];
		OtopToBottom = new int[4];
		TtopToBottom = new int[4];

		Xdiag1 = Odiag1 = Tdiag1 = Xdiag2 = Odiag2 = Tdiag2 = 0;

		isDotPresent = false;

		// left to right
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				char ch = grid[i][j];

				if (ch=='.')
					isDotPresent = true;

				if (ch=='X')
					XleftToRight[i]++;
				else if (ch=='O')
					OleftToRight[i]++;
				else if (ch=='T')
					TleftToRight[i]++;
			}
		}

		// top to bottom
		for (int j=0;j<4;j++) {
			for (int i=0;i<4;i++) {
				char ch = grid[i][j];
				if (ch=='X')
					XtopToBottom[j]++;
				else if (ch=='O')
					OtopToBottom[j]++;
				else if (ch=='T')
					TtopToBottom[j]++;
			}
		}

		// diagonal 1
		for (int i=0;i<4;i++) {
			char ch = grid[i][i];
			if (ch=='X')
				Xdiag1++;
			else if (ch=='O')
				Odiag1++;
			else if (ch=='T')
				Tdiag1++;

		}

		// diagonal 2
		for (int j=3, i=0;i<4;i++,j--) {
			char ch = grid[i][j];
			if (ch=='X')
				Xdiag2++;
			else if (ch=='O')
				Odiag2++;
			else if (ch=='T')
				Tdiag2++;
		}

		// check if X is won
		for (int i=0;i<4;i++) {
			if (XleftToRight[i]+TleftToRight[i]==4) {
				return XWON;
			} else if (XtopToBottom[i]+TtopToBottom[i]==4){
				return XWON;
			} else if (Xdiag1+Tdiag1==4)
				return XWON;
			else if (Xdiag2+Tdiag2==4)
				return XWON;
		}

		// check if O has won
		for (int i=0;i<4;i++) {
			if (OleftToRight[i]+TleftToRight[i]==4) {
				return OWON;
			} else if (OtopToBottom[i]+TtopToBottom[i]==4){
				return OWON;
			} else if (Odiag1+Tdiag1==4)
				return OWON;
			else if (Odiag2+Tdiag2==4)
				return OWON;
		}

		if (isDotPresent) {
			return INCOMPLETE;
		} else {
			return DRAW;
		}
	}

	private void readGrid(Scanner in) {
		grid = new char[4][4];

		for (int i=0;i<4;i++) {
			char[] line = in.nextLine().toCharArray();
			for (int j=0;j<4;j++) {
				grid[i][j] = line[j];
			}
		}
	}
}
