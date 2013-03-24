package projecteuler.set1;

public class P4LargestPalindrome {

	// finding largest palindrome having two multiples of 3 digits.
	public int findLargestPalindromeTwo3DigitMultiple() {
		int number = 0;

		for (int i=999;i>700;i--) {
			for (int j=999;j>700;j--) {
				if (isPalindrome(i*j)) {
					number = Math.max(number, i*j);
				}
			}
		}
		return number;
	}


	public boolean isPalindrome(int num) {
		int rev = 0;
		int copy = num;
		while (num > 0) {
			int digit = num % 10;
			num /= 10;
			rev = rev*10 + digit;
		}

		return rev == copy;
	}
}
