package fb.hackercup.cup2012.test; 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import fb.hackercup.cup2012.*;  

/** 
* AlphabetSoup Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 20, 2013</pre> 
* @version 1.0 
*/ 
public class AlphabetSoupTest { 
    
    AlphabetSoup testClass = new AlphabetSoup();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
        long now = System.nanoTime();
//        System.out.println("elapsed time " + (now-starttime)/1000000000 + "secs");
    } 
    
        /** 
    * 
    * Method: solve(String text) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
    	testClass.solve("WELCOME TO FACEBOOK HACKERCUP",1);
		testClass.solve("CUP WITH LABEL HACKERCUP BELONGS TO HACKER",2);
		testClass.solve("QUICK CUTE BROWN FOX JUMPS OVER THE LAZY DOG",3);
		testClass.solve("MOVE FAST BE BOLD",4);
		testClass.solve("HACK THE HACKERCUP",5);
    }

	@Test
	public void testInputFile() throws FileNotFoundException {
		Scanner in = new Scanner(this.getClass().getResourceAsStream("alphabet_soup.txt"));
		File file = new File("output/alphabet_output.txt");
		PrintWriter out = new PrintWriter(file);
		int i = 1;
		int tcases = Integer.parseInt(in.nextLine());
		while (tcases > 0) {
			out.println(testClass.solve(in.nextLine(), i++));
			tcases--;
		}

		in.close();
		out.close();
	}


    
        
    } 
