package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* SegmentDeleteTree Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Aug 18, 2013</pre> 
* @version 1.0 
*/ 
public class SegmentDeleteTreeTest { 
    
    SegmentDeleteTree testClass;
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
	public void testCreationOfSegDeleteTree() {
		int size = 4;

		SegmentDeleteTree deleteTree = new SegmentDeleteTree(size);
		deleteTree.printSegmentTree(System.out);
	}

} 
