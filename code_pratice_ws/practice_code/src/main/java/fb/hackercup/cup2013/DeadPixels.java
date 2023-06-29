package fb.hackercup.cup2013;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DeadPixels {

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());
		int testNo = 1;

		while (tests > 0) {
			findPositions(in, testNo, out);
			testNo++;
			tests--;
		}
	}


	private void findPositions(Scanner in, int testNo, PrintWriter out) {
		int W = in.nextInt();
		int H = in.nextInt();
		int P = in.nextInt();
		int Q = in.nextInt();
		int N = in.nextInt();
		int X = in.nextInt();
		int Y = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();

		int[] x = new int[N];
		int[] y = new int[N];

		Set<XY> deadPixelsSet = new HashSet<XY>();

		// find all positions
		x[0] = X;
		y[0] = Y;
		XY xy = new XY();
		xy.x = X;
		xy.y = Y;
		deadPixelsSet.add(xy);

		for (int i=1;i<N;i++) {
			x[i] = (int)((long)x[i-1]*a + (long)y[i-1]*b + 1) % W;
			y[i] = (int)((long)x[i-1]*c + (long)y[i-1]*d + 1) % H;
			xy = new XY();
			xy.x = x[i];
			xy.y = y[i];
			deadPixelsSet.add(xy);
		}

		int deadPixelsCount = deadPixelsSet.size();
		XY[] deadPixels = new XY[deadPixelsCount];

		int i=0;
		for (XY pixel:deadPixelsSet) {
			deadPixels[i]=pixel;
			i++;
		}

		Arrays.sort(deadPixels);

//		System.out.println("Case 1");
//		printAllCoefficients(x,y);

		printScreen(W,H,x,y);
//
//		initImage(P,Q);
//		initDeadPixelsCount(deadPixels);
//
//		XY from = new XY();
//		XY to = new XY();
//		int ways = 0;
//
//		// start from 0,0 till (W-P-1, H-Q-1)
//		for (int yc=0;yc+Q<=H;yc++) {
//			from.y = yc;
//			to.y = yc + Q - 1;
//			for (int xc=0;xc+P<=W;xc++) {
//				from.x = xc;
//				to.x = xc + P - 1;
//				if (totalDeadPixels == 0)
//					ways++;
//				// move image one place right, if possible
//				if (to.x < W-1)
//					moveImageRightOnePlace(deadPixelsSet);
//			}
//
//			if (to.y < H-1)
//				moveImageBelowOnePlace(deadPixelsSet);
//		}

		initMinMaxPixel(P,Q, deadPixels);

		XY from = new XY();
		XY to = new XY();
		int ways = 0;
		// start from 0,0 till (W-P-1, H-Q-1)
		for (int yc=0;yc+Q<=H;yc++) {
			from.y = yc;
			to.y = yc + Q - 1;
			for (int xc=0;xc+P<=W;xc++) {
				from.x = xc;
				to.x = xc + P - 1;

				if (isDeadPixelPresentInRange(from, to, deadPixels)==false) {
					ways++;
				}
			}
		}

//		System.out.println(totalDeadPixels);
		System.out.println("Case #" + testNo + ": " + ways);
		out.println("Case #" + testNo + ": " + ways);

	}

	XY topLeft, topRight, bottomLeft, bottomRight;
	int totalDeadPixels = 0;

	XY firstTleft, firstTRight, firstBLeft, firstBRight;
	int firstDeadPixels = 0;

	private void initImage(int P, int Q) {
		firstTleft = new XY();
		firstTleft.x = 0;
		firstTleft.y = 0;

		firstTRight = new XY();
		firstTRight.x = P-1;
		firstTRight.y = 0;

		firstBLeft = new XY();
		firstBLeft.x = 0;
		firstBLeft.y = Q-1;

		firstBRight = new XY();
		firstBRight.x = P-1;
		firstBRight.y = Q-1;

		firstDeadPixels = 0;

		copyFirstImageToNormalImage();
	}

	private void initDeadPixelsCount(XY[] deadPixels) {
		int count = 0;
		for (int i=0;i<deadPixels.length;i++) {
			if (deadPixels[i].x >= topLeft.x && deadPixels[i].y >= topLeft.y &&
					deadPixels[i].x <= bottomRight.x && deadPixels[i].y <= bottomRight.y) {
				count++;
			}
		}

		totalDeadPixels = count;
		firstDeadPixels = count;
	}

	private void moveImageRightOnePlace(Set<XY> deadPixelsSet) {
		if (deadPixelsSet.contains(topLeft))
			totalDeadPixels--;
		if (deadPixelsSet.contains(bottomLeft))
			totalDeadPixels--;

		topLeft.x = topLeft.x + 1;
		bottomLeft.x = bottomLeft.x + 1;

		topRight.x = topRight.x + 1;
		bottomRight.x = bottomRight.x + 1;

		if (deadPixelsSet.contains(topRight))
			totalDeadPixels++;
		if (deadPixelsSet.contains(bottomRight))
			totalDeadPixels++;
	}

	private void moveImageBelowOnePlace(Set<XY> deadPixelsSet) {
		if (deadPixelsSet.contains(firstTleft))
			firstDeadPixels--;
		if (deadPixelsSet.contains(firstTRight))
			firstDeadPixels--;

		firstTleft.y = firstTleft.y + 1;
		firstTRight.y = firstTRight.y + 1;

		firstBLeft.y = firstBLeft.y + 1;
		firstBRight.y = firstBRight.y + 1;

		if (deadPixelsSet.contains(firstBLeft))
			firstDeadPixels++;
		if (deadPixelsSet.contains(firstBRight))
			firstDeadPixels++;

		// copy first image to normal image
		copyFirstImageToNormalImage();

	}

	private void copyFirstImageToNormalImage() {
		topLeft = new XY();
		topLeft.x = firstTleft.x;
		topLeft.y = firstTleft.y;

		topRight = new XY();
		topRight.x = firstTRight.x;
		topRight.y = firstTRight.y;

		bottomLeft = new XY();
		bottomLeft.x = firstBLeft.x;
		bottomLeft.y = firstBLeft.y;

		bottomRight = new XY();
		bottomRight.x = firstBRight.x;
		bottomRight.y = firstBRight.y;

		totalDeadPixels = firstDeadPixels;
	}


	private void printAllCoefficients(int[] x, int [] y) {
		for (int i=0;i<x.length;i++) {
			System.out.println("x--> " + x[i] + " y--> " + y[i]);
		}
	}

	private void printScreen(int W, int H, int[] x, int[] y) {
		char[][] screen = new char[W][H];

		for (int i=0;i<W;i++)
			Arrays.fill(screen[i],'O');

		for (int i=0;i<x.length;i++) {
			screen[x[i]][y[i]] = 'X';
		}

		for (int yc=0;yc<H;yc++) {
			for (int xc=0;xc<W;xc++) {
				System.out.print(screen[xc][yc]);
			}
			System.out.println("");
		}
	}

	XY minPixel, maxPixel;

	private void initMinMaxPixel(int P, int Q, XY[] deadPixels) {
		XY from = new XY();
		from.x = 0;
		from.y = 0;

		XY to = new XY();
		to.x = P-1;
		to.y = Q-1;

		boolean deadPixelSet = false;
		XY lastPixel = new XY();
		for (int i=0;i<deadPixels.length;i++) {
			if (deadPixels[i].x >= from.x && deadPixels[i].y >= from.y &&
					deadPixels[i].x <= to.x && deadPixels[i].y <= to.y) {
				if (!deadPixelSet)
					minPixel = deadPixels[i];
				lastPixel = deadPixels[i];
			}
		}

		maxPixel = lastPixel;

	}


	private XY getDeadPixelPresentInRange(XY from, XY to, XY[] deadPixels) {
		boolean found = false;
		for (int i=0;i<deadPixels.length;i++) {
			if (deadPixels[i].x >= from.x && deadPixels[i].y >= from.y &&
					deadPixels[i].x <= to.x && deadPixels[i].y <= to.y) {
				return deadPixels[i];
			}
		}

		return null;
	}

	private boolean isDeadPixelPresentInRange(XY from, XY to, XY[] deadPixels) {
		boolean found = false;
		for (int i=0;i<deadPixels.length;i++) {
			if (deadPixels[i].x >= from.x && deadPixels[i].y >= from.y &&
					deadPixels[i].x <= to.x && deadPixels[i].y <= to.y) {
				return true;
			}
		}

		return found;
	}


}

class XY implements Comparable<XY>{
	int x;
	int y;
//
//	@Override
//	public boolean equals(Object o) {
//		XY other = (XY)o;
//		if ((x==other.x) && (y==other.y))
//			return true;
//		else
//			return false;
//	}
//
//	@Override
//	public int hashCode() {
//		int result = x;
//		result = 31 * result + y;
//		return result;
//	}

	@Override
	public int compareTo(XY o) {
		if (y < o.y) {
			return -1;
		} else if (y > o.y) {
			return 1;
		} else {
			if (x < o.x) {
				return -1;
			} else if (x > o.x) {
				return 1;
			} else
				return 0;
		}
	}
}
