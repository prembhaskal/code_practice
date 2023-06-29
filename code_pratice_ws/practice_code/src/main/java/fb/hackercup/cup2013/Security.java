package fb.hackercup.cup2013;

import java.io.PrintWriter;
import java.util.*;

public class Security {

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());
		int tc = 1;

		while (tests > 0) {
			findKey(in, tc, out);
			tc++;
			tests--;
		}
	}


	private void findKey(Scanner in, int testNo, PrintWriter out) {
		int m = Integer.parseInt(in.nextLine());
		String k1 = in.nextLine();
		String k2 = in.nextLine();

		int len = k1.length();

		KeyString[] guessOne = new KeyString[m];
		KeyString[] guessTwo = new KeyString[m];

		int l = len/m;

		for (int i=0,j=0;i<len;j++) {
			guessOne[j] = new KeyString();
			guessTwo[j] = new KeyString();
			guessOne[j].key = k1.substring(i,i+l);
			guessTwo[j].key = k2.substring(i,i+l);
			i = i+l;
		}

		// sort only second since only k2 has sections place changed
		Arrays.sort(guessTwo);


		List<String> finalList = new ArrayList<String>();
		findAllMatches(guessOne, guessTwo,m,0,"",finalList);

//		for (String str : finalList) {
//			System.out.println(str);
//		}

		if (finalList.isEmpty()) {
			System.out.println("Case #" + testNo + ": IMPOSSIBLE");
			out.println("Case #" + testNo + ": IMPOSSIBLE");
			return;
		}

		Collections.sort(finalList);


		System.out.println("Case #" + testNo + ": " + finalList.get(0));
		out.println("Case #" + testNo + ": " + finalList.get(0));
	}


	private String charArrayToString(char[] s) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<s.length;i++)
			sb.append(s[i]);

		return sb.toString();
	}

	private void findAllMatches(KeyString[] firstKeys, KeyString[] secondKeys, int m, int start, String finalString,
								List<String> finalList) {

		if (start >= m)
			return;

		String strSource = firstKeys[start].key;

		String matchString;
		String newFinalString="";

		for (int i=0;i<m;i++) {
			String strTarget = secondKeys[i].key;
			if (secondKeys[i].matched==true)
				continue;

			matchString = matchStrings(strSource, strTarget);

			if (matchString.equals("X"))
				continue;
			else {
				KeyString[] newSecondKey = secondKeys.clone();
				newSecondKey[i].matched = true;
				newFinalString =  finalString+matchString;
				findAllMatches(firstKeys, secondKeys, m, start+1, newFinalString, finalList);
//				newSecondKey[i].matched = false;//set to false to check for other combinations.
			}

		}

		if (start==m-1 && newFinalString.length()==strSource.length()*m)
			finalList.add(newFinalString);
	}

	private String matchStrings(String strn1, String strn2) {

		char [] str1 = strn1.toCharArray();
		char [] str2 = strn2.toCharArray();
		int len = str1.length;
		boolean matched = true;

		char[] matchString = new char[len];

		// match and replace sections
		for (int cj = 0; cj < len; cj++) {
			if (str1[cj] == '?' && str2[cj] == '?') {
				str1[cj] = str2[cj] = 'a';
			} else if (str1[cj] == '?') {
				str1[cj] = str2[cj];
			} else if (str2[cj] == '?') {
				str2[cj] = str1[cj];
			} else if (str1[cj] != str2[cj]) {
				matched = false;
				break;
			}

			matchString[cj] = str1[cj];
		}

		if (matched) {
			return charArrayToString(matchString);
		} else {
			return "X";
		}
	}


}

class KeyString implements Comparable<KeyString>{
	String key;
	boolean matched=false;

	@Override
	public int compareTo(KeyString o) {
		// push the string with '?'s down
		String thisString = key.replace('?', 'z');
		String thatString = o.key.replace('?','z');
		return thisString.compareTo(thatString);
	}
}
