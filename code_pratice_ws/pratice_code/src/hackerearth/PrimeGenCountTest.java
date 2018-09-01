package hackerearth;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by prem on 1/9/18.
 */
public class PrimeGenCountTest {

    long starttime;

    @Before
    public void before() throws Exception {
        starttime = System.nanoTime();
    }

    @After
    public void after() throws Exception {
        long now = System.nanoTime();
        System.out.println("elapsed time " + (now-starttime)/1000000 + "milli secs");
    }


    @Test
    public void testGen() {
        int[] primes = new PrimeGenCount().generatePrimes(100_000);

//        System.out.println("last prime is " + primes[3100]);
        System.out.println("primes count: " + primes.length);
    }

    @Test
    public void testCount() {
        PrimeGenCount primeGenCount = new PrimeGenCount();
        int[] primes = primeGenCount.generatePrimes(100_000);

        for (int i = 1; i <= 100_000; i++) {
            int actcount = getActualCountBrutFrc(primes, i);
            int fastcount = primeGenCount.getPrimeCountTillNum(primes, i);
            Assert.assertEquals("not same for number " + i, actcount, fastcount);
        }

        int[] rangecnt = primeGenCount.getRangeCount(primes, 100_000);
        for (int i = 0; i <= 100_000; i++) {
            int actcount = getActualCountBrutFrc(primes, i);
//            Assert.assertEquals("not same for number " + i, actcount, rangecnt[i]);
        }

        int test = getActualCountBrutFrc(primes, 99992);
        System.out.println("test is " + test);
    }

    private int getActualCountBrutFrc(int[] primes, int num) {
        int i;
        for (i = 0; i < primes.length; i++) {
            if (num == primes[i]) {
                return i + 1;
            }
            if (num < primes[i]) {
                return i;
            }
        }

        return i;
    }

    @Test
    public void checkWay() {
        int r1, r2, N;
        String cave;
        PrintWriter wr = new PrintWriter(System.out);
        PrimeGenCount genCount = new PrimeGenCount();

        r1 = 1;
        r2 = 2;
        N = 8;
        cave = "########";
        genCount.solve(r1, r2, N, cave, wr);

        r1 = 1;
        r2 = 2;
        N = 3;
        cave = "#*#";
        genCount.solve(r1, r2, N, cave, wr);

        wr.close();
    }

}