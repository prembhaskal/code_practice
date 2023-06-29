package projecteuler.set4;

public class DoubleBasePalin {

	// improvement, checking only for odd nos...an even number cannot have a palindrome binary reps.
	public int getSumOfDoubleBasePalin(int range) {
		int sum = 0;
		for (int i = 1; i < range; i+=2) { // note +=2
			if (isPalindrome(i) && isBinaryRepPalindrome(i)) {
				sum += i;
			}
		}

		return sum;
	}


	public boolean isPalindrome(int num) {
		if (num < 10)
			return true;

		int copy = num;
		int rev = 0;
		while (num > 0) {
			rev = rev*10 + num%10;
			num /= 10;
		}

		return rev == copy;
	}

	public boolean isBinaryRepPalindrome(int num) {
		char[] binary = Integer.toBinaryString(num).toCharArray();
		int len = binary.length;
		for (int i=0;i<=len/2;i++) {
			if (binary[i]!= binary[len-i-1]) {
				return false;
			}
		}

		return true;
	}
}
