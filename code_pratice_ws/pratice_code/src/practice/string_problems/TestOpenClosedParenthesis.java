package practice.string_problems;

public class TestOpenClosedParenthesis {


	// check if we have at least closing bracket ')' matching a '(' open bracket
	public boolean isMatched(String text) {
		char[] chars =  text.toCharArray();

		int open = 0;
		char ch;
		for (int i=0;i<chars.length;i++) {
			ch = chars[i];

			if (ch=='(')
				open++;

			if (ch==')' && open>0)
				return true;
		}

		return false;
	}


	// true if all opening brackets '(' are matched by closing brackets ')'
	public boolean ifAllBracketsMatched(String text) {
		char[] chars = text.toCharArray();

		int open = 0;
		char ch;

		for (int i=0;i<chars.length;i++) {
			ch =chars[i];

			if (ch=='(')
				open++;

			if (ch==')')
				open--;

			if (open < 0)
				return false;
		}

		if (open != 0)
			return false;
		else
			return true;
	}
}
