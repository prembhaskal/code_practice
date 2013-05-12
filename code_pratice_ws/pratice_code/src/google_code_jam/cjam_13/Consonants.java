package google_code_jam.cjam_13;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Consonants {

	private Set<Character> vowels = new HashSet<Character>();

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		init();

		for (int testNo=1;testNo<=tests;testNo++) {
			String name = in.next();
			int N = in.nextInt();

			long result = getValue(N, name);
//			long result = getNValueFromOther(N, name);

			out.println("Case #" + testNo + ": " + result);
		}
	}

	public void init() {
		char[] vow = new char[]{'a','e','i','o','u'};

		for (char ch : vow)
			vowels.add(ch);
	}


	// solve using brute force for small strings
	private int getValue(int n, String name) {

		if (name.length()==1)
			if (vowels.contains(name.charAt(0))) {
			return 0;
			} else {
			return 1;
			}

		int value = 0;

		int len = name.length();

		for (int i=0;i<len-n+1;i++) {
			for (int j=i+n;j<=len;j++) {
				String str = name.substring(i,j);
				if (containsConsecutiveConsonants(n, str)) {
					value++;
				}
			}
		}

		return value;
	}

	public boolean containsConsecutiveConsonants(int n, String str) {
		int max = n;

		char[] chars = str.toCharArray();

		if (n > str.length())
			return false;

		for (int i=0;i<chars.length;i++) {
			char ch = chars[i];
			if (!vowels.contains(ch)) {
				n--;
			} else {
				n = max;
			}

			if (n==0) {
				return true;
			}
		}

		return false;
	}


	// solution of another contestant.
	private long getNValueFromOther(int n, String input) {
		long count = 0;
		int windowStart=0;
		int maxMatched = 0;
		for(int j=0;j<input.length();j++) {
			Character c = input.charAt(j);
			if(isConsonent(c)) {
				maxMatched+=1;
			}else {
				maxMatched=0;
			}

			if(maxMatched==n) {
				count+= getNoOfSubStrings(j,n,windowStart,input.length());
				windowStart = j-n+2;
				maxMatched = maxMatched -1;

			}
		}

		return count;
	}


	private static long getNoOfSubStrings(int j, int n, int windowStart,
										  int length) {
		long possibleEndingChars = length-j;
		long possiblestartingChars = (j-windowStart+1)-n+1;
		return possiblestartingChars*possibleEndingChars;
	}

	public static boolean isConsonent(Character s) {

		if("aeiou".indexOf(s)>=0) {
			return false;
		}else {
			return true;
		}
	}
}
