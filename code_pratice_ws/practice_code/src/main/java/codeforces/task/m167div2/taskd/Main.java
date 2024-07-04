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
		int maxBuild = 0;
		for (int i=0;i<n;i++) {
			wepon[i][0] = in.nextInt();
			maxBuild = Math.max(maxBuild, wepon[i][0]);
		}
		for (int i = 0; i < n; i++) {
			wepon[i][1] = in.nextInt();
		}

		int[] metals = new int[m];
		for (int i = 0; i < m; i++) {
			metals[i] = in.nextInt();
		}

//		x <= maxBuild

//	try to precalculate values from 1 to maxBuild
// 	ans[x] = 2 + ans[x - minloss[x]] where x <= maxBuild



//	minloss[x] is minimum loss we can obtain by building a weapon with 'x' ingots
		int[] minloss = new int[maxBuild+1];
		Arrays.fill(minloss, Integer.MAX_VALUE);
		// minloss[ai] = min(minloss[ai], ai-bi)
		for (int i = 0; i < n; i++) {
			int x = wepon[i][0];
			minloss[x] = Math.min(minloss[x], x - wepon[i][1]);
		}
		// in above there will some holes, we will fill using next loop
//		minloss[x] = min(minloss[x], minloss[x-1])
		for (int i = 1; i <= maxBuild; i++) {
			minloss[i] = Math.min(minloss[i], minloss[i-1]);
		}
//		out.printf("minloss: %s\n", Arrays.toString(minloss));
//		ans[x] = 2 + ans[x-minloss[x]]
		long[] ans = new long[maxBuild+1];
		ans[0]=0;
		for (int i = 1; i <= maxBuild; i++) {
			if (i < minloss[i]) { // we cannot melt it
				ans[i] = 0;
			} else {
				ans[i] = 2 + ans[i - minloss[i]];
			}
		}

//		out.printf("ans: %s\n", Arrays.toString(ans));

		//	x > maxBuild
//	reduce x to maxBuild, by reducing minloss[maxBuild] k times
//	k = ceil{(x - maxBuild)/minloss[maxBuild]}  // reduce it ot maxBuild
//	remaining = x - k * minloss[maxBuild]
//	ans[x] = 2k + ans[remaining]
		// for each metal, try for each weapon
		long totalexp = 0;
		for (int i = 0; i < m; i++) {
			int x = metals[i];
			if (x > maxBuild) {
				int k = (x - maxBuild + (minloss[maxBuild]-1))/minloss[maxBuild]; // ceil[x/m] = (x + m - 1)/m
				int remaining = x - k * minloss[maxBuild];
				long exp = (2 * k) + ans[remaining];
				totalexp = totalexp + exp;
			} else {
				totalexp = totalexp + ans[x];
			}
		}
		out.println(totalexp);
	}

	public void solve1(InputReader in, PrintWriter out) throws IOException {

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
