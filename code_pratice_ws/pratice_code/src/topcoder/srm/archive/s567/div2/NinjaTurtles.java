package topcoder.srm.archive.s567.div2;

public class NinjaTurtles {

	public int countOpponents(int P, int K) {
		// for mininum pizza, value of N
		int n = 3*P*K/(9+K);

		while (true) {
			if(P==(3*(n/K)+n/3))
				return n;
			if(P < (3*(n/K) + n/3))
				return -1;
			n++;
		}
	}
}
