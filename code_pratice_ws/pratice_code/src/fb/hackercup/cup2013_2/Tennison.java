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

	// TODO the brute force should be changed a little so that
	// if we win,  the sun can remain the same.
	//         OR, the sun can increase...
	// similarly for losing.
	private double getProbability(int set, int wins, double Pi, double probTillHere) {
		if (wins == setsToWin)
			return probTillHere;
		if (set == 2*setsToWin)
			return 0;

		double probWinning = Ps * Pi + Pr * (1 - Pi);
		// win and more sun
		double P1 = getProbability(set + 1, wins + 1, Math.min(1.0, Pi + Pu), probTillHere * probWinning * Pw);
		// win and same sun
		double P2 = getProbability(set + 1, wins + 1, Pi, probTillHere * probWinning * (1 - Pw));
		// lose and lesser sun
		double P3 = getProbability(set + 1, wins, Math.max(0.0, Pi - Pd), probTillHere * (1-probWinning) * Pl);
		// lose and same sun
		double P4 = getProbability(set + 1, wins, Pi, probTillHere * (1 - probWinning) * (1 - Pl));

		return P1 + P2 + P3 + P4;
	}

	// solution using DP...
	// the solution uses 3d array...similar to the method above which uses 3 variables to decide the
	// the probability of the next set.

	// one guy even used a map of double...some craziness...
	// most people multiplied by 1000, to convert to integer....to apply DP..didn't get the logic much..though the solution
	// says the same thing.
}
