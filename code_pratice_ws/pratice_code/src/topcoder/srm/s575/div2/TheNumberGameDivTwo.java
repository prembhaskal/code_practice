package topcoder.srm.s575.div2;

import java.util.ArrayList;
import java.util.List;

public class TheNumberGameDivTwo {


	// TODO this logic is wrong, correct it after reading editorial.
	public String find(int n) {
		boolean chance = true;
		int newNum = n;

		while (true) {
			int divisor = getBiggestDivisor(newNum);

			newNum = newNum - divisor;

			if (divisor==0) {
				break;
			}

			chance = !chance;
		}

		if (chance) {
			return "Brus";
		} else {
			return "John";
		}
	}

	private int getBiggestDivisor(int num) {
		int divisor = 0;

		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				divisor = Math.max(divisor,i);
				int otherDiv = num/i;
				divisor = Math.max(divisor, otherDiv);
			}
		}

		return divisor;
	}

	// solution refer to http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=algorithmGames
	// for more details.
	// get all next possible moves
	// see if losing is possible from at least one of the next move.
	// its like if there exists a next move from where we will definitely lose, we move to it.
	// i.e move to a losing position, so that opponent loses.
	public boolean isWinning(int num) {
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
