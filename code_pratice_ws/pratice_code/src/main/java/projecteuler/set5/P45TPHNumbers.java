package projecteuler.set5;

import java.util.HashSet;
import java.util.Set;

public class P45TPHNumbers {

	private Set<Long> triangleNos;
	private Set<Long> pentagNos;

	public long getNextTriangleNumber(int range) {

		initNos(range);

		long nextNum = -1;

		boolean found = false;
		for (int i = 144; i < range; i++) {
			nextNum = i * ((long)2 * i - 1);

			if (triangleNos.contains(nextNum) && pentagNos.contains(nextNum)) {
				found = true;
				System.out.println("found at i = " + i);
				break;
			}
		}

		if (!found)
			return -1;

		return nextNum;
	}

	private void initNos(int range) {
		triangleNos = new HashSet<>();

		long num;
		for (int i = 286; i < range; i++) {
			num = i * ((long)i + 1);
			num /= 2;
			triangleNos.add(num);
		}

		pentagNos = new HashSet<>();

		for (int i = 166; i < range; i++) {
			num = i * ((long) 3*i - 1);
			num /= 2;
			pentagNos.add(num);
		}
	}
}
