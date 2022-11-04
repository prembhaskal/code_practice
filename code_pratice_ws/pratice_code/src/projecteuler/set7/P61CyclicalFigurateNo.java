package projecteuler.set7;

import java.util.*;

public class P61CyclicalFigurateNo {

	public int getSixCyclicDigitSum() {
		generateFourDigitNos();
		generateSmallerSet();
		findMatchingNumbers();
		return 0;
	}

	private void findMatchingNumbers() {
		List<Integer> typeNumbers = typeVsList.get(3);
		int indicatorBit = 0b000001; // 00001 means the type 3 has been used.
		for (int startNum : typeNumbers) {
			int last2Digit = getLastTwodigits(startNum);
			findNos(startNum, 4, last2Digit, indicatorBit, startNum);
		}
	}


	// indicator bits -- 0 means unused bits, 1 means used bits.
	// LSB indicates the type 3
	private void findNos(int startNo, int level, int last2Digits, int indicatorBits, int sumTillNow) {
		if (level > 8)
			return;
		Map<Integer, List<Integer>> matchingNos = getMatchingNos(last2Digits, indicatorBits);

		// for the last level, just check if the matching nos match with start no.
		if (level == 8) {
			int first2Digit = getFirstTwoDigits(startNo);
			for (int type : matchingNos.keySet()) {
				for (int matchingNum : matchingNos.get(type)) {
					int last2Digit = getLastTwodigits(matchingNum);
					if (last2Digit == first2Digit) {
						sumTillNow += matchingNum;
						System.out.println("found the number --> " + matchingNum + " -- " + startNo);
						System.out.println("sum of the six digits is " + sumTillNow);
						break;
					}
				}
			}

			return;
		}

		for (Integer type : matchingNos.keySet()) {
			int newIndicator = useTypeAndReturnNewIndicator(type, indicatorBits);

			List<Integer> nums = matchingNos.get(type);
			for (Integer number : nums) {
				int last2Dig = getLastTwodigits(number);
				findNos(startNo, level + 1, last2Dig, newIndicator, sumTillNow + number);
			}
		}
	}

	private int useTypeAndReturnNewIndicator(int type, int oldIndicator) {
		int shift = type - 3;
		int orBit = 1 << shift;
		return oldIndicator | orBit;
	}

	private Map<Integer, List<Integer>> getMatchingNos(int first2Digits, int indicatorBits) {
		int andBit = 1;
		Map<Integer, List<Integer>> typeVsList = new HashMap<>();
		for (int i = 3; i <= 8 ; i++) {
			if ((indicatorBits & andBit) != 0) { // means bit is used so don't use it.
				andBit = andBit << 1; // shift the and bit.
				continue;
			}
			andBit = andBit << 1; // shift the and bit.

			Map<Integer, List<Integer>> firDigitsVsNos = typeVsMapof1stDigVsListOfNos.get(i);
			List<Integer> matchingNos = firDigitsVsNos.get(first2Digits);
			if (matchingNos != null)
				typeVsList.put(i, new ArrayList<>(matchingNos));
		}

		return typeVsList;
	}

	// generate a smaller set of nos first.
	// number such that other nos exist in other types starting with the last 2 digits and ending with first 2 digits.
	private Map<Integer, Set<Integer>> smallerSet = new HashMap<>();
	private Map<Integer, Map<Integer, List<Integer>>> typeVsMapof1stDigVsListOfNos = new HashMap<>();
	private void generateSmallerSet() {
		int size = 0;
		for (int type = 3; type <= 8; type++) {
			Set<Integer> matchingNos = new HashSet<>();
			List<Integer> nos = typeVsList.get(type);

			Map<Integer, List<Integer>> firDigitsVsNos = new HashMap<>();

			for (int num : nos) {
				int f2dig = getFirstTwoDigits(num);
				int l2dig = getLastTwodigits(num);

				if (!isMatchingFirst2Digits(l2dig, type)) {
					continue;
				}
				if (!isMatchingLast2Digits(f2dig, type)) {
					continue;
				}

				size++;

				matchingNos.add(num);

				List<Integer> fDigNos = firDigitsVsNos.get(f2dig);
				if (fDigNos == null) {
					fDigNos = new ArrayList<>();
					firDigitsVsNos.put(f2dig, fDigNos);
				}
				fDigNos.add(num);
			}

			smallerSet.put(type, matchingNos);
			typeVsMapof1stDigVsListOfNos.put(type, firDigitsVsNos);
		}

		System.out.println("size of smaller set is " + size);
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

