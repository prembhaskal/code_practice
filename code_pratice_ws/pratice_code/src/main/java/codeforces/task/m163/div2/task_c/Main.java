package codeforces.task.m163.div2.task_c;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskC solution = new TaskC();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskC {

	public void solve(Scanner in, PrintWriter out) {
		int side = Integer.parseInt(in.nextLine());

		int[][] square = new int[side][side];

		for (int i=0;i<side;i++)
			Arrays.fill(square[i],0);

		int x,y;
		for (int i=1;i<side-1;i++) {
			x=in.nextInt();
			y=in.nextInt();
			square[x-1][y-1] = 1;
		}

		int empty_row;

		// find the empty row;
		for (int i=0;i<side;i++) {
			for (int j=0;j<side;j++) {
				if (square[i][j]==1) {

				}
			}
		}

	}


}

