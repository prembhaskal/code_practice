package com.prem.srm.m567.div2;

/**
 * User: premkumar.bhaskal
 * Date: 1/22/13
 * Time: 2:42 PM
 */
public class TheSquareRootDilemma {

	public int countPairs(int N, int M) {
		int pairs = 0;

		int temp = 0;

		for (int i=1;i<=N;i++) {
			// check if perfect square
			if (Math.sqrt(i)==(int)Math.sqrt(i)) {
				pairs += (int)Math.sqrt(M);
			} else {

				int extraFactors = 1;
				int j;
				for(j=2;j*j<=N;j++) {
					int exp_cnt = 0;
					while(N%j==0) {
						N /= j;
						exp_cnt++;
					}

					if (exp_cnt%2==1) {
						extraFactors *= j;
					}
				}

				if (N>1) {
					extraFactors *=j;
				}
			}
		}

		return pairs;
	}
}
