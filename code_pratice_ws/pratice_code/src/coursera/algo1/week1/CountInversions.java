package coursera.algo1.week1;

import java.io.PrintWriter;
import java.util.Scanner;

public class CountInversions {

	public void solve(Scanner in, PrintWriter out) {
		int[] nums = new int[100000];

		for (int i=0;i<100000;i++)
			nums[i] = Integer.parseInt(in.nextLine());

		out.println(countInversions(nums));
	}


	public long countInversions(int[] nums) {
		long count = 0;

		count = mergeAndReturnInversions(nums,0,nums.length-1);

		return count;
	}



	private long mergeAndReturnInversions(int[] nums, int start, int end) {
		if(start>=end)
			return 0;
		int mid = (start+end)/2;

		long count = mergeAndReturnInversions(nums, start, mid);
		count += mergeAndReturnInversions(nums,mid+1,end);
		count += doTheMerge(nums, start, mid, end);

		return count;
	}


	private long doTheMerge(int[] nums, int start, int mid, int end) {

		if (start == end)
			return 0;

		// precheck
		if (mid + 1 < end) {
			if (nums[mid] <= nums[mid+1])
				return 0;
		}

		int lengthLeft = mid - start + 1;
		int[] left = new int[lengthLeft];

		for (int i=0;i<lengthLeft;i++) {
			left[i] = nums[start + i];
		}

		int lengthRight = end - mid;
		int[] right = new int[lengthRight];

		for (int i=0;i<lengthRight;i++) {
			right[i] = nums[mid + 1 + i];
		}

		long inversions = 0;

		for (int k=0,i=0,j=0;k<lengthLeft+lengthRight;k++) {
			if ( j>=lengthRight ||  i<lengthLeft && (left[i] <= right[j])) {
				nums[start+k] = left[i++];
			} else {
				nums[start+k] = right[j++];
				// since we picked from right, it is an inversion provided there are elements present in left.
				inversions += (lengthLeft - i);
			}
		}


		return inversions;

	}
}
