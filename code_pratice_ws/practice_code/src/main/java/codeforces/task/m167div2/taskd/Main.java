package codeforces.task.m167div2.taskd;

import java.io.*;
import java.util.*;

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

class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int n = in.nextInt();
		int m = in.nextInt();

		int[][] wepon = new int[n][2];
		// 0 - wepon and 1 - melt
		for (int i=0;i<n;i++) {
			wepon[i][0] = in.nextInt();
		}
		for (int i = 0; i < n; i++) {
			wepon[i][1] = in.nextInt();
		}

		int[] metals = new int[m];
		for (int i = 0; i < m; i++) {
			metals[i] = in.nextInt();
		}

		// for wepon's with same difference, keep only the 1 with lower build
		Map<Integer, Integer> diffVsBuild = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int build = wepon[i][0];
			int melt = wepon[i][1];
			int diff = build - melt;
			Integer exist = diffVsBuild.get(diff);
			if (exist == null) {
				diffVsBuild.put(diff, i);
				continue;
			}
			if (build < wepon[exist][0]) {
				diffVsBuild.put(diff, i);
			}
		}
		int[][] refinedWepons = new int[diffVsBuild.size()][2];
		int idx = 0;
		for (Map.Entry<Integer, Integer> entry : diffVsBuild.entrySet()) {
			refinedWepons[idx][0] = wepon[entry.getValue()][0];
			refinedWepons[idx][1] = wepon[entry.getValue()][1];
			idx++;
		}

		// sort the wepon sorted by (wepon-melt)
		Arrays.sort(refinedWepons, (a, b) -> {
			return Integer.compare(a[0] - a[1], b[0] - b[1]);
        });

		// for each metal, try for each weapon
		long totalexp = 0;
		for (int i = 0; i < m; i++) {
			int rem = metals[i];
			for (int j = 0; j < refinedWepons.length; j++) {
				int build = refinedWepons[j][0];
				int melt = refinedWepons[j][1];
				if (rem >= build) {
					// rebuild > (rem - build)/(build-melt) hence +1 in next line
					int rebuilds = (rem - build) / (build - melt);
					rebuilds = rebuilds + 1;
                    totalexp = totalexp + (2 * rebuilds);
					rem = rem - (rebuilds * (build- melt));
				}
			}
//			out.printf("for metal %d, totalexp %d\n", metals[i], totalexp);
		}
		out.println(totalexp);
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
