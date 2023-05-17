package projecteuler.set7.test; 

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import projecteuler.set7.*;

/** 
* P62CubicPermutation Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 28, 2014</pre> 
* @version 1.0 
*/ 
public class P62CubicPermutationTest { 
    
    P62CubicPermutation testClass = new P62CubicPermutation();
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
    * Method: transformToDigitCountRepresentation(int number)
    * 
    */ 
    @Test
    public void testConvertToDigitCount() throws Exception { 
        int num = 240689;
		String converted = testClass.transformToDigitCountRepresentation(num);
		System.out.println("converted number is " + converted);

    }

	@Test
	public void solve() throws Exception {
		int limit = 10000;
		int matching = 5;
		testClass.solve(limit, matching);
	}
    
        
} 
