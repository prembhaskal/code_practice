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

	// listen the song of min-length from each band first, then listen to all songs. ;)
	private long getMaxSweetness(int songs, int[] band, int[] length) {
		Map<Integer, Integer> bandVsMinLength = new HashMap<Integer, Integer>();

		for (int i=0;i<band.length;i++) {
			Integer bandMinLength = bandVsMinLength.get(band[i]);
			if (bandMinLength==null) {
				bandVsMinLength.put(band[i], length[i]);
			} else {
				bandMinLength = Math.min(bandMinLength, length[i]);
				bandVsMinLength.put(band[i], bandMinLength);
			}
		}

		long totalLength = 0;
		for (int i=0;i<band.length;i++) {
			totalLength += length[i];
		}

		long minLength = 0;
		long sweetNess = 0;

		List<Integer> minLengths = new ArrayList<Integer>(bandVsMinLength.values());
		Collections.sort(minLengths);

		for (int i=0;i<minLengths.size();i++) {
			long len = minLengths.get(i);
			sweetNess = sweetNess + (len * (i+1));
			minLength += len;
		}

		long remLength = totalLength - minLength;

		sweetNess = sweetNess + (minLengths.size() * remLength);

		return sweetNess;
	}


}

