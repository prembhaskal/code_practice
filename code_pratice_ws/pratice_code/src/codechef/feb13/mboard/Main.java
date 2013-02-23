package codechef.feb13.mboard;
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

	public void solve(Scanner in, PrintWriter out) {
		startQuery(in,out);
	}

	boolean [] colValues, rowValues;
	int colSet, rowSet,rowClear, colClear, size;

	public void startQuery(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		initParams(N);

		int queries = in.nextInt();
		String query;
		for (int i=0;i<queries;i++) {
			query = in.next();
			if (query.equals("RowQuery")) {

			} else if (query.equals("ColQuery")) {

			} else if (query.equals("RowSet")) {

			} else {

			}
		}
	}

	private void initParams(int N) {
		colValues = new boolean[N];
		rowValues = new boolean[N];
		size = N;
		colSet = rowSet = 0;
		rowClear = colClear = N;
	}



}

