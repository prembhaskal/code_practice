package coursera.algo.week3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week3.*;  

/** 
* KargerMinCut Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 23, 2013</pre> 
* @version 1.0 
*/ 
public class KargerMinCutTest { 
    
    KargerMinCut testClass = new KargerMinCut();
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

        Scanner in = new Scanner(getClass().getResourceAsStream("files/kargerMinCut.txt"));
		try{
		PrintWriter out = new PrintWriter(System.out);
		int noOfVertices = 200;
		testClass.solve(in, out, noOfVertices);

		out.flush();
		} finally {
			in.close();
		}

    } 
    
        
} 
