package fb.hackercup.cup2013;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CardGame {

	int MOD = 1000000007;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());
		int tc=1;
		while (tests > 0) {
			int n = in.nextInt();
			int k = in.nextInt();

			int[] cards = new int[n];
			for (int i = 0; i < n; i++) {
				cards[i] = in.nextInt();
			}
			cardGame(n, k, cards, tc, out);

			tc++;
			tests--;
		}
	}


	public void cardGame(int n, int k, int[] cards, int testNo, PrintWriter out) {
		Arrays.sort(cards);

		if (n==k) {
			System.out.println("Case #" + testNo + ": " + cards[n-1]);
			out.println("Case #" + testNo + ": " + cards[n-1]);
			return;
		}

		int r = k-1;

		long max = cards[r];
		long val = 1;
		for (int i=r+1;i<n;i++) {
			val = (val * i) % MOD;
			val = (val *  modInverse(i-r)) % MOD;

			max = (max + (val * cards[i])%MOD)%MOD;
		}

		int maximal = (int)max;
		System.out.println("Case #" + testNo + ": " + maximal);
		out.println("Case #" + testNo + ": " + maximal);
	}

	private int modInverse(long n) {
		return pow(n,MOD-2);
	}

	private int pow(long n, long p) {
		long res = 1;

		while (p>0) {
			if ((p&1)==1) {
				res = (res * n) % MOD;
			}

			n = (n*n) % MOD;
			p /= 2;
		}

		return (int) res;
	}

}
