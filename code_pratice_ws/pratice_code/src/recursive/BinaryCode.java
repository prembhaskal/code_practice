package recursive;

// SRM144 DIV 1 -->  300 points problem
public class BinaryCode {

	public static void main(String [] s) {
		BinaryCode code = new BinaryCode();
		String message = "";
		String[] res = null;
		message = "123210122";
		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);

		message = "22111";

		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);

		message = "123210120";// should return NONE and NONE
		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);


		message = "3";

		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);


		message = "12221112222221112221111111112221111";
		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);

		message = "0";
		res = code.decode(message);
		System.out.println(message + "-->" + res[0] + "  " + res[1]);



	}

	public String[] decode(String message) {
		String[] result = new String[2];

		char[] chars = message.toCharArray();
		int[] ints = new int[chars.length];

		for(int i=0; i< chars.length;i++)
			ints[i] = Integer.parseInt(chars[i] + "");

		result[0] = decodeArray(0, ints );
		result[1] = decodeArray(1, ints);

		return result;
	}

	private String decodeArray(int firstElement, int[] inputArray) {
		boolean noResultPossible = false;
		int arrlen = inputArray.length;
		int [] resArr = new int[arrlen];
		String result = "";

		resArr[0] = firstElement;

		for(int i = 1; i< arrlen; i++) {
			if (i==1) {
				resArr[1] = inputArray[0] - resArr[0];
			} else if ( i <= arrlen-1)
				resArr[i] = inputArray[i-1] -resArr[i-1] - resArr[i-2];

			if ( resArr[i] != 0 && resArr[i] != 1) {
				noResultPossible = true;
				break;
			}
		}

		// check for consistency...digit after last should be 0, else incorrect coding
		if (!noResultPossible && arrlen > 1) {
			int last = inputArray[arrlen-1]-resArr[arrlen-1]-resArr[arrlen-2];
			if (last != 0)
				noResultPossible = true;
		}


		if ( noResultPossible)
			result = "NONE";
		else
			for(int i = 0; i< arrlen;i++)
				result = result + resArr[i];

		return result;
	}
}
