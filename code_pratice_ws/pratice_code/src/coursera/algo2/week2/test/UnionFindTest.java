package coursera.algo2.week2.test; 

import org.junit.Before;
import org.junit.After;
import coursera.algo2.week2.*;

/** 
* NaiveUnionFind Tester.
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 18, 2013</pre> 
* @version 1.0 
*/ 
public class UnionFindTest { 
    
    NaiveUnionFind testClass = new NaiveUnionFind();
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
    
        
} 
