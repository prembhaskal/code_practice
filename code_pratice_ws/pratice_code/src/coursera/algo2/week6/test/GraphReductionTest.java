package coursera.algo2.week6.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week6.*;  

/** 
* GraphReduction Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 19, 2013</pre> 
* @version 1.0 
*/ 
public class GraphReductionTest { 
    
    GraphReduction testClass = new GraphReduction();
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
    * Method: reduceTheGraph(InputReader in) 
    * 
    */ 
    @Test
    public void testReduceTheGraph() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/2sat6.txt");
		InputReader in = new InputReader(inputStream);

		int remainingClauses = testClass.reduceTheGraph(in);

		System.out.println("remaining clauses are " + remainingClauses);

		inputStream.close();
	}
    
        
} 
