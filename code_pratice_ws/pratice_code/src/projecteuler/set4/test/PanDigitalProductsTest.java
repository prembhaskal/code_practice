package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* PanDigitalProducts Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 20, 2013</pre> 
* @version 1.0 
*/ 
public class PanDigitalProductsTest { 
    
    PanDigitalProducts testClass = new PanDigitalProducts();
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
    * Method: getPanDigitalProductSum() 
    * 
    */ 
    @Test
    public void testGetPanDigitalProductSum() throws Exception { 
        long sum = testClass.getPanDigitalProductSum();

		System.out.println("sum of pan digital prod is " + sum);
	}

	@Test
	public void testPandigitalProd() {
		int prod = 7254;

		System.out.println("is the number pan digital product " + testClass.isPanDigitalProduct(prod));
	}
    
        
} 
