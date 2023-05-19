package basics;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
    private int guesses;
    private int rem;

    private boolean isDone;

    public SinkStartUpGame(int[] locs) {
        this.locs = locs;
        this.rem = locs.length;
    }

    public String checkGuess(int guess) {
        if (isDone) {
            System.out.println("game already over");
            return "success";
        }
        guesses++;

        if (guess < 0) {
            System.err.printf("invalid guess: %d\n", guess);
            return "failure";
        }

        for (int i = 0; i < locs.length; i++) {
            if (locs[i] == guess) {
                locs[i] = -1;
                rem--;
                if (rem == 0) {
                    isDone = true;
                }
                return "success";
            }
        }
        return "failed";
    }

    public boolean isDone() {
        return isDone;
    }

    public void printSummary() {
        if (isDone) {
            System.out.printf("game over, total guess: %d\n", guesses);
        } else {
            System.out.printf("game not over, total guess: %d\n", guesses);
        }
    }

}

class SinkStartUpGameTest {
    public static void main(String[] args) {
        int startloc =  new Random().nextInt(0, 4);
        var locations = new int[3];
        for (int i = 0; i < 3; i++) {
            locations[i] = startloc;
            startloc++;
        }

        var game = new SinkStartUpGame(locations);

        while (!game.isDone()) {
            int userinput = readUserInput();
            var res = game.checkGuess(userinput);
            System.out.printf("guess: %d, result: %s\n", userinput, res);
        }

        game.printSummary();

//        var res = game.checkGuess(2);
//        if (res.equals("success")) {
//            System.out.printf("working\n");
//        } else {
//            System.out.printf("not working\n");
//            fail();
//        }
    }

    static int readUserInput() {
        System.out.printf("enter a number: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    static void fail() {
        throw new RuntimeException("test failed");
    }
}
