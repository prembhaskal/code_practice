package fb.hackercup.cup2013_2.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import fb.hackercup.cup2013_2.*;  

/** 
* LabelMaker Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 8, 2013</pre> 
* @version 1.0 
*/ 
public class LabelMakerTest { 
    
    LabelMaker testClass = new LabelMaker();
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
    * Method: solve(InputReader in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/label_maker_input.txt");
		InputReader in = new InputReader(inputStream);

//		PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter("output/label_maker_output.txt");
		testClass.solve(in, out);

		out.flush();
		inputStream.close();
    } 
    
        
} 
