package test_generator.test; 

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import test_generator.*;  

/** 
* TestGenerator Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 1, 2013</pre> 
* @version 1.0 
*/ 
public class TestGeneratorTest { 
    
    TestGenerator testClass = new TestGenerator();
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
    * Method: generateTest(InputReader in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testGenerateTest() throws Exception { 
        File testFile = new File("output/testFile.txt");
		PrintWriter out = new PrintWriter(testFile);

		testClass.generateTest(null, out);

		out.close();
    } 
    
        
} 
