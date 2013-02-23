package topcoder.srm.s567.div2.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s567.div2.*;  

/** 
* NinjaTurtles Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 21, 2013</pre> 
* @version 1.0 
*/ 
public class NinjaTurtlesTest { 
    
    NinjaTurtles testClass = new NinjaTurtles();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
        long now = System.nanoTime();
        System.out.println("elapsed time " + (now-starttime)/1000000000 + "secs");
    } 
    
        /** 
    * 
    * Method: countOpponents(int P, int K) 
    * 
    */ 
    @Test
    public void testCountOpponents() throws Exception { 
    //TODO: Test goes here...
		assertEquals(testClass.countOpponents(5,4),6);
		assertEquals(testClass.countOpponents(1,4),3);

		System.out.println(testClass.countOpponents(999999,99));

    } 
    
        
    } 
