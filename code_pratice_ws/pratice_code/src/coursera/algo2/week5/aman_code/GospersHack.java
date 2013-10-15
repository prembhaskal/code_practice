package coursera.algo2.week5.aman_code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Amanpreet Singh on 10/14/13.
 */
public class GospersHack
{
	static int set = 0;
	static int limit = 0;
	static int result;
	static Map<Integer, Integer> indexCache = new HashMap<Integer, Integer>();

	/**
	 * It's useful, for example, in generating combinations of k items from n:
	 *
	 * @param k Number of items to be selected in this combination Set
	 * @param n Total Number of Items
	 */
	static public List<Integer> gospersHack(int k, int n)
	{
		List<Integer> combinations = new ArrayList<Integer>();
		int set = (1 << k) - 1;
		int limit = (1 << n);
		while (set < limit)
		{
//			System.out.println(String.format("%d - %s", getIndex(set), Integer.toBinaryString(set)));
			combinations.add(set);
			// Gosper's hack:
			int c = set & -set;
			int r = set + c;
			set = (((r ^ set) >>> 2) / c) | r;
		}
		return combinations;
	}

	static public void reset(int k, int n)
	{
		set = (1 << k) - 1;
		limit = (1 << n);
		indexCache.clear();
	}

	static public boolean next()
	{
		if (set < limit)
		{
			result = set;
			// Gosper's hack:
			int c = set & -set;
			int r = set + c;
			set = (((r ^ set) >>> 2) / c) | r;
			return true;
		}
		return false;

	}

	static public int getNext()
	{
		return result;
	}


	/**
	 * http://en.wikipedia.org/wiki/Combinatorial_number_system
	 * <br/>
	 * Given a combination finds its lexograhpic Index.
	 *
	 * @param n Generated Combination
	 * @return its index in Gospers Hack List
	 */
	static public int getIndex(Integer n)
	{
		int original = n;
		if (indexCache.containsKey(n))
			return indexCache.get(n);

		int sum = 0;
		int bitPos = 0;
		int c = 1;
		while (n > 0)
		{
			int lastBit = n % 2;
			if (lastBit == 1)
				sum += combination(bitPos, c++);
			n >>= 1;
			bitPos++;
		}

		indexCache.put(original, sum);
		return sum;
	}

	// use the fact that nCr = (n/r)*((n-1)C(r-1))
	// and any no. xCo = 1;
	static public long combination(long n, long r)
	{
		if (r == 0)
			return 1;
		else
		{
			long num = n * combination(n - 1, r - 1);
			return num / r;
		}
	}
}
