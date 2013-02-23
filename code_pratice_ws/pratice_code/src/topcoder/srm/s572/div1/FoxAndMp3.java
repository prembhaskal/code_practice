package topcoder.srm.s572.div1;

import java.util.Arrays;

public class FoxAndMp3 {

	int count;

	public String[] playList(int n) {

		int[] nums = new int[50];

		int num = 0;

		recursive(nums, num, n);

		String[] strings;
		if (n > 50)
			strings = new String[50];
		else
			strings = new String[n];

		int m = (n>50 ? 50:n);

		for (int i=0;i<m;i++)
			strings[i] = nums[i] + ".mp3";

		return strings;
	}

	private void recursive(int[] nums, int num, int n) {
		if (count == 50)
			return;
		if (num > n)
			return;

		if (num > 0)
			nums[count++] = num;

		for (int i=0;i<10;i++) {
			if(num==0 && i==0) continue;
			recursive(nums, num*10 + i, n);
		}
	}
}
