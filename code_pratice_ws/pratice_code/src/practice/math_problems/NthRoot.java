package practice.math_problems;

public class NthRoot {

	public long findNthRootNewtonMethod(long num, int root, boolean print) {
		double x = 1.0;
		int iteration = 1;
		double PRECISION = 0.5;

		while (true) {
			double power = powDouble(x, root - 1);
			double delta = (num / power - x) / root;

			if (Math.abs(delta) < PRECISION) {
				break;
			}

			x = x + delta;
			iteration++;
		}

		if (print)
			System.out.println("INFO - total iterations taken " + iteration);

		return (long) x;
	}


	private double powDouble(double num, int pow) {
		double power = 1;

		while (pow > 0) {
			if ((pow&1) == 1) {
				power = (power * num);
			}
			num = (num * num);
			pow /= 2;
		}

		return power;
	}

	public double getSquareRoot(int num) {
		double precision = 0.01;
		double x = 1.0;
		int iteration = 0;
		while (true) {
			double next = (num/x + x)/2;
			double delta = next - x;

			if (Math.abs(delta) < precision) {
				break;
			}

			iteration++;
			x = next;
		}

		System.out.println("INFO: total iterations are " + iteration);

		return x;
	}

 	public int findIntegerRootUsingBinarySearch(long num, int root) {

		int low = 1;
		int high = findHighestExp(root);
		int mid = 1;

		while (low <= high) {
			mid = (high - (high - low)/2);

			long power = pow(mid, root);
			if (power == num) {
				break;
			}

			if (power > num) {
				high = mid - 1;
			}
			else {
				// check if we have reached CEIL of root.
				long nextPower = pow(mid + 1, root);
				if (nextPower > num) {
					break;
				}

				low = mid + 1;
			}

		}

		return mid;
	}
	//TODO. THIS METHOD IS INCORRECT..
	public int findHighestExp(int root) {
		double max = Long.MAX_VALUE;
		double logx_into_root = Math.log10(max)/root;

		// power of 10 could go wrong with even a small inaccuracy in the decimal part of logx_into_root.
		double exp = Math.pow(logx_into_root, 10);

		return (int) exp;
	}


	public long pow(long num, int pow) {
		long power = 1;

		while (pow > 0) {
			if ((pow&1) == 1) {
				power = (power * num);
			}
			num = (num * num);
			pow /= 2;
		}

		return power;
	}

	public long powNaive(long num, int pow) {
		long power = 1;
		for (int i = 0; i < pow; i++) {
			power = power * num;
		}
		return power;
	}

}
