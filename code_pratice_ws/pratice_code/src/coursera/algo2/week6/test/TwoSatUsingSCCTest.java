package coursera.algo2.week6.test; 

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;

import common.util.InputReader;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week6.*;  

/** 
* TwoSatUsingSCC Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 18, 2013</pre> 
* @version 1.0 
*/ 
public class TwoSatUsingSCCTest { 
    
    TwoSatUsingSCC testClass = new TwoSatUsingSCC();
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
    * Method: isSatisifiable(InputReader in) 
    * 
    */ 
    @Test
    public void testIsSatisfiable() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/graph.txt");
		InputReader in = new InputReader(inputStream);

		testClass.isSatisfiable(in);

		inputStream.close();
	}
    
        
} 
