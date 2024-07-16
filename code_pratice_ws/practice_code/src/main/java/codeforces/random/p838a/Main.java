package codeforces.random.p838a;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			Task solution = new Task();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// https://codeforces.com/problemset/problem/838/A
class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int n = in.nextInt();
		int m = in.nextInt();
		int[][] pixels = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = in.next();
			char[] linechs = line.toCharArray();
			for (int j = 0; j < m; j++) {
				pixels[i][j] = linechs[j] == '1' ? 1 : 0;
			}
		}

		// precalculate to speed up stuff.
		int[][] pixeldp = new int[n][m];
		for (int i = 0; i < n ; i++) {
			int col = 0;
			for (int j = 0; j < m; j++) {
				col = pixels[i][j] + col;
				pixeldp[i][j] = col;
				if (i > 0) {
					pixeldp[i][j] += pixeldp[i-1][j];
				}
			}
//			System.out.printf("i:%d, -->:%s\n", i, Arrays.toString(pixeldp[i]));
		}

		int minToggles = n * m;
		int l = Math.max(n, m);
		for (int k = 2; k <= l; k++) {
//			int toggles1 = toggleBits(n, m, k, pixels);
			int toggles = toggleBits1(n, m, k, pixeldp);
//			System.out.printf("k :%d, toggles: %d, toggles1: %d\n", k, toggles, toggles1);
//			if (toggles > minToggles) {
//				break;
//			}
			minToggles = Math.min(minToggles, toggles);
		}
		out.println(minToggles);
	}

	int toggleBits1(int n, int m, int k, int[][] pixeldp) {
		int toggles = 0;
		int ksq = k * k;
		for (int i = 0; i < n; i += k) {
			for (int j = 0; j < m; j += k) {
				int ones = getParity1(n, m, i , j, k, pixeldp);
				int zeroes = ksq - ones;
				toggles = toggles + Math.min(ones, zeroes);
			}
		}
		return toggles;
	}
//	P1  P4
//	P2  P3
	int getParity1(int n, int m, int r, int c, int k, int[][] pixeldp) {
		int r2 = Math.min(r + k - 1 , n - 1);
		int c2 = Math.min(c + k - 1, m - 1);
		int p1 = 0;
		if (r > 0 && c > 0) {
			p1 = pixeldp[r-1][c-1];
		}
		int p2 = 0;
		if (c > 0) {
			p2 = pixeldp[r2][c-1];
		}
		int p3 = pixeldp[r2][c2];
		int p4 = 0;
		if (r > 0) {
			p4 = pixeldp[r-1][c2];
		}
		return p3 - p4 - p2 + p1;

//		int r2 = Math.min(r + k, n - 1); int c2 = c;
//		int r3 = Math.min(r + k, n - 1); int c3 = Math.min(c + k, m - 1);
//		int r4 = r; int c4 = Math.min(c + k, m - 1);
//		return pixeldp[r3][c3] - pixeldp[r4][c4] - pixeldp[r2][c2] + pixeldp[r1][c1];
	}

	int toggleBits(int n, int m , int k, int[][] pixels) {
		int toggles = 0;
		int ksq = k * k;
		for (int i = 0; i < n; i += k) {
			for (int j = 0; j < m; j+= k) {
				int ones = getParity(pixels, n, m, i, j, k);
				int zeroes = ksq - ones;
				toggles = toggles + Math.min(ones, zeroes);
			}
		}
		return toggles;
	}

	// col[r,c] is start point
	int getParity(int [][] pixels, int n, int m, int r, int c, int k) {
		int parity = 0;
		for (int i = 0; i < k; i++) {
			int pr = r + i;
			if (pr >= n) {
				break;
			}
			for (int j = 0; j < k; j++) {
				int pc = c + j;
				if (pc >= m) {
					break;
				}
				parity = parity + pixels[pr][pc];
			}
		}
		return parity;
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
