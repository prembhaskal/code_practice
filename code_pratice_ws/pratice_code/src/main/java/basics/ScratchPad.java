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

class SinkStartUpGame {
    private int[] locs;

    public SinkStartUpGame(int[] locs) {
        this.locs = locs;
    }

    public String checkGuess(int guess) {
        for (int i = 0; i < locs.length; i++) {
            if (locs[i] == guess) {
                return "success";
            }
        }
        return "failed";
    }

    public boolean isDone() {
        return false;
    }

}

class SinkStartUpGameTest {
    public static void main(String[] args) {
        var locations = new int[]{2, 4, 5};
        var game = new SinkStartUpGame(locations);

        var res = game.checkGuess(2);
        if (res.equals("success")) {
            System.out.printf("working\n");
        } else {
            System.out.printf("not working\n");
            fail();
        }
    }

    static void fail() {
        throw new RuntimeException("test failed");
    }
}
