package projecteuler.set1;

public class P9pythagoreantriplets {

	public void getTriplets() {
		boolean stop = false;

		for (int a=1;a<1000;a++) {
			for (int b=1;b<1000;b++) {
				int value = 2 * 1000 * (a + b) - (2 * a * b);

				if (value==1000000) {
					int c = 1000 - a - b;
					System.out.println("a is " + a + " and b is " + b + " and c is " + c);
					int prod = a * b * c;
					System.out.println("product abc is " + prod);
					stop = true;
					break;
				}
			}

			if (stop)
				break;
		}
	}
}
