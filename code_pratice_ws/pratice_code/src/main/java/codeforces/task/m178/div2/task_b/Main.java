package codeforces.task.m178.div2.task_b;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskB solution = new TaskB();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}

}

class TaskB {

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
			int minWidth2 = getMinWidthIterative();
			out.println(minWidth2);
		}
	}

	// TODO try implementing the approach of checking all combinations of 2 and 1's to get min width.
	// if a book1 of width w1 is not taken, then a book1 of w2 (where w2>w1) can never give a better answer
	// hence sorting by width helps. same holds for book2.
	// this got accepted.
	private int getMinWidthIterative() {
		List<Integer> book1 = new ArrayList<Integer>();
		List<Integer> book2 = new ArrayList<Integer>();

		for (int i=0;i<books;i++) {
			if (bookThickness[i]==1)
				book1.add(bookWidth[i]);
			else
				book2.add(bookWidth[i]);
		}

		Collections.sort(book1);
		Collections.sort(book2);

		int minWidth = 3000; // some big number
		for (int b1=0;b1<=book1.size();b1++) {
			for (int b2=0;b2<=book2.size();b2++) {
				int widthAvail = b1 + 2*b2;
				int widthPresent = 0;
				for (int i=book1.size()-1-b1;i>=0;i--)
					widthPresent += book1.get(i);
				for (int i=book2.size()-1-b2;i>=0;i--)
					widthPresent += book2.get(i);

				if (widthAvail >= widthPresent) {
					minWidth = Math.min(widthAvail, minWidth);
				}
			}
		}

		return minWidth;
	}

}

