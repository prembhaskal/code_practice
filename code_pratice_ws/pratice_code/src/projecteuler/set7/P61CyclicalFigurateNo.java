package projecteuler.set7;

import java.util.*;
import org.omg.CORBA._IDLTypeStub;

public class P61CyclicalFigurateNo {

	public int getSixCyclicDigitSum() {
		generateFourDigitNos();
		generateSmallerSet();
		return 0;
	}

	// generate a smaller set of nos first.
	// number such that other nos exist in other types starting with the last 2 digits and ending with first 2 digits.
	Set<Integer> smallerSet = new HashSet<>();
	private void generateSmallerSet() {
		for (int type = 3; type <= 8; type++) {
			List<Integer> nos = typeVsList.get(type);

			for (int num : nos) {
				int f2dig = getFirstTwoDigits(num);
				int l2dig = getLastTwodigits(num);

				if (!isMatchingFirst2Digits(l2dig, type)) {
					continue;
				}
				if (!isMatchingLast2Digits(f2dig, type)) {
					continue;
				}

				smallerSet.add(num);
			}
		}

		System.out.println("size of smaller set is " + smallerSet.size());
	}

	private boolean isMatchingFirst2Digits(int first2Digit, int notType) {

		for (int i = 3; i <= 8 ; i++) {
			if (i == notType)
				continue;

			Map<Integer, Integer> first2DigitVsNum = first2DigitList.get(i-3);
			if (first2DigitVsNum.get(first2Digit) != null)
				return true;
		}

		return false;
	}

	private boolean isMatchingLast2Digits(int last2Digit, int notType) {
		for (int i = 3; i <= 8; i++) {
			if (i == notType)
				continue;
			Map<Integer, Integer> last2DigitVsNum = last2DigitsList.get(i-3);
			if (last2DigitVsNum.get(last2Digit) != null)
				return true;
		}

		return false;
	}


	// this may be overwritten since first and last 2 digit may remain same,
	// but it is not a concern since they will be used only to check for the presence of the number.
	// first 2 digits number vs actual number
	private List<Map<Integer, Integer>> last2DigitsList = new ArrayList<>();
	// last 2 digits vs actual number.
	private List<Map<Integer, Integer>> first2DigitList = new ArrayList<>();

	// type vs numbers with that type
	private Map<Integer, List<Integer>> typeVsList = new HashMap<>();

	private void generateFourDigitNos() {
		for (int type = 3; type <= 8; type++) {
			first2DigitList.add(new HashMap<Integer, Integer>());
			last2DigitsList.add(new HashMap<Integer, Integer>());
			// init the type vs list, so that logic below is simplified.
			typeVsList.put(type, new ArrayList<Integer>());
		}

		for (int type = 3; type <= 8; type++) {
			List<Integer> numList = typeVsList.get(type);
			for (int j = 0; j < 300; j++) {
				int val = getVal(j, type);
				if (val < 1000)
					continue;
				if (val > 9999)
					break;
				int first2Digit = getFirstTwoDigits(val);
				int last2Digit = getLastTwodigits(val);
				first2DigitList.get(type-3).put(first2Digit, val);
				last2DigitsList.get(type-3).put(last2Digit,val);
				numList.add(val);
			}
		}
	}

	private int getVal(int i, int type) {
		switch (type) {
			case 3 : return (i * (i + 1)) / 2;
			case 4 : return ( i * i);
			case 5 : return (i * (3 * i - 1))/2;
			case 6 : return (i * (2 * i - 1));
			case 7 : return (i * (5 * i - 3))/2;
			case 8 : return (i * (3 * i - 2));
			default: throw new RuntimeException("what the hell is this type " + type);
		}
	}

	private int getFirstTwoDigits(int fourDigNumber) {
		return fourDigNumber/100;
	}

	private int getLastTwodigits(int fourdigitNumber) {
		return fourdigitNumber % 100;
	}

}

