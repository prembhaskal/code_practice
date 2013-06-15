package codechef.june13.wstring;

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

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String str = in.next();

			int maxLen = getMaxWStringLength(str);

			out.println(maxLen);
		}

	}

	private int getMaxWStringLength(String str) {
		char [] strArray = str.toCharArray();
		int noOfHashs = 0;
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i]=='#')
				noOfHashs++;
		}

		if (noOfHashs < 3)
			return 0;

		// maxSectionBtw has section vs max length in array.
		int[] maxSectionBtw = new int[noOfHashs+1];

		int secIndex = 0;
		int[] chCount = new int[26];
		Arrays.fill(chCount, 0);
		int maxChCount = 0;

		// create the section map for the whole string.
		for (int i=0;i<strArray.length;i++) {
			char ch = strArray[i];
			if (ch=='#') {// new section starts
				maxSectionBtw[secIndex] = maxChCount;
				secIndex++;// next section
				maxChCount = 0; // reset max for next section.
				Arrays.fill(chCount, 0);
			} else {
				int idx = (int)ch - (int)'a';
				chCount[idx]++;
				// get the max out of the chCount.
				maxChCount = Math.max(chCount[idx], maxChCount);
			}
		}
		// set for the last section.
		maxSectionBtw[secIndex] = maxChCount;

		// create the forward section.
		// find max occurrence of any character till here FORWARD
		Arrays.fill(chCount, 0);
		int[] maxSectionFwd = new int[noOfHashs];
		secIndex = 0;
		maxChCount = 0;
		for (int i = 0; i < strArray.length; i++) {
			char ch = strArray[i];
			if (ch=='#') {
				maxSectionFwd[secIndex] = maxChCount;
				secIndex++;
			} else {
				int idx = (int)ch - (int)'a';
				chCount[idx]++;
				// get the max out of the chCount.
				maxChCount = Math.max(chCount[idx], maxChCount);
			}
		}

		// create the reverse section.
		// find max occurrence of any character till here REVERSE
		Arrays.fill(chCount,0);
		int[] maxSectionRev = new int[noOfHashs];
		secIndex = noOfHashs - 1;
		maxChCount = 0;
		for (int i=strArray.length-1;i>=0;i--) {
			char ch = strArray[i];
			if (ch=='#') {
				maxSectionRev[secIndex] = maxChCount;
				secIndex--;
			} else {
				int idx = (int)ch - (int)'a';
				chCount[idx]++;
				maxChCount = Math.max(chCount[idx], maxChCount);
			}
		}

		// TODO this is wrong as per question.
		// TODO we should choose 3 continuous Hashes '#' instead. (with no Hashes in between).
		// i.e. a#ab#ax#ay#cc#dd#ee#f should return 13 --> aaaa#cc#dd#ee
		int maxStrLength = 0;
		for (int i=0;i<noOfHashs-3+1;i++) {
			int secLength = 0;
			// max[i] = mf[i] + mb[i+1] + mb[i+2] + mr[i+2]
			boolean hasZeroLengthSec = false;
			secLength = maxSectionFwd[i] + maxSectionBtw[i+1] + maxSectionBtw[i+2] + maxSectionRev[i+2];
			if (maxSectionFwd[i]==0 || maxSectionBtw[i+1]==0 || maxSectionBtw[i+2]==0 || maxSectionRev[i+2]==0) {
				hasZeroLengthSec = true;
				secLength = 0;
			}

			maxStrLength = Math.max(maxStrLength, secLength);
		}

		// add the 3 hashes for the max length
		maxStrLength += 3;

		if (maxStrLength < 4)
			maxStrLength = 0;

		return maxStrLength;
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
