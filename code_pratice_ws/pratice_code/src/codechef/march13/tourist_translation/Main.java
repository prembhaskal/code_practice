package codechef.march13.tourist_translation;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}

}

class TaskA {

	Map<Character, Character> characterMap = new HashMap<Character, Character>();

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.next());

		String permutation = in.next();

		formCharacterMap(permutation.toCharArray());

		for (int i=0;i<tests;i++) {
			String line = in.next();
			char[] lineChars = line.toCharArray();

			for (int j=0;j<lineChars.length;j++) {
				out.print(characterMap.get(lineChars[j]));
			}
			out.println();
		}

	}

	private void formCharacterMap(char[] permutation) {

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char[] alphaChars = alphabet.toCharArray();

		for (int i=0;i<26;i++) {
			characterMap.put(alphaChars[i], permutation[i]);
			characterMap.put( Character.toUpperCase(alphaChars[i]), Character.toUpperCase(permutation[i]));
		}

		characterMap.put('_',' ');
		characterMap.put('?','?');
		characterMap.put('.','.');
		characterMap.put('!','!');
		characterMap.put(',',',');

	}


}

