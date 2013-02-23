package codeforces.task.m161div2.task_c;

import java.io.PrintWriter;
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
		int n = in.nextInt();

		int[] A = new int[2*n];
		int[] B = new int[2*n];

		ArrayList<Arc> points = new ArrayList<Arc>();

		Arc arc;
		for (int i=0;i<2*n;i++) {
			arc = new Arc(in.nextInt(), in.nextInt());
			points.add(arc);
		}

		Collections.sort(points);

	}

	private void call(int i, int j, ArrayList<Arc> points, int[] array, Set<Integer> set, Scanner in, PrintWriter out) {
//		int pt_b1 = points.get(2*j-2).b;
//		int pt_b2 = points.get(2*j-1).b;
////		if (set.contains(pt_b1) && pt_b2) {
//			out.print(-1);
//			out.close();
//			in.close();
//			System.exit(0);
//		} else {
//
//		}
	}


}


class Arc implements Comparable<Arc> {

	int a;
	int b;

	public Arc(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Arc arc) {
		if (this.a > arc.a)
			return 1;
		else if (this.b > arc.b)
			return 1;
		else
			return 0;
	}
}
