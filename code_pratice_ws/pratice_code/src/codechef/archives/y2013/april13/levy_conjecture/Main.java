package codechef.archives.april13.levy_conjecture;

import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();
	}

}

class TaskA {

	int [] primes;

	Set<Integer> primeSet = new HashSet<Integer>();

	Map<Integer, Integer> noVsOrderedPair = new HashMap<Integer, Integer>();

	public void solve(Scanner in, PrintWriter out) {

		generatePrimes(10000);

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int num = in.nextInt();

			if (num < 6)
				out.println("0");
			else {
				Integer orderedPair = noVsOrderedPair.get(num);
				if (orderedPair == null) {
					orderedPair = getOrderedPair(num);
					noVsOrderedPair.put(num, orderedPair);
				}
				out.println(orderedPair);
			}
		}

	}

	private int getOrderedPair(int n) {
		int orderedPairs = 0;

		for (int i=0;i<primes.length;i++) {
			if (primes[i] > n) {
				break;
			}

			int find = n - primes[i];
			if (find %2==0) {
				find /= 2;

				if (primeSet.contains(find))
					orderedPairs++;
			}
		}

		return orderedPairs;
	}


	private void generatePrimes(int range) {
		List<Integer> primeNos = new ArrayList<Integer>();

		primeNos.add(2);

		int num = 3;

		while (num < range) {
			boolean isPrime = true;

			for (int prime : primeNos) {

				if (prime > num/prime)
					break;

				if (num%prime==0) {
					isPrime = false;
					break;
				}
			}


			if (isPrime)
				primeNos.add(num);

			num += 2;
		}

		for (int prime : primeNos)
			primeSet.add(prime);

		primes = new int[primeNos.size()];
		for (int i=0;i<primes.length;i++)
			primes[i] = primeNos.get(i);
	}

}

