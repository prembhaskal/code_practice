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
* FindTheMin Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 26, 2013</pre> 
* @version 1.0 
*/ 
public class FindTheMinTest { 
    
    FindTheMin testClass = new FindTheMin();
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

		Scanner in = new Scanner(getClass().getResourceAsStream("files/findthemin.txt"));
		File output = new File("output/find_themin_output.txt");
		PrintWriter out = new PrintWriter(output);

		testClass.solve(in,out);

		in.close();
		out.close();
    } 
    
        
        /** 
    * 
    * Method: findTheMin(int testCase, Scanner in, PrintWriter out) 
    * 
    */
                                     
        /** 
    * 
    * Method: printValues(int[] nums) 
    * 
    */
                                     
    } 
