package coursera.algo2.week4.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week4.*;  

/** 
* ShortestShortestPath Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 1, 2013</pre> 
* @version 1.0 
*/ 
public class ShortestShortestPathTest { 
    
    ShortestShortestPath testClass = new ShortestShortestPath();
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
    * Method: getShortestShortestPath(InputReader in) 
    * 
    */ 
    @Test
    public void testGetShortestShortestPath() throws Exception {
//		InputStream inputStream = getClass().getResourceAsStream("files/g1.txt");
//		InputStream inputStream = getClass().getResourceAsStream("files/g2.txt");
//		InputStream inputStream = getClass().getResourceAsStream("files/g3.txt");
		InputStream inputStream = getClass().getResourceAsStream("files/week5_large.txt");
		InputReader in = new InputReader(inputStream);

		Integer shortestPath = testClass.getShortestShortestPath(in);

		if (shortestPath == null)
			System.out.println("graph has negative cycle");
		else
			System.out.println("shortest shortest path is " + shortestPath);
		inputStream.close();
    } 
    

        
} 
