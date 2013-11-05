package test_generator.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import test_generator.*;  

/** 
* MazeLevelGenerator Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 6, 2013</pre> 
* @version 1.0 
*/ 
public class MazeLevelGeneratorTest { 
    
    MazeLevelGenerator testClass = new MazeLevelGenerator();
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
    * Method: generateLevel(InputReader in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testGenerateLevel() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/maze_levels.txt");
		InputReader in = new InputReader(inputStream);

		PrintWriter out = new PrintWriter("output/maze_level_out.txt");

		testClass.generateLevel(in, out);

		inputStream.close();
		out.flush();
    } 
    
        
} 
