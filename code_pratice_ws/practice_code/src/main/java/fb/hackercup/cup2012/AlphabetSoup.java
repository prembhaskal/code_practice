package fb.hackercup.cup2012;

public class AlphabetSoup {

	public String solve(String text, int tcase) {
		char[] array = text.toCharArray();
		// H A C K E R C U P .... A C E H K R P U ... 8 chars

		int[] numArray = new int[8];


		for (char c : array) {
			switch (c) {
				case 'A':numArray[0]++;break;
				case 'C':numArray[1]++;break;
				case 'E':numArray[2]++;break;
				case 'H':numArray[3]++;break;
				case 'K':numArray[4]++;break;
				case 'R':numArray[5]++;break;
				case 'P':numArray[6]++;break;
				case 'U':numArray[7]++;break;
				default:;
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i=0;i<8;i++) {
			if (min > numArray[i])
				min = numArray[i];
		}

		int cs = numArray[1];

		int words = Math.min(min, cs/2);

		System.out.println("Case #"+tcase + ": " + words);

		return "Case #"+tcase + ": " + words;
	}
}
