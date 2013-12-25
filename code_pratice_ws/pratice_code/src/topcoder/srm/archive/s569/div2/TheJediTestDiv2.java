package topcoder.srm.archive.s569.div2;

public class TheJediTestDiv2 {

	public int countSupervisors(int[] students, int Y, int J) {
		int count = 0;

		int[] jediCount = new int[students.length];
		int total = 0;

		int val;
		for (int i=0;i<jediCount.length;i++) {
			val = students[i]/J;
			if (students[i]%J!=0)
				val++;
			jediCount[i]=val;
			total += val;
		}

		int min = Integer.MAX_VALUE;
		int value = 0;
		for (int i=0;i<jediCount.length;i++) {
			int rem = students[i] - Y;
			if (rem > 0) {
				value = rem/J;
				if (rem%J!=0)
					value++;
			}

			count = total - jediCount[i] + value;

			min = Math.min(min,count);
		}

		return min;
	}


	public int countSuperVisorWay2(int[] students, int Y, int J) {
		int count = 0;

		int total = 0;
		int[] jediCount = new int[students.length];

		for (int i=0;i<students.length;i++) {
			jediCount[i] = (students[i] + J - 1)/J;
			total += jediCount[i];
		}

		int min = Integer.MAX_VALUE;

		for (int i=0;i<students.length;i++) {
			int rem = students[i] -Y;
			int value = 0;
			if (rem > 0) {
				value = (rem + J - 1)/J;
			}

			min = Math.min(min, total - jediCount[i] + value);
		}

		return min;
	}


	// NAIVE 0(N^2) METHOD

	public int countSupervisorsWay3(int[] students, int Y, int J) {

		int count = Integer.MAX_VALUE;

		for (int i=0;i<students.length;i++) {
			int ans = 0;
			for (int j=0;j<students.length;j++) {
				if (i!=j) {
					ans += (students[j] + J - 1)/J;
				} else {
					int rem = students[j] - Y;
					if (rem > 0) {
						ans += (rem + J-1)/J;
					}
				}
			}

			count = Math.min(ans, count);
		}

		return count;
	}
}
