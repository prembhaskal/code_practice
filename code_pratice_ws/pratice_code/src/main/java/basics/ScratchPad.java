package basics;

import java.util.*;

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
    private List<Integer> locs;
    private int guesses;
    private int rem;

    private boolean isDone;

    public SinkStartUpGame(int[] locarray) {
        this.locs = new LinkedList<>();
        for (int loc: locarray) {
            locs.add(loc);
        }
        this.rem = locarray.length;
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

        Iterator<Integer> iter = locs.iterator();
        while (iter.hasNext()) {
            int loc = iter.next();
            if (loc == guess) {
                iter.remove();
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
