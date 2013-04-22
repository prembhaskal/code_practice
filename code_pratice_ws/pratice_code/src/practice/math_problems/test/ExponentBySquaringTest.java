package practice.math_problems.test; 

import java.util.Scanner;
import java.io.PrintWriter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;  

/** 
* ExponentBySquaring Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 22, 2013</pre> 
* @version 1.0 
*/ 
public class ExponentBySquaringTest { 
    
    ExponentBySquaring testClass = new ExponentBySquaring();
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
    * Method: powerIterative(long num, long pow) 
    * 
    */ 
    @Test
    public void testPowerIterative() throws Exception { 
        int num = 37;

		for (int i=1;i<1000;i++) {
			long power1 = testClass.powerIterative(num, i);
			long power2 = testClass.powerRecursive(num, i);

			if (power1 != power2) {
				System.out.println("power -->" + i + " power1 -->" + power1 + " power2 -->" + power2);
				System.out.println("improper recursive method");
				Assert.fail();
				break;
			}
		}
    } 
    
	@Test
	public void testRecursive() {
		int num = 23353535;
		int power = 1000000000;

		long pow = testClass.powerRecursive(num, power);

		System.out.println(num + " power " + power + " is " + pow);
	}
} 
