package com.prem.srm.m567.div2.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import com.prem.srm.m567.div2.*;  

/** 
* TheSquareRootDilemma Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 22, 2013</pre> 
* @version 1.0 
*/ 
public class TheSquareRootDilemmaTest { 
    
    TheSquareRootDilemma testClass = new TheSquareRootDilemma();
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
    * Method: countPairs(int N, int M) 
    * 
    */ 
    @Test
    public void testCountPairs() throws Exception { 
    //TODO: Test goes here...
//		assertEquals(testClass.countPairs(3,8),5);


    } 
    
        
    } 
