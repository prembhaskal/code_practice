package gfg.string;

import java.util.ArrayList;

// GFG https://www.geeksforgeeks.org/problems/closest-strings0611/1?page=1&sortBy=submissions
public class ClosestStrings {

    int shortestDistance(ArrayList<String> s, String word1, String word2) {
        // code here
        int p1 = -1;
        int p2 = -1;

        if (word1.equals(word2)) {
            return 0;
        }

        int minDist = s.size() + 1;
        for (int i = 0; i < s.size(); i++) {
            String curr = s.get(i);
            if (curr.equals(word1)) {
                p1 = i;
            }

            if (curr.equals(word2)) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                minDist = Math.min(minDist, Math.abs(p1 - p2));
            }
        }
        return minDist;
    }
}
