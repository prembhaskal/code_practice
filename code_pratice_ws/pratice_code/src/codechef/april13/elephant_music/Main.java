package codechef.april13.elephant_music;

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

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int songs  = in.nextInt();
			int[] band = new int[songs];
			int[] length = new int[songs];
			for (int j=0;j<songs;j++) {
				band[j] = in.nextInt();
				length[j] = in.nextInt();
			}

			long maxSweetNess = getMaxSweetness(songs, band, length);
			out.println(maxSweetNess);
		}

	}

	private long getMaxSweetness(int songs, int[] band, int[] length) {
		Map<Integer, Integer> bandVsTotalLength = new HashMap<Integer, Integer>();

		for (int i=0;i<band.length;i++) {
			Integer bandLength = bandVsTotalLength.get(band[i]);
			if (bandLength==null) {
				bandLength = 0;
			}
			bandLength = bandLength + length[i];
			bandVsTotalLength.put(band[i], bandLength);
		}

		List<Integer> bandLengths = new ArrayList<Integer>(bandVsTotalLength.values());

		Collections.sort(bandLengths);

		long sweetNess = 0;

		for (int i=0;i<bandLengths.size();i++) {
			sweetNess = sweetNess + ((long)bandLengths.get(i) * (i+1));
		}

		return sweetNess;
	}


}

