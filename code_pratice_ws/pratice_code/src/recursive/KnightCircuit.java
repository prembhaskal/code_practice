package recursive;

// SRM 564 DIV - I
public class KnightCircuit {
	public int maxSize(int w, int h) {
		int maxSize = 0;

		if (w == 1 || h ==1)
			maxSize = 1;
		else if ( w > 3 && h >3)
			maxSize = w * h;
		else if (w ==2 || h ==2) {
			int greater = w > h ? w : h;
			maxSize = (greater/2) + 1;
		}
		else
			maxSize = 8;

		return maxSize;
	}
}
