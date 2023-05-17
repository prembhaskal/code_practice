package basics;

import java.util.Arrays;

public class ScratchPad {

    public static void main(String[] s) {
        System.out.printf("hello world\n");

        String[] wordlist = {"one", "two", "three"};
        System.out.printf("word's list is %s \n", wordlist);
        System.out.printf("word's list with arrays.toString is %s \n", Arrays.toString(wordlist));
        System.out.printf("word's list length is %d \n", wordlist.length);
    }
}
