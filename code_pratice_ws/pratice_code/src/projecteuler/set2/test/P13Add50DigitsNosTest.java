package projecteuler.set2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set2.*;  

/** 
* P13Add50DigitsNos Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 30, 2013</pre> 
* @version 1.0 
*/ 
public class P13Add50DigitsNosTest { 
    
    P13Add50DigitsNos testClass = new P13Add50DigitsNos();
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
    * Method: addAllNumbers(Scanner in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testAddAllNumbers() throws Exception {
		Scanner in = new Scanner(getClass().getResourceAsStream("files/50_digit_nos.txt"));
		PrintWriter out = new PrintWriter(System.out);
        testClass.addAllNumbers(in, out);

		in.close();
		out.flush();
    } 
    
        
} 
