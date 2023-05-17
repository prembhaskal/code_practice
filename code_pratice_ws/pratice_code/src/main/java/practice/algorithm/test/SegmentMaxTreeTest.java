package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* SegmentMaxTree Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 5, 2013</pre> 
* @version 1.0 
*/ 
public class SegmentMaxTreeTest { 

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
    * Method: query(int low, int high) 
    * 
    */ 
    @Test
    public void testQuery() throws Exception {
		int[] array = new int[]{3,2,4,0,9};

		SegmentMaxTree minTree= new SegmentMaxTree(array);

		System.out.println(minTree.query(0,0));
		System.out.println(minTree.query(0,1));
		System.out.println(minTree.query(0,2));
		System.out.println(minTree.query(3,3));
		System.out.println(minTree.query(3,4));
    } 
    
        
} 
