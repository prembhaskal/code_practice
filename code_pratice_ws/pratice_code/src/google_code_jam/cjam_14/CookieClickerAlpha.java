package google_code_jam.cjam_14;

import common.util.InputReader;
import java.io.PrintWriter;

public class CookieClickerAlpha {

	private double C;
	private double F;
	private double X;

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo = 1; testNo <= tests; testNo++) {
			C = in.nextDouble();
			F = in.nextDouble();
			X = in.nextDouble();

			double minTime = getMinTime();


			out.print("Case #" + testNo + ": ");
			out.format("%.7f%n", minTime);
		}
	}

	// worked for small input.
	private double getMinTime() {
		
		double previousTime = X/2.0;

		double divisor = 2.0;

		double part1 = 0;
		double part2 = 0;

		while (true) {
			part1 = part1 + (C/divisor);
			double newDivisor = divisor + F;
			part2 = X/newDivisor;

			double newTime = part1 + part2;
			if (previousTime < newTime)
				break;

			// for next cycle
			divisor = newDivisor;
			previousTime = newTime;
		}


		return previousTime;
	}
}
