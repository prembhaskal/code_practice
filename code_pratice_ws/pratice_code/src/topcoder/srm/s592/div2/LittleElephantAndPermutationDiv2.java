package topcoder.srm.s592.div2;

// minor brute force -- this got accepted.
public class LittleElephantAndPermutationDiv2 {

	long totalPairs = 0;
	int[] actualArray;
	int K;


	public long getNumber(int N, int K) {
		this.K = K;
		actualArray = new int[N];
		int [] copy = new int[N];
		for (int i = 0; i < N; i++) {
			copy[i] = actualArray[i] = i+1;
		}

		getPermutation(copy);

		totalPairs = totalPairs * fact(N);

		return totalPairs;
	}

	private long fact(int num) {
		if (num == 1 || num ==0)
			return 1;
		else
			return num * fact(num-1);
	}

	private void getPermutation(int[] nums) {
		getPermutations(nums, 0, nums.length-1);
	}

	private void getPermutations(int[] nums, int start, int end) {
		int digitsToRotate = end - start + 1;

		if ( digitsToRotate == 1) {
			doTheStuff(nums);
		}
		else {
			for (int i=0; i < digitsToRotate; i++) {
				rotateLeft(nums, digitsToRotate);
				getPermutations(nums, start+1, end);
			}
		}
	}

	private void doTheStuff(int [] nums){
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += Math.max(nums[i], actualArray[i]);
		}

		if (total >= K) {
			totalPairs++;
		}
	}

	private void rotateLeft(int[] nums, int digits) {
		int start = nums.length - digits;
		int end = nums.length - 1;
		int temp = nums[start];

		for (int i=start; i < end; i++) {
			nums[i] = nums[i+1];
		}

		nums[end] = temp;
	}
}
