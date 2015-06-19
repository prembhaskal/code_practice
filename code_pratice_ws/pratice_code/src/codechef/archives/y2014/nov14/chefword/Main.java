package codechef.nov14.chefword;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

// TODO this did not work. (not even the brute force) :) :)
// input was list of string possibly duplicates :( :(
// learn matrix multiplication and squaring...matrix multiplication applies pretty straight forward here.
// prob_matrix ^ K , gives the required probability. FINISH. for each ch find and output.
// concept of probability was correct though :)
class TaskA {

	private int N;
	private int K;

	private double[][] probs;

	private String chefWord;
	private String[] words;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			K = in.nextInt();

			chefWord = in.next();

			probs = new double[26][26];
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 26; k++) {
					probs[j][k] = in.nextDouble();
				}
			}

			words = new String[N];
			for (int j = 0; j < N; j++) {
				words[j] = in.next();
			}


			double probability;
			probability = getProb();
			out.println(probability);
//			probability = getProbBruteForce();
//			out.println(probability);
		}

	}

	private double getProb() {
		// prob for each character at each location.
		int len = chefWord.length();
		double[][] charProb = new double[len][26];

		int chIdx = 0;
		for (char ch : chefWord.toCharArray()) {
			int idx = ch - 97;

			double[] previous = new double[26];
			previous[idx] = 1.0;
			double[] next = new double[26];
			double[] temp;

//			K = Math.min(K, 10000); // well this K is enough for passing. though ideally it should be matrix multiplication.
			for (int i = 0; i < K; i++) {

				for (int j = 0; j < 26; j++) {
					double prob = 0; // prob of getting j.
					for (int k = 0; k < 26; k++) {
						prob += (previous[k] * probs[k][j]);
					}

					if (prob > 1.0)
						prob = 1.0;

					next[j] = prob;
				}

				// if result is not changing stop right there. (since it won't change anymore).
				boolean changed = false;
				for (int j = 0; j < 26; j++) {
					if (Math.abs(previous[j] - next[j]) > 0.000001) {
						changed = true;
						break;
					}
				}

				// next iteration
				temp = previous;
				previous = next;
				next = temp;
				Arrays.fill(next, 0);

				if (!changed) {
					break;
				}
			}

			charProb[chIdx++] = previous;
		}

		double totalProb = 0;
		Set<String> uWords = new HashSet<>();
		for (String word : words) {// Stupid question...one problem was this..duplicate inputs.
			if (!uWords.add(word))
				continue;
			double prob = 0.0;
			if (word.length() == len) {
				prob = 1.0;
				for (int i = 0; i < len; i++) {
					int ch = word.charAt(i);
					prob = prob * charProb[i][ch - 97];
				}
			}

			totalProb += prob;
		}

		if (totalProb > 1.0)
			totalProb = 1.0;

		return totalProb;
	}

	private double getProbBruteForce() {
		if (K > 3)
			return 0;
		int len = chefWord.length();
		double[][] probsAfterK = new double[len][26];

		for (int i = 0; i < len; i++) {
			int ch = chefWord.charAt(i);
			ch = ch - 97;
			updateProb(ch, 1, 1.0, probsAfterK[i]);
		}

		double totalProb = 0;
		Set<String> uWords = new HashSet<>();
		for (String word : words) {
			if (!uWords.add(word))
				continue;
			double prob = 0.0;
			if (word.length() == len) {
				prob = 1.0;
				for (int i = 0; i < len; i++) {
					int ch = word.charAt(i);
					prob = prob * probsAfterK[i][ch - 97];
				}
			}

			totalProb += prob;
		}

		if (totalProb > 1.0)
			totalProb = 1.0;

		return totalProb;
	}

	private void updateProb(int ch, int claps, double prob, double[] placeProbs) {
		if (claps > K) {
			placeProbs[ch] += prob;
			return;
		}

		for (int i = 0; i < 26; i++) {
			updateProb(i, claps + 1, prob * probs[ch][i], placeProbs);
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
