package topcoder.srm.s575.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s575.div2.*;  

/** 
* TheNumberGameDivTwo Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 6, 2013</pre> 
* @version 1.0 
*/ 
public class TheNumberGameDivTwoTest { 
    
    TheNumberGameDivTwo testClass = new TheNumberGameDivTwo();
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
    * Method: find(int n) 
    * 
    */ 
    @Test
    public void testFind() throws Exception {
		System.out.println(testClass.find(6));
		System.out.println(testClass.find(1000));
		System.out.println(testClass.find(52));
	}
    
        /** 
    * 
    * Method: getBigOddDivisorForEvenNumber(int num) 
    * 
    */ 
    @Test
    public void testIsWinningMethod() throws Exception {
		boolean canWin;
		canWin = testClass.isWinning(1000);
		System.out.println("can John win " + canWin);
	}
    
        
} 
