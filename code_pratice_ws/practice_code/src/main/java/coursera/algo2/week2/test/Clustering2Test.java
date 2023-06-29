package coursera.algo2.week2.test; 

import common.util.InputReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week2.*;  

/** 
* Clustering2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 19, 2013</pre> 
* @version 1.0 
*/ 
public class Clustering2Test { 
    
    Clustering2 testClass = new Clustering2();
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
	public void testClustering() throws IOException {
		InputStream inputStream = getClass().getResourceAsStream("files/clustering_big.txt");
		InputReader in = new InputReader(inputStream);

		int maxClusters = testClass.getNoOfClusters(in);

		System.out.println(maxClusters);

		inputStream.close();
	}
    
        
} 
