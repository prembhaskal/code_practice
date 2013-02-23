package codechef.feb13.lecards;
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

	int MOD = 1000000007;

	int[] inverse = new int[502];

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());

		initInverses();

		for (int i=0;i<tests;i++) {
			int N = in.nextInt();
			for (int j=0;j<N;j++)
				in.nextInt();
			findWins(N, out);
		}
	}

	// wins when elephant has more cards than hippo, dont worry about repetition since cost
	// of each win is just 1.

	// choose r from n---> nCr
	// using nCr-1 = r/(n-r+1) * nCr;
	// modInverse of course
	private void findWins(int N, PrintWriter out) {
		int middle = N/2;

		middle++;

		long ways = 1;
		long prev = ways;

		for (int i=N-1;i>=middle;i--) {
			prev = (prev * (i+1) ) % MOD;
			prev = (prev * inverse[N-i]) % MOD;

			ways = (ways + prev) % MOD;
		}

		out.println(ways);
	}


	private void initInverses() {
		inverse[0] = 0;
		for (int i=1;i<inverse.length;i++) {
			inverse[i] = modInverse(i);
		}
	}

	private int modInverse(long n) {
		return pow(n, MOD-2);
	}

	private int pow(long n, long p) {
		long res = 1;

		while (p > 0) {
			if ((p&1)==1) {
				res = (res * n) % MOD;
			}

			n = (n * n) % MOD;
			p /= 2;
		}

		return (int)res;
	}

}

