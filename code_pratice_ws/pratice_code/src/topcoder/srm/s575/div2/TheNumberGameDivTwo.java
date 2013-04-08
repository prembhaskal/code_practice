package topcoder.srm.s575.div2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheNumberGameDivTwo {

	int[] canWin = new int[1001];

	public String find(int num) {
		boolean chance;
		Arrays.fill(canWin, -1);

		chance = isWinning(num);

		if (chance) {
			return "John";
		} else {
			return "Brus";
		}
	}

	// solution refer to http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=algorithmGames
	// for more details.
	// get all next possible moves
	// see if losing is possible from at least one of the next move.
	// its like if there exists a next move from where we will definitely lose, we move to it.
	// i.e move to a losing position, so that opponent loses.
	// remembering previous results.
	public boolean isWinning(int num) {
		if (canWin[num] >= 0)
			return canWin[num]==0?false:true;

		List<Integer> divisors = getDivisorsList(num);

		List<Integer> nextMoves = new ArrayList<Integer>();
		for (Integer divisor : divisors) {
			nextMoves.add(num-divisor);
		}

		boolean canNextNumLose = false;
		for (Integer nextNum : nextMoves) {
			if (!isWinning(nextNum)) {
				canNextNumLose = true;
				break;
			}
		}

		canWin[num] = canNextNumLose?1:0;
		return canNextNumLose;
	}


	// return divisors other than 1 and the number itself.
	public List<Integer> getDivisorsList(int num) {
		List<Integer> divisors = new ArrayList<Integer>();

		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				divisors.add(i);
				int another = num/i;
				if (another!=i)
					divisors.add(another);
			}
		}

		return divisors;
	}


}
