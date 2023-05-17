package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* Solution Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jul 22, 2014</pre> 
* @version 1.0 
*/ 
public class SolutionTest { 
    
    Solution testClass = new Solution();
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
    * Method: getMaxMovies(int[] movie_start, int[] movie_end) 
    * 
    */ 
    @Test
    public void testGetMaxMovies() throws Exception { 
        int movies;
		int[] start;
		int[] end;

		start = new int[] {10, 12, 9, 14, 16, 14};
		end = new int[] {11, 13, 15, 16, 18, 18};
		movies = testClass.getMaxMovies(start, end);

		System.out.println(movies);
	}
    
        
} 
