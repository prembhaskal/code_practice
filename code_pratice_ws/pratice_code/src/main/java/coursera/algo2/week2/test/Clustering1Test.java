package coursera.algo2.week2.test; 

import common.util.InputReader;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week2.*;  

/** 
* Clustering1 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 18, 2013</pre> 
* @version 1.0 
*/ 
public class Clustering1Test { 
    
    Clustering1 testClass = new Clustering1();
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
    
    @Test
	public void getMaxSpacing() {
//		InputStream inputStream = getClass().getResourceAsStream("files/cluster1.txt");
		InputStream inputStream = getClass().getResourceAsStream("files/clustering1.txt");
		InputReader in = new InputReader(inputStream);

		int clusters = 4;
		int maxSpacing  = testClass.findMaxSpacing(in, clusters);

		System.out.println("max spacing is " + maxSpacing);
	}

} 
