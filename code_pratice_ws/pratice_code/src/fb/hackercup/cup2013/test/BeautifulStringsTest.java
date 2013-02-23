package fb.hackercup.cup2013.test; 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import fb.hackercup.cup2013.*;  

/** 
* BeautifulStrings Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 26, 2013</pre> 
* @version 1.0 
*/ 
public class BeautifulStringsTest { 
    
    BeautifulStrings testClass = new BeautifulStrings();
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
    * Method: solver(String text, int testCase) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
//		testClass.solve("ABbCcc",1);
//		testClass.solve("Good luck in the Facebook Hacker Cup this year!",2);
		testClass.solve("abcdefghijklmnopqrstuvwxyz",1);
//		testClass.solve("vmnrzV)wXYyV!iqHKclWPgu.adkG.Nz)TEfyjLu.Ina:Epo(i)ZQwxI JCItPwPuL!.QeSiWWcejugIJ:;Jm(ePJFtaHOZT ISffEebjuJRagGcrs",3);
//		testFile();
	}

	private void testFile() throws Exception {
		Scanner in = new Scanner(this.getClass().getResourceAsStream("files/beauty.txt"));
		File file = new File("output/beauty_output.txt");
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
