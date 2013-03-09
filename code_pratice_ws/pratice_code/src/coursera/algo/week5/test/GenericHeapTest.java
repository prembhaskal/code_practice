package coursera.algo.week5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week5.*;  

/** 
* GenericHeap Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 9, 2013</pre> 
* @version 1.0 
*/ 
public class GenericHeapTest { 
    
    GenericHeap testClass;
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
	public void testGenericHeap() {
		HeapEntry[] heapEntries = getHeapEntries();
		testClass = new GenericHeap(heapEntries);

		for (int i=0;i<heapEntries.length;i++) {
			HeapEntry min = testClass.extractMin();
			System.out.println("min key " + min.key + " min value " + min.value);
		}
	}


	private HeapEntry[] getHeapEntries() {
		HeapEntry[] heapEntries = new HeapEntry[10];

		for (int i=1;i<11;i++) {
			HeapEntry entry = new HeapEntry(i, (i<<2<<1) + (i<<1));
			heapEntries[i-1] = entry;
		}

		return heapEntries;
	}
} 
