package coursera.algo.week4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week4.*;  

/** 
* StrongConnectedComp Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 1, 2013</pre> 
* @version 1.0 
*/ 
public class StrongConnectedCompTest { 
    
    StrongConnectedComp testClass = new StrongConnectedComp();
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
//        Scanner in = new Scanner(getClass().getResourceAsStream("files/graph_1.txt"));
		Scanner in = new Scanner(getClass().getResourceAsStream("files/SCC.txt"));
		PrintWriter out = new PrintWriter(System.out);

//		testClass.solve(in, out, 9);
		testClass.solve(in, out, 875714);

		in.close();
		out.flush();
    } 
    
        
} 
