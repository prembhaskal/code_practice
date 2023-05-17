package google_code_jam.practice;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * User: premkumar.bhaskal
 * Date: 4/12/13
 * Time: 4:09 PM
 */
public class Numbers {

	double number;

	// see solution explaination here https://groups.google.com/forum/?fromgroups=#!topic/google-code/eGR-c4UYUGE
	public void solve(Scanner in, PrintWriter out) {
		int tests  = in.nextInt();

		double three = 3.0;
		double rootFive = Math.pow(5, 0.5);

		number = three + rootFive;

		for (int testNo=1;testNo<=tests;testNo++) {
			int power = in.nextInt();
			String number = getTheLast3Digits(power);
			out.println("Case #" + testNo + ": " + number);
		}
	}

	private String getTheLast3Digits(int power) {
//		double  numberPower = power(number, power);
		double  numberPower = powerUsingBigDecimal(number, power);

		int number3Digits = (int)numberPower;

		String  ThreeDigits = "" + number3Digits;
		if (number3Digits > 99 )
			return ThreeDigits;
		else if (number3Digits > 9)
			return "0" + ThreeDigits;
		else
			return "00" + ThreeDigits;

	}

	private double power(double num, int pow) {
		double power = 1;

		while (pow > 0) {
			if (pow%2==1) {
				power *= num;
				power = truncateToThreedigits(power);
			}

			num *= num;
			num = truncateToThreedigits(num);
			pow /= 2;
		}

		return power;
	}

	// TODO for integers, (a*b)%n = (a*(b%n))%n;
	// why does that approach does not work here ?? find out may be by simulation.
	private double truncateToThreedigits(double num) {
		int after3digits = (int)num/1000;
		after3digits *= 1000;
		double ans = num - after3digits;
		return ans;
	}

	private double powerUsingBigDecimal(double num, int pow) {
		BigDecimal numB = BigDecimal.valueOf(num);
		BigDecimal power = BigDecimal.ONE;

		while (pow > 0) {
			if (pow%2==1) {
				power = power.multiply(numB);

//				power = truncateTo3Digits(power);
			}

			numB = numB.pow(2);
//			numB = truncateTo3Digits(numB);
			pow /= 2;
		}

		power = truncateTo3Digits(power);

		double ans = power.doubleValue();
		return ans;
	}

	private BigDecimal truncateTo3Digits(BigDecimal numB) {
		BigDecimal after3Dig = numB.remainder(BigDecimal.valueOf(1000));
		return after3Dig;
	}
}
