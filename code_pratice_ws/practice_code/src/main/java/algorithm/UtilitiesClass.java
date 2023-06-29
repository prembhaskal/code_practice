package algorithm;

import java.util.Random;

public class UtilitiesClass {

	public static long startTime() {
		return System.nanoTime();
	}

	public static void printTimeElapsed(long starttime) {
		long now  = System.nanoTime();
		System.out.println("elapsed time " + (now-starttime)/1000 + " mi" +
				"" +
				"cro-secs");
	}

	public static void printArray(int[] nums) {
		for(int i : nums) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void printArrayNoSpaces(int[] nums) {
		for(int i : nums) {
			System.out.print(i + "");
		}
		System.out.println();
	}

	public static void fillArray(int[] nums) {
		int start = nums.length;
		for (int i=0;i<nums.length;i++) {
			nums[i] = start--;
		}
	}

	public static void fillArrayWithHalfSorted(int[] nums) {

		// first part is sorted
		for(int i=0;i<nums.length/2;i++)
			nums[i] = i;

		// reverse ordered from middle
		int start = nums.length;
		for(int i=nums.length/2;i<nums.length;i++)
			nums[i] = start--;
	}


	// array has both +ve and -ve numbers in the range.
	public static int[] getRandomArray(int size, int range) {
		int[] num = new int[size];

		Random random = new Random();
		Random evenOrOdd = new Random();

		for (int i=0;i<size;i++) {
			int rnd = random.nextInt(range);

			if (evenOrOdd.nextInt(2)==0)
				rnd = rnd * -1;

			num[i] = rnd;
		}

		return num;
	}
}
