package codechef.april13.fc_barca_pass;

import java.io.PrintWriter;
import java.util.*;

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

	int[] inverses;

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();

		initInverses();

		for (int i=0;i<tests;i++) {
			int n = in.nextInt();
			int k = in.nextInt();

			// n-1 since last pass should be to MESSI.
			int passes = getNoOfPasses(n-1, k);

			out.println(passes);
		}

	}

	private int getNoOfPasses(long n, int k) {
		long passes;

		if (k==1)
			return 1;

		if (n==1)
			return k;

		// if n is even, the values are k.(k^n-1)/(k+1)
		if ((n&1)==0) {
			int modInv = inverses[k+1];
			passes = power(k,n);
			passes = (passes - 1)%MOD;
			passes = (passes * k) % MOD;
			passes = (passes * modInv)%MOD;
			return (int) passes;
		}
		// if n is odd, the values are k.(k^n+1)/(k+1)
		else {
			int modInv = inverses[k+1];
			passes = power(k,n);
			passes = (passes + 1)%MOD;
			passes = (passes * k) % MOD;
			passes = (passes * modInv)%MOD;
			return (int) passes;
		}

	}

	private int power(long num, long pow) {
		long power = 1;

		while(pow > 0) {
			if ((pow&1)==1) {
				power = (power * num)%MOD;
			}

			num = (num * num)%MOD;
			pow /= 2;
		}

		return (int)power;
	}

	private void initInverses() {
		inverses = new int[12]; // since max value of k is 10, we store inverse of k+1
		inverses[0] = 0;

		for (int i=1;i<inverses.length;i++) {
			inverses[i] = modInverse(i);
		}
	}

	private int modInverse(long n) {
		return power(n, MOD-2);
	}


}

