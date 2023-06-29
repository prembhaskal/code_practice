package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P21AmicableNos Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 2, 2013</pre> 
* @version 1.0 
*/ 
public class P21AmicableNosTest { 
    
    P21AmicableNos testClass = new P21AmicableNos();
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
    * Method: getAmicableNosSum(int range) 
    * 
    */ 
    @Test
    public void testGetAmicableNosSum() throws Exception {
		System.out.println("amicable sum till 10000 " + testClass.getAmicableNosSum(1000000));
	}

	@Test
	public void testSumOfDivisors() {
		System.out.println("sum of 220 divisors is " + testClass.getSumOfDivisors(220));
	}
    
        
} 
