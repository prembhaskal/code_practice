package topcoder.srm.s575.div2;

public class TheNumberGameDivTwo {


	// TODO this logic is wrong, correct it after reading editorial.
	public String find(int n) {
		boolean chance = true;
		int newNum = n;

		while (true) {
			int divisor = getBiggestDivisor(newNum);

			newNum = newNum - divisor;

			if (divisor==0) {
				break;
			}

			chance = !chance;
		}

		if (chance) {
			return "Brus";
		} else {
			return "John";
		}
	}

	private int getBiggestDivisor(int num) {
		int divisor = 0;

		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				divisor = Math.max(divisor,i);
				int otherDiv = num/i;
				divisor = Math.max(divisor, otherDiv);
			}
		}

		return divisor;
	}


	public int getBigOddDivisorForEvenNumber(int num) {
		if (num==2)
			return 0;

		boolean found = false;
		int foundelement = 0;
		for (int i=2;i<=num/i;i++) {
			int exp = 0;
			if (num%i==0) {
				int div = num/i;

				if (div%2==1) {
					found = true;
					foundelement = div;
					break;
				}

				if (i%2==1) {
					foundelement = Math.max(i,foundelement);
				}
			}
		}

		return foundelement;
	}
}
