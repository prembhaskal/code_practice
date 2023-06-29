package projecteuler.set3;

public class P24MillionthPermutation {

	private int count = 0;
	private boolean stop = false;
	public void findPermutation(String str) {
//		System.out.println(getFactorial(10));
		findPermutation(str.toCharArray(), str.length());
	}

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

	/**
	 * lexicographic next permutation
	 * see http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
	 * It also takes care of repetitions.
	 * Also it is faster than recursion, coz there ain't any recursion. ;)
	*/
	public boolean nextPermutation(int[] num) {

		// find the largest index k, such num[k] < num[k+1]
		int k = -1;
		for (int i = 0; i < num.length - 1; i++) {
			if (num[i] < num[i+1])
				k = i;
		}
		
		// if no such index exist, it means that this is the last permutation.
		if (k==-1)
			return false;
		
		
		// find largest index l, such that a[k] < a[l], we will certainly get 1.
		// also l > k.
		int l = 0;
		for (int i = k + 1; i < num.length; i++) {
			if (num[k] < num[i])
				l = i;
		}

		// swap l and k;
		swap(num, k, l);

		// reverse the rest of the array from k+1 to end.
		int start = k+1;
		int end = num.length - 1;
		while (start < end) {
			swap(num, start, end);
			start++;
			end--;
		}

		return true;
	}

	private void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
}
