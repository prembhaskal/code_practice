package fb.hackercup.cup2013.test; 

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import fb.hackercup.cup2013.*;  

/** 
* BalancedSmileys Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 26, 2013</pre> 
* @version 1.0 
*/ 
public class BalancedSmileysTest { 
    
    BalancedSmileys testClass = new BalancedSmileys();
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
    * Method: solve(Scanner in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
		Scanner in = new Scanner(getClass().getResourceAsStream("files/balanced_smiley.txt"));
		File output = new File("output/balanced_smiley.txt");
		PrintWriter out = new PrintWriter(output);

		testClass.solve(in, out);

		in.close();
		out.close();
    } 
    
        
        /** 
    * 
    * Method: balancedSmileys(String text, int testNo, PrintWriter out) 
    * 
    */
                                     
    } 
