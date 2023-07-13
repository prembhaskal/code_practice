package basics;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScratchPad {

    public static void main(String[] args) {
        try {
            new ScratchPad().runMain(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runMain(String[] args) throws Exception {
        new StreamBasics().runStreamExamples();
    }


}

class Sys {
    public static void printf(String msg, Object... s) {
        System.out.printf(msg, s);
    }

    public static void println(Object... s) {
        for (var obj: s) {
            System.out.print(obj);
            System.out.print(" ");
        }
        System.out.println();
    }
}