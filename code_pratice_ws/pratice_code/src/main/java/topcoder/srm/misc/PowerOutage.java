package topcoder.srm.misc;

// SRM 144 Div 2 level 3
public class PowerOutage {

	public int estimateTimeOut(int[] fromJunction, int[] toJunction, int[] ductLength){
		long timeOut = 0;

		// find total time needed
		int totalTime = 0;
		for (int i=0;i<ductLength.length;i++) {
			totalTime += ductLength[i];
		}

		// get the maxDistance
		int maxDistance = getMaxDistance(fromJunction, toJunction, ductLength, 0,0);

		timeOut = totalTime*2 - maxDistance;

		return (int) timeOut;
	}

	// at each node, add the distance from parent node to the its own length
	// do this recursively for each of the destination and at each point keep track of the maximum one,
	private int getMaxDistance(int[] fromJunction, int[] toJunction, int[] ductLength,int distanceFromBase, int start) {
		int maxDistance = distanceFromBase;

		for (int i=0; i < fromJunction.length; i++) {
			if (fromJunction[i] == start) {
				int distance = getMaxDistance(fromJunction, toJunction, ductLength,
						distanceFromBase + ductLength[i], toJunction[i]);
				if ( distance > maxDistance)
					maxDistance = distance;
			}
		}

		return maxDistance;
	}


}