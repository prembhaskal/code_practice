package projecteuler.set3;

public class P24MillionthPermutation {

	private int count = 0;
	private boolean stop = false;
	public void findPermutation(String str) {
//		System.out.println(getFactorial(10));
		findPermutation(str.toCharArray(), str.length());
	}

	// TODO see improved method in solution of problem 41.
	private void findPermutation(char[] chars, int length) {
		if (stop)
			return;
		if (length==2) {
			count++;
			printPermutation(new String(chars), count);
			rotateByOne(chars, chars.length-2);
			count++;
			printPermutation(new String(chars), count);
			rotateByOne(chars, chars.length-2);
		} else {
			// actual string needed for helping to print in lexicographic way.
			String originalStr = new String(chars);
			for (int i=0;i<length;i++) {
				findPermutation(chars, length-1);
//				rotateByOne(chars, chars.length - length);
				chars = originalStr.toCharArray();
				int endPoint = chars.length - length + i + 1;
				endPoint = endPoint%chars.length;
				// rotating only a part of string, first 2 elements then 3 ,then 4 and so on.
				rotateByOne(chars, chars.length - length, endPoint);
			}
		}

	}

	private void printPermutation(String str, int count) {
//					System.out.println(str + "--" + count);
		if (count == 1000000) {
			System.out.println(str);
			stop = true;
		}
	}

	private void rotateByOne(char[] chars, int startPoint, int endPoint) {
		char temp = chars[endPoint];
		while (endPoint > startPoint) {
			chars[endPoint] = chars[endPoint-1];
			endPoint--;
		}
		chars[startPoint] = temp;
	}



	private void rotateByOne(char[] chars, int startingPoint) {
		char temp = chars[startingPoint];
		while (startingPoint < chars.length - 1) {
			chars[startingPoint] = chars[startingPoint + 1];
			startingPoint++;
		}
		chars[chars.length-1] = temp;
	}

	private int getFactorial(int n) {
		if (n==1)
			return 1;
		return n * getFactorial(n-1);
	}
}
