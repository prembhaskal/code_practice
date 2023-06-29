package fb.hackercup.cup2013_2.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import fb.hackercup.cup2013_2.*;

/** 
* Basketball Tester.
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 23, 2013</pre> 
* @version 1.0 
*/ 
public class BasketballTest {
    
    Basketball testClass = new Basketball();
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
    * Method: solve(InputReader in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/basketball_input.txt");
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter("output/basketball_output.txt");
		testClass.solve(in, out);

		inputStream.close();
		out.flush();
    } 
    
        
} 
