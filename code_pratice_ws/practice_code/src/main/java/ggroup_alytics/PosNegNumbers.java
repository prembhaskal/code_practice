package ggroup_alytics;

public class PosNegNumbers {

	public void reArrangePositiveNegativeNumbers(int[] nums) {
		int evenPtr = 0;
		int oddPtr = 1;

		int len = nums.length;

		while (evenPtr < len && oddPtr < len) {

			while (oddPtr < len && nums[oddPtr] < 0) {
				oddPtr += 2;
			}

			while (evenPtr < len && nums[evenPtr] >= 0) {
				evenPtr += 2;
			}

			if (evenPtr >= len || oddPtr >= len)
				break;

			swap(nums, evenPtr, oddPtr);
		}
	}


	private void swap(int[] nums, int idx1, int idx2) {
		int temp = nums[idx1];
		nums[idx1] = nums[idx2];
		nums[idx2] = temp;
	}
}
