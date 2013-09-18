package coursera.algo2.week2.test; 

import org.junit.Before;
import org.junit.After;
import coursera.algo2.week2.*;
import org.junit.Test;

/** 
* NaiveUnionFind Tester.
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 18, 2013</pre> 
* @version 1.0 
*/ 
public class UnionFindTest { 
    
    NaiveUnionFind testClass;
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

	@Test
	public void testUnionFind() {
		testClass = new NaiveUnionFind(6);

		printLeaders();

		// merge 1 and 2
		testClass.merge(1,2);
		printLeaders();

		testClass.merge(3,4);
		printLeaders();

		testClass.merge(1,3);
		printLeaders();

		testClass.merge(1,4);
		printLeaders();

		testClass.merge(5,6);
		printLeaders();

		testClass.merge(1,6);
		printLeaders();
	}

	private void printLeaders() {
		System.out.println("--------------------------");
		for (int i = 1; i < 7; i++) {
			System.out.println("leader for vertex --> " + i + " is " + testClass.find(i));
		}
		System.out.println("--------------------------\n");
	}
    
        
} 
