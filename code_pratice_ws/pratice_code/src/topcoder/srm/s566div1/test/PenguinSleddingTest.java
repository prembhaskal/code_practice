package topcoder.srm.s566div1.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s566div1.*;  

/** 
* PenguinSledding Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 18, 2013</pre> 
* @version 1.0 
*/ 
public class PenguinSleddingTest { 
    
    PenguinSledding testClass = new PenguinSledding();
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
    * Method: countDesigns(int numCheckpoints, int[] checkpoint1, int[] checkpoint2) 
    * 
    */ 
    @Test
    public void testCountDesigns() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        
    } 
