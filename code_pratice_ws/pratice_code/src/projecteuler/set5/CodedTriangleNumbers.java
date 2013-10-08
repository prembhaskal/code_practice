package projecteuler.set5;

import common.util.InputReader;
import java.util.HashSet;
import java.util.Set;

public class CodedTriangleNumbers {

	private String[] words;
	private int count;

	private Set<Integer> triangleNos;

	public int getCountOfTriangledNumbers(InputReader in) {
		count = in.nextInt();
		words = new String[count];

		for (int i = 0; i < count; i++) {
			words[i] = in.next();
		}

		generateTriangleNumbers(20);

		int totalTriangleNos = 0;

		for (String word : words) {
			if (isTriangleWord(word))
				totalTriangleNos++;
		}

		return totalTriangleNos;
	}

	private boolean isTriangleWord(String word) {
		char[] chars = word.toCharArray();
		int sum = 0;
		for (char ch : chars) {
			sum = sum + (ch - 65 + 1);
		}

		if (triangleNos.contains(sum))
			return true;

		return false;
	}

	private void generateTriangleNumbers(int range) {
		triangleNos = new HashSet<>();

		for (int i = 1; i <= range; i++) {
			int prod = i * ( i + 1);
			prod /= 2;

			triangleNos.add(prod);
		}
	}
}
