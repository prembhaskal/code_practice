package codechef.march14.mike3;

import java.io.*;
import java.util.Arrays;
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
	private int M;

	private int maxOffersSelected;
	private boolean[][] offers;
	// if jth bit is set means, the ith offer and jth offer cannot be together.
	private int[] exclusionsBits;

	private boolean STOP = false;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		N = in.nextInt() + 1;
		M = in.nextInt();

		offers = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			int k = in.nextInt();
			int a[] = new int[k];
			for (int j = 0; j < k; j++) {
				a[j] = in.nextInt();
				offers[i][a[j]] = true;
			}
		}

		// offers till now.
//		boolean[] offersTillNow = new boolean[N];
//		chooseOrNot(0, offersTillNow, 0);

		solve2();

		int accepted = maxOffersSelected;
		out.println(accepted);
	}

	// BRUTE FORCE ... it times out AS Expected :( :(
	private void chooseOrNot(int offerNo, boolean[] offersTillNow, int offersSelected) {
		if (STOP)
			return;

		if (offerNo == M) {
			maxOffersSelected = Math.max(offersSelected, maxOffersSelected);
			if (maxOffersSelected == M)
				STOP = true; // stop when maximum possible offers are selected.
			return;
		}

		boolean[] currentOffer = offers[offerNo];

		// try to choose current offer.
		boolean canChoose = true;
		int modifiedTill = 0;

		for (int i = 1; i < N; i++) {
			if (offersTillNow[i] & currentOffer[i]) {
				canChoose = false;
				break;
			}

			offersTillNow[i] = offersTillNow[i] | currentOffer[i];
			modifiedTill = i;
		}

		if (canChoose) {
			chooseOrNot(offerNo + 1, offersTillNow, offersSelected + 1);
		}

		// revert the actual condition... till only the bits modified...formula will work only then.
		for (int i = 1; i <= modifiedTill; i++) {
			offersTillNow[i] = offersTillNow[i] & (!currentOffer[i]);
		}

		// try by not selecting the offer
		chooseOrNot(offerNo + 1, offersTillNow, offersSelected);
	}


	// well this was accepted and it is quite very fast :) :)
	private void solve2() {
		/*
			Algorithm:
			find mutual exclusions present for each of the offer.
			brute force with the above information...it would be faster.
		*/

		exclusionsBits = new int[M];

		int myShiftedBit = 1;
		for (int i = 0; i < M; i++) {
			boolean[] currentOffer = offers[i];

			int otherShiftedBit = myShiftedBit; // setting the set bit of other offer to present initially.

			for (int j = i; j < M; j++) {
				boolean clash = false;
				boolean[] compareOffer = offers[j];

				for (int k = 1; k < N; k++) {
					clash = compareOffer[k] & currentOffer[k];
					if (clash)
						break;
				}

				// mark this node if clash.
				if (clash) {
					exclusionsBits[i] = exclusionsBits[i] | otherShiftedBit;

					// also mark the exclusion bit for the reverse comparison
					exclusionsBits[j] = exclusionsBits[j] | myShiftedBit;
				}

				otherShiftedBit = otherShiftedBit << 1;
			}

			myShiftedBit = myShiftedBit << 1;
		}

		// now brute force using this exclusion bits.
		chooseExclusionBit(0, 0, 0);
	}

	private void chooseExclusionBit(int offer, int selectedBit, int totalSelected) {
		if (offer == M) {
			maxOffersSelected = Math.max(totalSelected, maxOffersSelected);
			if (maxOffersSelected == M)
				STOP = true; // stop when maximum possible offers are selected.
			return;
		}

		int exclusionBit = exclusionsBits[offer];

		int canChoose = selectedBit & exclusionBit;

		// check if we can choose the offer.
		if (canChoose == 0) { // means there is no clash
			// select the bit for this offer.
			int nextSelectedBit = selectedBit | (1 << offer);
			chooseExclusionBit(offer + 1, nextSelectedBit, totalSelected + 1);
		}

		// try by not choosing the offer.
		chooseExclusionBit(offer + 1, selectedBit, totalSelected);
	}

	private int getMaxOffers() {

		return 0;
	}

	private class Offer implements Comparable<Offer> {
		int min;
		int max;
		boolean used;

		public Offer(int min, int max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public int compareTo(Offer o) {
			return max < o.max ? -1 : ((max == o.max) ? 0 : 1);
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
