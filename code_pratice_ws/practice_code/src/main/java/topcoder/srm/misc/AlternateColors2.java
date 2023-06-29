package topcoder.srm.misc;

public class AlternateColors2 {

	public static void main(String[] s) {
//		testSolutionEqn();
		testCountWays();
	}

	private static void testCountWays() {
		AlternateColors2 colors = new AlternateColors2();

		// 6,4 --> 9
		System.out.println(colors.countWays(6,4));
		//1,1 --> 1
		System.out.println(colors.countWays(1,1));
		//3,3 --> 3
		System.out.println(colors.countWays(3,3));
		//6,1 --> 21
		System.out.println(colors.countWays(6,1));
		// 1000,2 --> 1
		System.out.println(colors.countWays(1000,2));
		// 100000,100000 --> 1666700000
		System.out.println(colors.countWays(100000,100000));
		// 10,10 -->
		System.out.println(colors.countWays(10,10));
		// 6,6 -->
		System.out.println(colors.countWays(6,6));

	}

	public long countWays(int n, int k) {
		long ways = 0;

		ways = ways2CombinationBefore(n,k);

		return ways;
	}

	// way1...... XXXXXXXKthXXXXX
	// we add the possible combinations present from XX... 2 balls before the kth ball.
	// we have 6 such combinations....

	private long ways2CombinationBefore(int n, int k) {
		long ways = 0;

		long wayBefore = 1;
		long wayAfter = 1;

		// GREEN and BLUE; (also considering that there are no balls before this)
		if (k%3==1) {
			// only one way this happen
			wayBefore = 1;
			// chose (n-k) from 3 repetitions allowed
			wayAfter = combination(n-k+2,2);
			ways = ways + (wayAfter*wayBefore);
		}

		// RED && GREEN; RED && BLUE
		if (k>=3) {
			wayBefore = noOfPositiveSolnFor3xPlus2y(k-3);

			wayAfter = combination(n-k+1,1);
			ways = ways + (wayAfter*wayBefore) * 2; // double for 2 conditions
		}

		long waysB = 1;
		if (k>3) {
			waysB = 0;
			int z=0;
			while (z<=k-3) {
				waysB += noOfPositiveSolnFor3xPlus2y(k-3-z);
				z++;
			}
		}

		// RED && RED
		if ( k>1)// since k=1 is taken care by first case
			ways = ways + waysB *1;


		// TODO change the calculations for these last 2 conditions.
		// BLUE && RED; GREEN && RED
		if (k>3) {
			wayBefore = noOfPositiveSolnFor3xPlus2y(k-2);
			wayAfter = 1;
			ways = ways + (wayBefore * wayAfter)*2;
		}

		// BLUE && RED



		return ways;
	}

	private long combination(int n, int r) {
		r = (r > n-r ? n-r : r); // find smaller r for iteration
		if (r==0)
			return 1;
		int result = 1;
		for (int k=1;k<=r;k++) {
			result = result * (n-k+1);
			result = result/k;
		}
		return result;
	}

	private static void testSolutionEqn() {
		AlternateColors2 colors = new AlternateColors2();
		// ans --> 4
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(20));
		// ans --> 0
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(1));
		// ans --> 1
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(5));
		// ans --> 1
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(0));
		// ans --> 1
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(3));
		// ans --> 5
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(24));

		System.out.println(colors.combination(20,1));
		System.out.println(colors.combination(7,5));
		System.out.println(colors.combination(1,1));
	}

	// solutions for eqn. 3x + 2y = c;
	public int noOfPositiveSolnFor3xPlus2y(int c) {
		int soln = 0;
// for 3x+2y = c, the co-efficients of extended euclidean algorithm
// are n=1 and m=-1; solution for 3n+2m=gcd(3,2)=1;
// range for k..... -nc/|b| <= k <= mc/|a|
		double upper_limit = c/2.0;// taking absolute hence removing the -1.
		double lower_limit = c/3.0;

		int nextInt = (int) Math.ceil(lower_limit);
		if (nextInt <= upper_limit) {
			soln++;
			soln = soln + (int)(upper_limit - nextInt);
		}

		return soln;
	}

}
