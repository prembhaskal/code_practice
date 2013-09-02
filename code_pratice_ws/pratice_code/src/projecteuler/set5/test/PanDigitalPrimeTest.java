package projecteuler.set5.test; 

import algorithm.UtilitiesClass;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import projecteuler.set5.*;

/** 
* PanDigitalPrime Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 2, 2013</pre> 
* @version 1.0 
*/ 
public class PanDigitalPrimeTest { 
    
    PanDigitalPrime testClass = new PanDigitalPrime();
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
    * Method: getLargestPanDigitPrime() 
    * 
    */ 
    @Test
    public void testGetLargestPanDigitPrime() throws Exception { 
        
    }

	@Test
	public void testRotate() {
		int[] num = new int[]{1,2,3,4};

		testClass.leftRotate(num, 3);
		UtilitiesClass.printArray(num);
	}

	@Test
	public void testPermutation() {
		int num = 123456789;
		testClass.printPermutation(num);
	}
    
        
} 
