package practice.math_problems;

public class SatishPuzzle {

	public void solve() {
		boolean found = false;
		// i, j, k are price
		start:
		for (int i = 5; i <= 100; i += 5) {//1 item for 5 rupees
			for (int j = 1; j <= 100; j++) {// 20 item for 1 rupees
				int k = 100 - i - j; // 1 item 1 rupee

				if ( k <= 0)
					continue;

				int total_items = i/5 + 20 * j + k;
				if (total_items == 100) {
					found = true;
					System.out.println("found");
					System.out.println(" i --> " + i );
					System.out.println(" j --> " + j );
					System.out.println(" k --> " + k );
					break start;
				}
			}
		}

		if (!found)
			System.out.println("did not find :( :(");
	}
}
