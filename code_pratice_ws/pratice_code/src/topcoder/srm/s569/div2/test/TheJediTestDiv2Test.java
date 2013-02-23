package topcoder.srm.s569.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s569.div2.*;  

/** 
* TheJediTestDiv2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 9, 2013</pre> 
* @version 1.0 
*/ 
public class TheJediTestDiv2Test { 
    
    TheJediTestDiv2 testClass = new TheJediTestDiv2();
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
    * Method: countSupervisors(int[] students, int Y, int J) 
    * 
    */ 
    @Test
    public void testCountSupervisors() throws Exception { 
        int[] students;
		int Y,J;

		students = new int[]{11,13,15};
		Y = 9;
		J = 5;

		System.out.println(testClass.countSupervisors(students,Y,J));

		students = new int[]{10,15};
		Y = 12;
		J = 5;
		System.out.println(testClass.countSupervisorsWay3(students,Y,J));

		students = new int[]{10};
		Y = 100;
		J = 2;
		System.out.println(testClass.countSupervisorsWay3(students,Y,J));
	}
    
        
} 
