package google_code_jam.cjam_14.test; 

import common.util.InputReader;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.cjam_14.*;  

/** 
* CookieClickerAlpha Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 12, 2014</pre> 
* @version 1.0 
*/ 
public class CookieClickerAlphaTest { 
    
    CookieClickerAlpha testClass = new CookieClickerAlpha();
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
		InputStream inputStream = getClass().getResourceAsStream("files/cookie_input.txt");
		InputReader inputReader = new InputReader(inputStream);
		File file = new File("output/cookie_output.txt");
		PrintWriter out = new PrintWriter(file);

		testClass.solve(inputReader, out);

		inputStream.close();
		out.close();
    } 
    
        
} 
