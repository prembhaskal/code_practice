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

//			double prob = getProbBruteForce();
			double prob = getProbMemoization();

			out.print("Case #" + i + ": ");
			out.format("%.6f%n", prob);
		}
	}

	int setsToWin;
	double Ps, Pr, Pi, Pu, Pw, Pd, Pl;
	int PuInt, PdInt;

	private double getProbBruteForce() {
		return getProbability(1, 0, Pi);
	}

	// TODO the brute force should be changed a little so that
	// if we win,  the sun can remain the same.
	//         OR, the sun can increase...
	// similarly for losing.
	private double getProbability(int set, int wins, double Pi) {
		if (wins == setsToWin)
			return 1.0;
		if (set == 2*setsToWin)
			return 0;

		double probWinning = Ps * Pi + Pr * (1 - Pi);
		// win and more sun
		double P1 = getProbability(set + 1, wins + 1, Math.min(1.0, Pi + Pu)) * probWinning * Pw;
		// win and same sun
		double P2 = getProbability(set + 1, wins + 1, Pi) *probWinning * (1 - Pw);
		// lose and lesser sun
		double P3 = getProbability(set + 1, wins, Math.max(0.0, Pi - Pd)) * (1-probWinning) * Pl;
		// lose and same sun
		double P4 = getProbability(set + 1, wins, Pi)* (1 - probWinning) * (1 - Pl);

//		System.out.println(set + " - " + wins + " - " + Pi + " ---> " + (P1 + P2 + P3 + P4));

		return P1 + P2 + P3 + P4;
	}

	// solution using DP...
	// the solution uses 3d array...similar to the method above which uses 3 variables to decide the
	// the probability of the next set.

	// one guy even used a map of double...some craziness...
	// most people multiplied by 1000, to convert to integer....to apply DP..didn't get the logic much..though the solution
	// says the same thing.

	private double getProbMemoization() {
		double [][][] DP = new double[2*setsToWin][setsToWin][1001];
		PuInt = (int) (Pu * 1000);
		PdInt = (int) (Pd * 1000);
		int PiInt = (int) (Pi * 1000);

		for (int i = 0; i < 2 * setsToWin; i++) {
			for (int j = 0; j < setsToWin; j++) {
				for (int k = 0; k < 1001; k++) {
					DP[i][j][k] = -1;
				}
			}
		}

		double ans =  getProbabilityMemoization(1, 0, PiInt, DP);
		return ans;
	}

	private double getProbabilityMemoization(int set, int wins, int Pint, double [][][] DP) {
		if (wins == setsToWin)
			return 1.0;
		if (set == 2*setsToWin)
			return 0;
		if (DP[set][wins][Pint] >= 0.0)
			return DP[set][wins][Pint];

		double probWinning = Ps * Pint + Pr * (1000 - Pint);
		probWinning = probWinning / 1000;

		// win and more sun
		double P1 = getProbabilityMemoization(set + 1, wins + 1, Math.min(1000, Pint + PuInt), DP) * probWinning * Pw;
		// win and same sun
		double P2 = getProbabilityMemoization(set + 1, wins + 1, Pint, DP) * probWinning * (1 - Pw);
		// lose and lesser sun
		double P3 = getProbabilityMemoization(set + 1, wins, Math.max(0, Pint - PdInt), DP) * (1 - probWinning) * Pl;
		// lose and same sun
		double P4 = getProbabilityMemoization(set + 1, wins, Pint, DP) * (1 - probWinning) * (1 - Pl);

		DP[set][wins][Pint] = P1 + P2 + P3 + P4;

//		System.out.println("M - " + set + " - " + wins + " - " + Pint + " ---> " + DP[set][wins][Pint]);

		return DP[set][wins][Pint];
	}
}
