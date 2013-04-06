package topcoder.srm.s574.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s574.div2.*;  

/** 
* CityMap Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 6, 2013</pre> 
* @version 1.0 
*/ 
public class CityMapTest { 
    
    CityMap testClass = new CityMap();
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
    * Method: getLegend(String[] cityMap, int[] POIs) 
    * 
    */ 
    @Test
    public void testGetLegend() throws Exception { 
        String[] cityMap;
		int[] POIs;

		cityMap = new String[]{"M....M",
				"...R.M",
				"R..R.R"};
		POIs = new int[]{3, 4};
		System.out.println(testClass.getLegend(cityMap, POIs));

		cityMap = new String[]{"XXXXXXXZXYYY"};
		POIs = new int[]{1, 8, 3};
		System.out.println(testClass.getLegend(cityMap, POIs));


	}
    
        
} 
