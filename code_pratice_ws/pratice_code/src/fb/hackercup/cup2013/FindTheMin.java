package fb.hackercup.cup2013;

import java.io.PrintWriter;
import java.util.Scanner;

public class FindTheMin {

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());

		int testNo = 1;
		while (tests > 0) {
			findTheMin(testNo++, in, out);
			tests--;
		}
	}

	int a,b,c,r,n,k;
	int values[];

	private void findTheMin(int testCase, Scanner in, PrintWriter out) {
		int min = 0;
		n = in.nextInt();
		k = in.nextInt();

		a = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		r = in.nextInt();

		values = new int[k];

		// this is like a lookup to help find min non negative
		int[] valuesOccurences = new int[k];

		// fill values using psuedo generator
		// m[i] = (b * m[i - 1] + c) % r, 0 < i < k
		values[0] = a;
		if (a < k)
			valuesOccurences[a]++;

		for (int i=1;i<k;i++) {
			long inter = ((long)b * (long)values[i-1])%r;
			inter = (inter + (long)c)%r;
			values[i] = (int)inter;
			if (values[i] < k)
				valuesOccurences[values[i]]++;
		}

//		printValues(values, out);

		setFirstSecondMin(valuesOccurences);
		int newIndex;
		int newValue = 0;
		boolean reachedK = false;

		for (int i=k;i<n;i++) {
//			newValue = findMininumNonNegative(valuesOccurences,0);
			newValue = firstMin;
			firstMin = k;

			if (newValue == k) {
				int[] newInt = new int[k+1];
				newInt[0] = k;
				newIndex = i%k;
				for(int j=1;j<k+1;j++)
					newInt[j] = values[(newIndex-1+ j)%(k)];

				int rem = (n-i-1)%(k+1);
				min = newInt[rem];
				break;
			}
			if (i==n-1) {
				min = newValue;
				break;
			}

			newIndex = i%k;
			// replace the oldValue with the new value, both in values and lookups
			if (values[newIndex] < k)
//				valuesOccurences[values[newIndex]]--;
				removeFromOccurences(valuesOccurences, values[newIndex]);

			if (newValue < k)
//				valuesOccurences[newValue]++;
				putIntoOccurences(valuesOccurences,newValue);

			values[newIndex] = newValue;
//			System.out.println("done values " + i);

		}

		System.out.println("Case #" + testCase + ": " + min);
		out.println("Case #" + testCase + ": " + min);

	}

	private void printValues(int[] nums, PrintWriter out) {
		for (int i=0;i<nums.length;i++)
//			out.println(nums[i]);
			System.out.println(nums[i]);
	}


	int firstMin, secondMin;
	private void setFirstSecondMin(int[] values) {
		boolean firstSet = false;
		for (int i=0;i<values.length;i++) {
			if (values[i]==0) {
				firstMin = i;
				firstSet = true;
				break;
			}
		}

		for (int i = firstMin+1;i<values.length;i++) {
			if (values[i]==0) {
				secondMin = i;
				return;
			}
		}

		if (!firstSet)
			firstMin = k;

		secondMin = k;
	}

	private void removeFromOccurences(int[] values, int removeIndex) {
		values[removeIndex]--;
		if (values[removeIndex]==0 && removeIndex < secondMin)
			firstMin = removeIndex;
		else {
			firstMin = secondMin;
			secondMin = findMininumNonNegative(values,firstMin+1);
		}
	}


	private void putIntoOccurences(int[] values, int putIndex) {
		values[putIndex]++;
		if (firstMin >= secondMin) {
			firstMin = secondMin;
			secondMin = findMininumNonNegative(values, firstMin+1);
		}

	}

	private int findMininumNonNegative(int[] values, int searchFrom) {
		for (int i=searchFrom;i<values.length;i++) {
			if (values[i]==0)
				return i;
		}
		return k;
	}
}
