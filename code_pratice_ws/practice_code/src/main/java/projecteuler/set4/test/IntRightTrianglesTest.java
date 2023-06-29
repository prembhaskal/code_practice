package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* IntRightTriangles Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Aug 25, 2013</pre> 
* @version 1.0 
*/ 
public class IntRightTrianglesTest { 
    
    IntRightTriangles testClass = new IntRightTriangles();
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
    * Method: getValueWithMaxTriangles() 
    * 
    */ 
    @Test
    public void testGetValueWithMaxTriangles() throws Exception {
		System.out.println("no of solutions for 120 is " + testClass.getNumberOfSolutionsForPerimeter(120));

		System.out.println("max solutions available for " + testClass.getValueWithMaxTriangles());
	}
    
        
} 
