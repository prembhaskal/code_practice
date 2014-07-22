package practice.string_problems;

import common.util.InputReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO handle multiple chars at one place
public class DictionaryTrie {

	private Map<Character, Trie> trieMap = new HashMap<>();
	private Letter[][] matrix;
	private boolean used[][];
	private List<String> words;
	private int rows;
	private int cols;

	public void buildTrieUsingDictionary(InputReader inputReader) {
		while (true) {
			String word = inputReader.next();
			if (word == null) {
				break;
			}
			word = word.toLowerCase();

			// check if we have the map already.
			char[] letters = word.toCharArray();
			Trie trie = trieMap.get(letters[0]);
			if (trie == null) {
				trie = new Trie(letters[0]);
				trieMap.put(letters[0], trie);
			}

			// add the remaining word to trie
			addRemainingWord(letters, trie);
		}
	}

	public List<String> searchWordsInScramble(String[][] scramble, int rows, int cols) {
		matrix = new Letter[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = new Letter(i, j, scramble[i][j]);
			}
		}

		this.rows = rows;
		this.cols = cols;
		this.words = new ArrayList<>();
		this.used = new boolean[rows][cols];

		// for each letter try to find new words.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Letter letter = matrix[i][j];
//				Trie root = trieMap.get(letter.ch);
				Trie root = getNextMatchingNode(null, letter.str);
				if (root != null)
					navigate(letter, root, "");
			}
		}

		return words;
	}

	private void navigate(Letter letter, Trie rootNode, String word) {
		word = word + letter.str;
		used[letter.i][letter.j] = true;

		// check if the trie path actually exists, if not mark as unused and return.
		if (rootNode == null) {
			used[letter.i][letter.j] = false;
			return;
		}

		if (rootNode.isLastLetter) {
			if (word.length() > 2)
				words.add(word);
		}

		// else keep searching.
		List<Letter> neighbours = getNeighbouringLetters(letter);
		for (Letter x : neighbours) { // try to find word for each of the neighbour.
			if (!used[x.i][x.j]) {
//				Trie newNode = rootNode.children.get(x.ch); // get the next node from trie.
				Trie newNode = getNextMatchingNode(rootNode, x.str);
				navigate(x, newNode, word); // try using this combination.
			}
		}

		used[letter.i][letter.j] = false;

	}

	private Trie getNextMatchingNode(Trie rootNode, String str) {
		char[] chars = str.toCharArray();
		int i = 0;
		if (rootNode == null) { // try to match the first letter(s) of trie with the given str.
			rootNode = trieMap.get(chars[0]); // get the first node by matching with the trie.
			i++;
		}

		while (rootNode != null && i < chars.length) {
			rootNode = rootNode.children.get(chars[i]);
			i++;
		}

		return rootNode;
	}

	private List<Letter> getNeighbouringLetters(Letter root) {
		List<Letter> letters = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int x = root.i + i;
				if (x < 0 || x >= rows)
					continue;
				int y = root.j + j;
				if (y < 0 || y >= cols)
					continue;
				letters.add(matrix[x][y]);
			}
		}

		return letters;
	}

	private void addRemainingWord(char[] chars, Trie root) {
		Trie currNode = root;
		for (int i = 1; i < chars.length; i++) {
			Trie newNode = currNode.children.get(chars[i]);
			if (newNode == null) {
				newNode = new Trie(chars[i]);
			}
			currNode.children.put(chars[i], newNode);
			currNode = newNode;
		}
		currNode.isLastLetter = true;
	}

	public void addInTrie(String word) {
		char[] chars = word.toCharArray();

		Trie root = new Trie(chars[0]);
		addRemainingWord(chars, root);
	}


	// Trie
	private class Trie {
		public Character ch;
		public Map<Character, Trie> children;
		public boolean isLastLetter = false;

		public Trie(Character ch) {
			this.ch = ch;
			children = new HashMap<>();
		}
	}

	private class Letter {
		int i;
		int j;
		String str;

		public Letter(int i, int j, String str) {
			this.i = i;
			this.j = j;
			this.str = str;
		}
	}
}

