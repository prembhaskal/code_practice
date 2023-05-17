package codechef.archives.december13.chode;

import java.io.*;
import java.util.Arrays;

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

	String freqSeq;
	String sequence;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			freqSeq = in.next();
			sequence = in.next();
			String finalStr = getDecryptedSequence();
			out.println(finalStr);
		}

	}

	private String getDecryptedSequence() {
		char[] freqSeqs = freqSeq.toCharArray();

		// make a frequency counter
		Letter[] letterCounter = new Letter[26];
		for (int i = 0; i < 26; i++) {
			letterCounter[i] = new Letter(i, 0);
		}

		char[] seqChars = sequence.toCharArray();
		// converting to array is somehow faster than String.charAt()...don't know why
		for (int i = 0; i < seqChars.length; i++) {
			char ch = seqChars[i];
			if (ch >= 'A' && ch <= 'Z')
				letterCounter[ch-'A'].count++;
			else if (ch >= 'a' && ch <= 'z')
				letterCounter[ch-'a'].count++;
			// don't do nothing for any other characters.
		}

		Arrays.sort(letterCounter);

		// map the alphabet...actual to frequency.
		char[] mapLetters = new char[26];
		for (int i = 25; i >= 0; i--) {
			char freqch = freqSeqs[i];
			char actCh = (char) (letterCounter[i].ch);

			mapLetters[actCh] = freqch;
		}

		// form the final string, use the mapped alphabet for this.
		char[] finalChars = new char[seqChars.length];
		for (int i = 0; i < finalChars.length; i++) {
			char ch = seqChars[i];
			if (ch >= 'a' && ch <= 'z')
				ch = mapLetters[ch - 'a'];
			else if (ch >= 'A' && ch <= 'Z')
				ch = (char)(mapLetters[ch - 'A'] - 32); // handle capital letters.
			finalChars[i] = ch;
		}

		String finalStr = new String(finalChars);
		return finalStr;
	}

	private class Letter implements Comparable<Letter>{
		int ch;
		int count;

		public Letter(int ch, int count){
			this.ch = ch;
			this.count = count;
		}

		@Override
		public int compareTo(Letter o) {
			if (this.count < o.count)
				return -1;
			else if (this.count > o.count)
				return 1;
			else {
				if (this.ch < o.ch)
					return -1;
				else if (this.ch > o.ch)
					return 1;
				else
					return 0;
			}
		}
	}


}

class InputReader {
	public BufferedReader reader;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	public String next() {
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}

		return line;
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

}
