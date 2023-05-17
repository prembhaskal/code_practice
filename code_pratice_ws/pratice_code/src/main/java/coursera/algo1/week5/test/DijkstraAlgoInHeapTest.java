package coursera.algo1.week5.test;

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import coursera.algo1.week5.*;

/** 
* DijkstraAlgoInHeap Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 9, 2013</pre> 
* @version 1.0 
*/ 
public class DijkstraAlgoInHeapTest { 
    
    DijkstraAlgoInHeap testClass = new DijkstraAlgoInHeap();
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
    * Method: solve(Scanner in, PrintWriter out, int nodes) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
//        Scanner in = new Scanner(getClass().getResourceAsStream("files/test_graph2.txt"));
		Scanner in = new Scanner(getClass().getResourceAsStream("files/dijkstraData.txt"));
		PrintWriter out = new PrintWriter(System.out);

//		testClass.solve(in, out, 10);
		testClass.solve(in, out, 200);

		in.close();
		out.flush();
    } 
    
        /** 
    * 
    * Method: addNeighbour(int fromNode, Node toNode) 
    * 
    */ 
    @Test
    public void testAddNeighbour() throws Exception { 
        
    } 
    
        
} 
