package practice.math_problems.test; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;  

/** 
* CheckPrime Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 6, 2013</pre> 
* @version 1.0 
*/ 
public class CheckPrimeTest { 
    
    CheckPrime testClass = new CheckPrime();
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
    
        /** 
    * 
    * Method: isPrime(long prime) 
    * 
    */ 
    @Test
    public void testIsPrime() throws Exception {
		System.out.println("is the number prime " + testClass.isPrime(12154365463416749L));

		long num = 12154365463416748L;

		while (true) {
			boolean isPrime = testClass.isPrime(num);
			if (isPrime) {
				break;
			}
			num--;
		}

		System.out.println("the previous prime is " + num);
	}

	// list of primes
	/*
	* 12154365463416749
	*
	* */


	@Test
	public void testPreviousPrimeFinding() {
		List<Long> primes1 = getPrimes(25, 500);
		List<Long> primes2 = getPrimes(100, 500);

		for (int i=0;i<primes1.size();i++) {
			if (!primes1.get(i).equals(primes2.get(i))) {
				System.out.println("primes not matching " + primes1.get(i) + "--" + primes2.get(i));
			}
		}
	}


	private List<Long> getPrimes(int certainty, int totalNum) {
		List<Long> primes = new ArrayList<Long>();

//		long startNum = 12399999999999999L;
		long startNum = 10000L;

		while (totalNum > 0) {

			while (true) {
				boolean isPrime = testClass.isPrime(startNum,certainty);
				if (isPrime) {
					System.out.println("prime number " + totalNum + " is " + startNum);
					totalNum--;
					primes.add(startNum);
					break;
				}
				startNum--;
			}

			startNum--;
		}

		return primes;
	}
        
} 
