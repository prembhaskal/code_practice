package practice.algorithm;

public class PrintCombination {

	private int totalCallsMade;
	private int totalCombinations;
	private boolean print;

	public void printCombination(int[] nums, int sizeRange, boolean print) {
		this.print = print;
		boolean[] mask = new boolean[nums.length];
		totalCombinations = 0;
		totalCallsMade = 0;

		getCombination(nums, mask, 0, 0, sizeRange);

		System.out.println("total Combinations are " + totalCombinations);
		System.out.println("total calls made are " + totalCallsMade);
	}

	private void getCombination(int[] nums, boolean[] mask, int idx, int size, int maxSize) {
		totalCallsMade++;

		// stop early, if we cannot meet the maxSize requirement.
		int remElements = nums.length - idx;
		if (size + remElements < maxSize)
			return;

		if (size == maxSize) {
			totalCombinations++;
			if (print)
				doStuff(nums, mask);
			return;
		}

		if (idx == nums.length)
			return; // no more elements to select from.


		// choose this number
		mask[idx] = true;
		getCombination(nums, mask, idx+1, size+1, maxSize);

		// don't choose this number
		mask[idx] = false;
		getCombination(nums, mask, idx+1, size, maxSize);
	}

	private void doStuff(int[] nums, boolean[] mask) {
		for (int i = 0; i < nums.length; i++) {
			if (mask[i])
				System.out.print(nums[i] + " ");
		}
		System.out.println("");
	}
}
