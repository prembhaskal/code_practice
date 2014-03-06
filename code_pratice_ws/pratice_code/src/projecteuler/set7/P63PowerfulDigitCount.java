package projecteuler.set7;

public class P63PowerfulDigitCount {

	// 10^n-1 < x^n < 10^n
	// so the upper bound is x < 10;
	// lower bound.... 10^n-1 < x^n....
	// n <= 1 / (1 - logx) .... log is base 10.

	public void findTheDigits() {
		int total = 0;

		for (int x = 1; x < 10; x++) {
			int n = (int) (1.0 / (1.0 - Math.log10(x)));
			System.out.println(n);
			total += n;
		}

		System.out.println("total is " + total);
	}

}
