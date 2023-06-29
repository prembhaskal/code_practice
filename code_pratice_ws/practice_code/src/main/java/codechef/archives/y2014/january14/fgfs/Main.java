package codechef.january14.fgfs;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	private int N;
	private Customer[] customers;
	private int K;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			K = in.nextInt();

			customers = new Customer[N];

			for (int j = 0; j < N; j++) {
				int s = in.nextInt();
				int f = in.nextInt();
				int p = in.nextInt();
				customers[j] = new Customer(s, f, p);
			}


			int customersServed = getServedCustomers();
			out.println(customersServed);
		}

	}

	private int getServedCustomers() {
		// GO GREEDY. hope this works.
		// sort the given customers by departure time.
		Arrays.sort(customers);

		int previousCompart = -1;
		int presentCompart;

		int totalServed = 0;

		List<Customer> customerList = new ArrayList<Customer>();
		for (Customer customer : customers) {
			presentCompart = customer.p;

			if ((previousCompart != -1) && (previousCompart != presentCompart)) {
				totalServed += getCustomersForCompartment(customerList);
				customerList.clear();
				customerList.add(customer);
			}
			else {
				customerList.add(customer);
			}

			previousCompart = presentCompart;
		}

		// check for the remaining customers.
		totalServed += getCustomersForCompartment(customerList);

		return totalServed;
	}

	private int getCustomersForCompartment(List<Customer> customerList) {
		int servedCust = 0;
		Customer previousCust = null;

		for (Customer presentCust : customerList) {
			if (previousCust == null) {
				servedCust++;
				previousCust = presentCust; // change only when we used this.
			}
			else {
				// take this customer if he is not overlapping previous guy, else skip him.
				if (presentCust.s >= previousCust.f) {
					servedCust++;
					previousCust = presentCust; // change only when we used this.
				}
			}
		}

		return servedCust;
	}


	private class Customer implements Comparable<Customer>{
		public int s;
		public int f;
		public int p;

		public Customer(int s, int f, int p) {
			this.s = s;
			this.f = f;
			this.p = p;
		}

		@Override
		public int compareTo(Customer o) {
			// check if preferences are the same.
			if (p < o.p)
				return -1;
			if (p > o.p)
				return 1;

			// sort by departure time.
			if (f < o.f)
				return -1;
			if (f > o.f)
				return 1;

			return 0;
		}

		@Override
		public String toString() {
			return "s --> " + s + "   f --> " + f + "   p --> " + p;
		}
	}

}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
