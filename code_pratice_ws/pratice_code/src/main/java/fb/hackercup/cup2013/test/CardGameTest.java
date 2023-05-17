package fb.hackercup.cup2013.test; 

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import fb.hackercup.cup2013.*;  

/** 
* CardGame Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 3, 2013</pre> 
* @version 1.0 
*/ 
public class CardGameTest { 
    
    CardGame testClass = new CardGame();
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
    * Method: solve(Scanner in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
		Scanner in = new Scanner(getClass().getResourceAsStream("files/card_game.txt"));
		File output = new File("output/card_game_output.txt");
		PrintWriter out = new PrintWriter(output);

		testClass.solve(in, out);

		in.close();
		out.close();
	}
    

} 
