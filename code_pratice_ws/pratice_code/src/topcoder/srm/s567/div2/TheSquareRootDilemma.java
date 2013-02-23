package topcoder.srm.s567.div2;

public class TheSquareRootDilemma {

	public int countPairs(int N, int M) {
		int pairs = 0;

		for (int i=1;i<=N;i++) {

			int num = i;
			int extra = 1;
			// find the isolated pairs (power==1) and use them
			// Standard trial division
			for (int j=2;j*j<=num;j++) {
				int count = 0;

				while (num%j==0) {
					count++;
					num /= j;
				}

				if (count%2==1)
					extra *= j;
			}

			extra *= num;

			// if N is not square, then N = (square)*(extra)
			// for MN= square, M = (extra)*(another_square)
			// finding all possiblities here
			// if N == square, after trial division will leave extra==num
			// hence MN = extra * (another square), the same.
			int k = 1;
			while (extra *k*k <=M ) {
				pairs++;
				k++;
			}
		}

		return pairs;
	}


}
