package codeforces.task.m162.div2.task_c;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskC solution = new TaskC();
//		solution.solve(in,writer);
		solution.betterSolve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskC {

	public void solve(Scanner in, PrintWriter out) {
		String seq = in.nextLine();

		char[] sequence = seq.toCharArray();

//		Interval[] stones = new Interval[sequence.length];
		List<Interval> stones = new ArrayList<Interval>();

		BigDecimal x = BigDecimal.ZERO;
		BigDecimal y = BigDecimal.ONE;

		for (int i=0;i<sequence.length;i++) {
			if (sequence[i]=='l') {
				y = y.subtract((x.subtract(y).abs().divide(BigDecimal.valueOf(2))));
				stones.add(new Interval(i+1, y));
			} else {
				x = x.add((x.subtract(y).abs().divide(BigDecimal.valueOf(2))));
				stones.add(new Interval(i+1,x));
			}
		}

		Collections.sort(stones);

		for (Interval interval : stones)
			out.println(interval.stone);

	}


	public void betterSolve(Scanner in, PrintWriter out) {
		String seq = in.nextLine();

		char[] sequence = seq.toCharArray();

		Stack<Integer> stack = new Stack<Integer>();
		List<Integer> list = new ArrayList<Integer>();


		for (int i=0;i<sequence.length;i++) {
			if (sequence[i]=='l') {
				stack.push(i+1);
			} else {
				list.add(i+1);
			}
		}

		for (int i: list) {
			out.println(i);
		}

		while (!stack.isEmpty())
			out.println(stack.pop());
	}

}

class Interval implements Comparable<Interval>{
	int stone;

	BigDecimal position;

	public Interval(int stone, BigDecimal position) {
		this.stone = stone;
		this.position = position;
	}

	@Override
	public int compareTo(Interval o) {
		return position.compareTo(o.position);
	}
}
