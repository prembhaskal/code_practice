package com.prem.test.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: premkumar.bhaskal
 * Date: 12/27/12
 * Time: 3:16 PM
 */
public class TestClass {

	public static void main(String[] s) {
		testMapOfIntAndList();
	}


	// consider a map having the value as an object, so once we get a reference to the object,
	// and then we modify it, we need not set it back in the map. since map will only be storing a
	// reference to actual object.
	private static void testMapOfIntAndList() {
		Map<Integer, List<String>> mapOfIntStringList = new HashMap<Integer, List<String>>();

		// prepare data
		List<String> strList = new ArrayList<String>();
		strList.add("first");

		mapOfIntStringList.put(1,strList);

		System.out.println("list from map");
		printListItems(mapOfIntStringList.get(1));

		List<String> listRef = mapOfIntStringList.get(1);
		System.out.println("list reference from map");
		printListItems(listRef);

		listRef.add("second");

		System.out.println("/n list reference added a new string, but not in map");
		printListItems(listRef);

		System.out.println("/n list from map, modified using external reference");
		printListItems(mapOfIntStringList.get(1));
	}



	private static void printListItems(List<String> list) {
		for(String str: list)
			System.out.println(str);
	}
}
