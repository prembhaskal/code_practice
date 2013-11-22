package com.prem.test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: premkumar.bhaskal
 * Date: 3/1/13
 * Time: 4:21 PM
 */
public class TestClass {


	public static void main(String[] s) {
//		testDivision();
//
//		String[] strArray1 = new String[]{"test", "test1"};
//		readVarargs(strArray1);
//		readVarargs((Object)strArray1);
//		String[] strArray2 = new String[]{"test2", "test3"};
//		readVarargs(strArray1, strArray2);
		testIntegerMap();
	}



	// test division 103993/33102

	private static void testDivision() {
		int a = 103993;
		int b = 33102;

		int [] quot = new int[33101];
		int[] rem = new int[33101];

		for (int i=0;i<33101;i++) {
			quot[i] = a/b;
			rem[i] = a%b;
			a = rem[i] * 10;
		}

		System.out.println("done");
	}

	private static Object[] objs;
	private static void readVarargs(Object... arg) {
		objs = arg;

		System.out.println("size of varargs is " + objs.length);
		for (int i = 0; i < objs.length; i++) {
			System.out.println(objs[i]);
		}
	}

	// this means Integer is immutable., if countMap.put is skipped the value will remain as 0.
	private static void testIntegerMap() {
		Map<String, Integer> countMap = new HashMap<>();
		String[] strs = new String[]{"1", "2", "1"};

		for (String str : strs) {
			Integer count = countMap.get(str);
			if (count == null) {
				count = 0;
				countMap.put(str, count);
			}

			count = count + 1;
			countMap.put(str, count);
		}

		for (String str : strs) {
			System.out.println(countMap.get(str));
		}
	}

}
