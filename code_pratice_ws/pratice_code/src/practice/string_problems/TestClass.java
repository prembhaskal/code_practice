package practice.string_problems;

public class TestClass {

	public static void main(String[] args) {

		// print combinations.
		new TestClass().printCombinations();
	}


	public void printCombinations() {
		int[] primes = new int[]{51,53,59,61,67,69,71,73,79,83,89,97};

		for (int i = 0; i < primes.length; i++) {
			for (int j = 0; j < primes.length; j++) {
				if (i != j) {
					System.out.println(primes[i] + "" + primes[j]);
				}
			}
		}
	}
}
