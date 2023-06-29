package practice.string_problems.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.string_problems.*;  

/** 
* TestOpenClosedParenthesis Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 26, 2013</pre> 
* @version 1.0 
*/ 
public class TestOpenClosedParenthesisTest { 
    
    TestOpenClosedParenthesis testClass = new TestOpenClosedParenthesis();
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
    * Method: isMatched(String text) 
    * 
    */ 
    @Test
    public void testIsMatched() throws Exception {
    } 
    
        /** 
    * 
    * Method: ifAllBracketsMatched(String text) 
    * 
    */ 
    @Test
    public void testIfAllBracketsMatched() throws Exception {
		System.out.println(testClass.ifAllBracketsMatched(")))(((..(trt()tr)(t)(tt(....)tt)"));
    } 
    
        
    } 
