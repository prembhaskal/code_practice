package fb.hackercup.cup2013_2;

import common.util.InputReader;
import java.io.PrintWriter;

public class Tennison {

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int i = 1; i <= tests; i++) {
			setsToWin = in.nextInt();
			Ps = in.nextDouble();
			Pr = in.nextDouble();
			Pi = in.nextDouble();
			Pu = in.nextDouble();
			Pw = in.nextDouble();
			Pd = in.nextDouble();
			Pl = in.nextDouble();

			double prob = getProbBruteForce();

			out.println("Case #" + i + ": " + prob);
		}
	}

	int setsToWin;
	double Ps, Pr, Pi, Pu, Pw, Pd, Pl;

	private double getProbBruteForce() {
		return getProbability(1, 0, Pi, 1.0);
	}

	private double getProbability(int set, int wins, double Pi, double probTillHere) {
		if (wins == setsToWin)
			return probTillHere;
		if (set == 2*setsToWin)
			return 0;

		double probWinning = Ps * Pi + Pr * (1 - Pi);
		// we win this set.
		double P1 = getProbability(set + 1, wins + 1, Math.min(1.0, Pi + Pu * Pw), probTillHere * probWinning);
		// we lose this set.
		double P2 = getProbability(set + 1, wins, Math.max(0.0, Pi - Pd*Pl), probTillHere * (1-probWinning));

		return P1 + P2;
	}

//	private double getProbabilityDP() {
//		int maxSetPossible = setsToWin*2;
//		double [][] prodArray = new double[maxSetPossible][maxSetPossible];
//
//		// we win in first set
//		prodArray[1][1] = prodHere(Ps, 0, 0);
//		// we lose in first set
//		prodArray[0][1] = 1 - prodArray[1][1];
//
//		for (int i = 0; i <= setsToWin; i++) {
//			for (int j = i + 1; j < maxSetPossible; j++) {
//				prodArray[i][j] = prodHere(Ps, i, j - i) * prodArray[i-1][j-1];
//				pro
//			}
//		}
//	}
//
//	private double prodHere(double probSun, double inc, double dec) {
//		double prod = Pr * probSun  + Ps * (1-probSun);
//		prod += inc;
//		prod -= dec;
//		return prod;
//	}
}
