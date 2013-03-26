package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P8greatest5digitProduct Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 26, 2013</pre> 
* @version 1.0 
*/ 
public class P8greatest5digitProductTest { 
    
    P8greatest5digitProduct testClass = new P8greatest5digitProduct();
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
    * Method: findGreatestProductof5ConsDigit(Scanner in, int digits) 
    * 
    */ 
    @Test
    public void testFindGreatestProductof5ConsDigit() throws Exception { 
        Scanner in = new Scanner(getClass().getResourceAsStream("files/1000_digit_number.txt"));
		System.out.println("greatest product is " + testClass.findGreatestProductof5ConsDigit(in, 1000));
    } 
    
        
} 
