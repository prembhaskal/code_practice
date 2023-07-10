package basics;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ScratchPad {

    public static void main(String[] args) {
        try {
            runMain(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runMain(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> task = new Callable<Integer>() {

            @Override
            public Integer call() {
                printf("printing from thread ... \n");
                return 1;
            }
        };

        Future<?> future = executorService.submit(task);
        executorService.shutdown();

        var ans = future.get();

//        Thread.currentThread().sleep(200);

        printf("printing from main ... \n");
        printf("result from thread: %d\n", ans);

    }

    public static void printf(String msg, Object... s) {
        System.out.printf(msg, s);
    }
}

