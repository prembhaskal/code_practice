package leetcode.recursive;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class P22GenValidParenthesis {
        public List<String> generateParenthesis(int n) {
//            return gen(n);
             validList = new ArrayList<>();
//             genbt(n, "", 0, 0);
             genbt1(n, new StringBuilder(), 0, 0);
             return validList;
        }

        List<String> validList = new ArrayList<>();

//    Approach
//    this is trying to generate the string, instead of enclosing previously created string
//    at each step, check if we can add '(', if left_count < n
//    at each step, check if we can add ')', if left_count > right_count
//    TODO - It is bit unclear how it generates without duplicates.
        public void genbt(int n, String str, int left, int right) {
            if (str.length() == 2 * n) {
                validList.add(str);
                return;
            }

            if (left < n) {
                genbt(n, str + "(", left+1, right);
            }
            if (left > right) { // proceed only if valid.
                genbt(n, str + ")", left, right+1);
            }
        }

        // Approach, same as above, but trying to save time using StringBuilder.
        public void genbt1(int n , StringBuilder str, int left, int right) {
            if (str.length() == 2 * n) {
                validList.add(str.toString());
                return;
            }
            if (left < n) {
                genbt1(n, str.append('('), left + 1, right);
                str.deleteCharAt(str.length()-1);
            }
            if (left > right) {
                genbt1(n, str.append(')'), left, right+1);
                str.deleteCharAt(str.length()-1);
            }
        }

        // approach
        // suppose previous patterns  p0 p1 p2 p3 ... p2n-2
        // we add '(' in begin and try putting ')' in different places
        // ( p0 ) p1 p2 ... pend
        // (p0 p1) p2 ... pend
        // and so on.
        // not good approach
        // TODO - convert to CATALAN number approach.
        public List<String> gen(int n) {
            if (n == 1) {
                List<String> list = new ArrayList<String>();
                list.add("()");
                return list;
            }
            List<String> next = gen(n-1);
            Set<String> newlist = new HashSet<>();
            for (String comb : next) {
                char[] cs = comb.toCharArray();

                for (int j = 1; j < cs.length + 2; j++) {
                    char[] patt = new char[cs.length + 2];
                    patt[0] = '(';
                    patt[j] = ')';
                    int m = 0;
                    for (int k = 0; k < cs.length+2; k++) {
                        if (k != 0 && k != j) {
                            patt[k] = cs[m];
                            m++;
                        }
                    }
                    newlist.add(new String(patt));
                }

            }

            return new ArrayList<String>(newlist);
        }

}
