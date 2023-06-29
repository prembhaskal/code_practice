package projecteuler.set6;

import common.util.InputReader;
import java.util.Set;

public class P59XORDecryption {

	Set<String> dictWords;
	char[] encryptedChars;
	String decryptedLine;

	public String getEncryptionKey(String encryptedLine, Set<String> dictWords) {
		this.dictWords = dictWords;
		convertToEncrypted(encryptedLine);

		char[] chars = new char[]{'a', 'a', 'a'};
		String maxStr = "";
		int maxCount = -1;
		String finalDecryptedLine = "";

		for (int i = 0; i < 26; i++) {
			chars[1] = 'a';
			for (int j = 0; j < 26; j++) {
				chars[2] = 'a';
				for (int k = 0; k < 26; k++) {
					int matchCount = getMatchingWords(chars);
					if (matchCount > maxCount) {
						maxCount = matchCount;
						maxStr = new String(chars);
						finalDecryptedLine = decryptedLine;
					}

					chars[2]++;
				}

				chars[1]++;
			}
			chars[0]++;
		}

		System.out.println(finalDecryptedLine);
		System.out.println("ascii sum is " + getAsciiSum(finalDecryptedLine));

		return maxStr;
	}

	private int getAsciiSum(String line) {
		int sum = 0;
		char[] chars = line.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			sum += chars[i];
		}

		return sum;
	}

	private void convertToEncrypted(String line) {
		String[] encryptedStr = line.split(",");
		encryptedChars = new char[encryptedStr.length];
		for (int i = 0; i < encryptedStr.length; i++) {
			encryptedChars[i] = (char) Integer.parseInt(encryptedStr[i]);
		}
	}

	private int getMatchingWords(char[] chars) {
		String decryptedString = getDecryptedString(chars);
		decryptedLine = decryptedString;
		String[] words = decryptedString.split(" ");

		int matchCount = 0;
		for (String word : words) {
			if (dictWords.contains(word.toLowerCase()))
				matchCount++;
		}

		return matchCount;
	}

	private String getDecryptedString(char[] chars) {
		char[] charsCombination = new char[encryptedChars.length];

		for (int i = 0, j = 0; i < charsCombination.length; i++, j++) {
			charsCombination[i] = (char)(encryptedChars[i] ^ chars[j%3]);
		}

		return new String(charsCombination);
	}
}
