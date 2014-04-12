package google_code_jam.cjam_14;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class MagicTrick {

	private int[][] nums;
	private int[][] newNums;

	private int ans1;
	private int ans2;

	public void solve(InputReader in, PrintWriter out) throws Exception {
		int tests = in.nextInt();

		for (int testNo = 1; testNo <= tests; testNo++) {
			nums = new int[4][4];
			newNums = new int[4][4];


			ans1 = in.nextInt();
			ans1--;

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					nums[j][k] = in.nextInt();
				}
			}

			ans2 = in.nextInt();
			ans2--;

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					newNums[j][k] = in.nextInt();
				}
			}

			String result = getTheNumber();
			out.println("Case #" + testNo + ": " + result);
		}
	}

	private String getTheNumber() {

		Set<Integer> firstNos = new HashSet<>();

		for (int i = 0; i < 4; i++) {
			firstNos.add(nums[ans1][i]);
		}

		int matchCount = 0;
		int matchedNum = -1;
		for (int i = 0; i < 4; i++) {
			int num = newNums[ans2][i];
			if (firstNos.contains(num)) {
				matchCount++;
				matchedNum = num;
			}
		}

		if (matchCount == 1)
			return matchedNum + "";
		else if (matchCount == 0)
			return "Volunteer cheated!";
		else
			return "Bad magician!";

	}
}
