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
* ShortestJohnsonAlgorithm Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 4, 2013</pre> 
* @version 1.0 
*/ 
public class ShortestJohnsonAlgorithmTest { 
    
    ShortestJohnsonAlgorithm testClass = new ShortestJohnsonAlgorithm();
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
//		InputStream inputStream = getClass().getResourceAsStream("files/week5_large.txt");
		InputStream inputStream = getClass().getResourceAsStream("files/g3.txt");
		InputReader in = new InputReader(inputStream);

		testClass.getShortestShortestPath(in);

		inputStream.close();
    }
    
        
} 
