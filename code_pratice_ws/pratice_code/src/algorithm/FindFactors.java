package algorithm;

public class FindFactors {


	public static void printFactors(int num) {
		int i;
		for (i=2;i<=num;i++) {
			if (num%i==0) {

				while (num%i==0) {
					num /= i;
				}

				System.out.println(i);
			}
		}

		if (num > 1) {
			System.out.println(i);
		}
	}
}
