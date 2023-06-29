package practice.algorithm.recursive;

public class PermutationFinding {

	public void printPermutationByRotation(int[] nums) {
		findPermutation(nums, nums.length);
	}

	private void findPermutation(int[] nums, int len) {
		if (len == 1) {
			print(nums);
			return;
		}

		for (int i = 0; i < len; i++) {
			findPermutation(nums, len - 1);
			rotateLeftByOne(nums, nums.length - len);
		}
	}

	private void rotateLeftByOne(int[] nums, int start) {
		int temp = nums[start];

		for (int i = start; i < nums.length - 1; i++) {
			nums[i] = nums[i + 1];
		}

		nums[nums.length - 1] = temp;
	}

	private void print(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}

	public void printPermutationSwapping(int[] nums) {
		permute(nums, 0);
	}

	private void permute(int[] nums, int start) {
		if (start == nums.length - 1) {
			print(nums);
		}
		else {
			for (int i = start; i < nums.length; i++) {
				swap(nums, start, i);
				permute(nums, start + 1);
				swap(nums, start, i);
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
