package projecteuler.set5;

public class P48SelfPowers {

	long tendigit = 1000_000_000_0L;

	public long getLast10Digits(int range) {

		long last10digits = 0;

		for (int i = 1; i <= range ; i++) {
			long pow = raiseTo(i, i);
			last10digits += pow;
		}

		last10digits %= tendigit;
		return last10digits;
	}

	// power by exponentiation won't work as we run overflow since 10^10 * 10^10 > Long.Max
	private long raiseTo(long n, long pow) {
		long power = 1;
		for (int i = 0; i < pow; i++) {
			power = (power * n) % tendigit;
		}

		return power;
	}
}
