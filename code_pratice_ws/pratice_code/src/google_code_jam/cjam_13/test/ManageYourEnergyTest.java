package google_code_jam.cjam_13.test; 

import common.util.InputReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.cjam_13.*;  

/** 
* ManageYourEnergy Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 1, 2013</pre> 
* @version 1.0 
*/ 
public class ManageYourEnergyTest { 
    
    ManageYourEnergy testClass = new ManageYourEnergy();
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
		InputStream inputStream = getClass().getResourceAsStream("files/manage_energy_input.txt");
		InputReader in = new InputReader(inputStream);

//		OutputStream outputStream = System.out;
		OutputStream outputStream = new FileOutputStream("output/manage_energy_output.txt");

		PrintWriter out = new PrintWriter(outputStream);

		testClass.solve(in, out);

		inputStream.close();
		out.close();
    } 
    
        
} 
