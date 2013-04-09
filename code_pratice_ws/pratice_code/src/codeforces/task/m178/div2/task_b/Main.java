package codeforces.task.m178.div2.task_b;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}

}

class TaskA {

	int books;

	int[] bookThickness;
	int[] bookWidth;

	boolean [] isHorizontal;

	public void solve(Scanner in, PrintWriter out) {
		books = in.nextInt();

		bookThickness = new int[books];
		bookWidth = new int[books];

		for (int i=0;i<books;i++) {
			bookThickness[i] = in.nextInt();
			bookWidth[i] = in.nextInt();
		}


		if (books == 1) {
			out.println(bookThickness[0]);
		} else {
			int minWidth = getMinWidth();
			out.println(minWidth);
		}
	}



	// greedy approach for a general height
	// > try putting book horizontally.
	// else try replacing a horizontal book to get better(min) width --- widthReplaceH
	// also try replacing a vertical book to get better(min ) width -- widthReplaceV
	// choose min of widthReplaceH and widthReplaceV
	// if both attempts fail to get better than already available, choose to put vertically.
	private int getMinWidth() {
		int minWidth = Integer.MAX_VALUE;
		isHorizontal= new boolean[books];

		// first is always kept as vertical.
		int widthAvailable = bookWidth[0];
		int totalThickNess = 0;

		for (int i=1;i<books;i++) {
			int thickness = bookThickness[i];
			// check if we can keep it horizontal
			if (thickness <= widthAvailable) {
				isHorizontal[i] = true;
				widthAvailable -= thickness;
			}
			else { // check if we can replace any horizontal book and reduce available width
				for (int j=i-1;j>=0;j--) {
					int newWidth = widthAvailable - bookWidth[j] + bookWidth[i];
					// check if this is possible

				}
			}
		}

		return minWidth;

	}


	// TODO try implementing the approach of checking all combinations of 2 and 1's to get min width.

}

