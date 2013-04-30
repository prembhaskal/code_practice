package google_code_jam.cjam_13;

import common.util.InputReader;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: premkumar.bhaskal
 * Date: 4/30/13
 * Time: 4:02 PM
 */
public class Bullseye {

	private long lowerLimit;
	private long upperLimit;


	public void solve(InputReader in, PrintWriter out) throws IOException {
		int tests = in.nextInt();

		for (int testNo=1;testNo<=tests;testNo++) {
			long radius = in.nextLong();
			long totalPaint = in.nextLong();

//			long maxRings = getMaxBlackRings(radius, totalPaint);
			long maxRings = getMaxBlackRingsBigData(radius, totalPaint);

			out.println("Case #" + testNo + ": " + maxRings);
		}

	}

	// this method is smaller inputs only
	// area of a strip of inner radius r and outer radius r+1 is PI*(2*r+1)
	// area of the next strip is PI * (2*r+5), then PI*(2*r+9) and so on.
	public long getMaxBlackRings(long radius, long totalPaint) {
		long noOfRings = 0;

		long paintReqd = (2*radius + 1);

		while (paintReqd <= totalPaint) {
			noOfRings++;

			paintReqd += (2 * radius + 1 + 4*noOfRings);
		}

		return noOfRings;
	}

	// to test for bigger numbers
	// total paint for n circles with initial radius 'r' == 2nr + 2n^2 - n;
	public long getMaxBlackRingsBigData(long radius, long totalPaint) {
		findoutLowerAndUpperLimit(radius, totalPaint);
		long rings = getMaxRingsFromLimits(radius, totalPaint);
//		System.out.println("lower limit is " + lowerLimit + " and upper limit is " + upperLimit);
		return rings;
	}

	private long getMaxRingsFromLimits(long radius, long totalPaint) {
		while (upperLimit - lowerLimit > 1) {
			long mid = (upperLimit + lowerLimit)/2;

			long paintReqd = getPaintRequired(radius, mid);

			if (paintReqd > totalPaint) {
				upperLimit = mid;
			} else {
				lowerLimit = mid;
			}
		}

		return lowerLimit;

	}

	private void findoutLowerAndUpperLimit(long radius, long totalPaint) {
		lowerLimit = 1;
		upperLimit = 1;

		long num = 1;
		long paintRequired = 0;
		while (paintRequired <= totalPaint) {
			lowerLimit = num;
			num = num * 2;
			paintRequired = getPaintRequired(radius, num);
		}

		upperLimit = num;
	}

	private long getPaintRequired(long radius, long num) {

		// we are never exceeding range by the way.
		double paintReqd = (2*radius + 2*num - 1);
		paintReqd = paintReqd * (double)num;
		if (paintReqd > Long.MAX_VALUE)
			System.out.println("EXCEEDING RANGE :(");


		return num * (2*radius + 2*num -1);
	}
}
