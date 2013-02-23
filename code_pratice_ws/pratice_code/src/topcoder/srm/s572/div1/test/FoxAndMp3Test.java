package topcoder.srm.s572.div1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s572.div1.*;  

/** 
* FoxAndMp3 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 20, 2013</pre> 
* @version 1.0 
*/ 
public class FoxAndMp3Test { 
    
    FoxAndMp3 testClass = new FoxAndMp3();
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
    * Method: playList(int n) 
    * 
    */ 
    @Test
    public void testPlayList() throws Exception { 
        String[] strings = testClass.playList(109);

		for(String str: strings)
			System.out.println(str);
	}
    
        
} 
