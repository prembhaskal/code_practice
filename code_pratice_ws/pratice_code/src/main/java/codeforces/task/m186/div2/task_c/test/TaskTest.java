package codeforces.task.m186.div2.task_c.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import codeforces.task.m186.div2.task_c.*;  

/** 
* Task Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 1, 2013</pre> 
* @version 1.0 
*/ 
public class TaskTest {

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
		System.out.println("power for " + getPower(260000));
	}


	private int getPower(int size) {
		int power = 0;
		while (size > 1) {
			size /= 4;
			power++;
		}

		return power;
	}



} 
