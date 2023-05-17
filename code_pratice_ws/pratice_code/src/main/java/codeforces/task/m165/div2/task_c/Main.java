package codeforces.task.m165.div2.task_c;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		int sizeCount = Integer.parseInt(in.nextLine());

//		int[] sizes = new int[sizeCount];
//		int[] boxCount = new int[sizeCount];
//
//		for (int i=0;i<sizeCount;i++) {
//			sizes[i] = in.nextInt();
//			boxCount[i] = in.nextInt();
//		}

		// start from minimum, find for each size, the max-size required to pack all boxes of this size.

		int maxsize = -1;
		int size, count, boxSize, boxCount;
		for (int i=0;i<sizeCount;i++) {
			count = 4;
			boxSize = in.nextInt();
			boxCount = in.nextInt();
			for (size=boxSize+1;count<boxCount;size++) {
				count *= 4;
			}

			maxsize = Math.max(maxsize, size);

		}


		out.println(maxsize);

	}


}
