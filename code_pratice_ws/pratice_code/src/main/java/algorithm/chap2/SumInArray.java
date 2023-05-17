package algorithm.chap2;

public class SumInArray {

	public boolean isSumofTwoPossible(int[] array, long sum) {
		boolean pos = false;

		// sort array to
		// n*log(n) cost
		new MergeSort().sortOptimised(array);

		// check if sum is possible
		for (int i=0,j=array.length-1;i<j;i++) {

			// present element + plus the last element is the smallest sum which we can obtain
			// if smallest sum > Actual sum, then remove the last element
			while (array[i] + array[j] > sum)
				j--;

			if (array[i] + array[j] == sum) {
				pos = true;
				break;
			}

		}

		return pos;
	}
}
