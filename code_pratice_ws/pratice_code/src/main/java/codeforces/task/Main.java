package codeforces.task;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TestSolution solution = new TestSolution();
		solution.testSolution(in,writer);

		writer.close();
		in.close();


	}

}

	class TestSolution {
		public void testSolution(Scanner in, PrintWriter out) {
			int n = in.nextInt();
			String[] s = new String[1000];
			int[] score = new int[1000];

			int i =0;
			while (in.hasNext()) {
				s[i] = in.next();
				score[i] = in.nextInt();
				i++;
			}

			Map<String, Integer> map = new HashMap<String, Integer>();
			for (int k=0;k<n;k++) {
				if (map.get(s[k]) != null) {
					map.put(s[k], map.get(s[k]) + score[k]);
				} else {
					map.put(s[k], score[k]);
				}
			}

			// find the max
			int max = Integer.MIN_VALUE;
			int val;
			for (int k=0;k<n;k++) {
				val = map.get(s[k]);
				if (max < val)
					max = val;
			}

			// find player (amongst who have max) who reaches more than or equal to m the earliest
			Map<String, Integer> map2 = new HashMap<String, Integer>();

			int k;
			for (k=0;k<n;k++) {
				if (map2.get(s[k]) != null) {
					val = map2.get(s[k]) + score[k];
					map2.put(s[k], val);

				} else {
					map2.put(s[k], score[k]);
				}

				if (map2.get(s[k]) >= max && map.get(s[k])>=max)
					break;

			}

			out.println(s[k]);
		}
	}

