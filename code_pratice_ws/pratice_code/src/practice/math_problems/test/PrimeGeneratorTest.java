package practice.math_problems.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;  

/** 
* PrimeGenerator Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 2, 2013</pre> 
* @version 1.0 
*/ 
public class PrimeGeneratorTest { 
    
    PrimeGenerator testClass = new PrimeGenerator();
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
    * Method: generatePrimeUsingOptimalTrialDivision(int range) 
    * 
    */ 
    @Test
    public void testGeneratePrimeUsingOptimalTrialDivision() throws Exception { 
        int start = 2;
		int end = 1000_000;

		testClass.generatePrimeUsingOptimalTrialDivision(end);
    }

	@Test
	public void testGenerategeneratePrimesSieveOfEratNormal() throws Exception {
		int end = 1000_000;
		testClass.generatePrimesSieveOfEratNormal(end);
	}

	@Test
	public void testGeneratePrimesInRangeTrialDivision() throws Exception {
		int start = 1000_000_000;
		int end = 1001_000_000;

		// NOTE start - end < 1000_000;

		testClass.generatePrimesInRangeTrialDivision(start, end);
	}


	@Test
	public void testGeneratePrimesInRangeUsingSieve() throws Exception {
		int start = 1010000000;
		int end = 1011000000;
//		1010000000 1011000000
		// NOTE start - end < 1000_000;

		testClass.generatePrimesInRangeUsingSeive(start, end);
	}
} 
