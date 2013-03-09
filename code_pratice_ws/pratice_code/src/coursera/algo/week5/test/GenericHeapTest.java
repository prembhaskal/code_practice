package coursera.algo.week5.test; 

import algorithm.UtilitiesClass;
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

	@Test
	public void testInsertDeleteIndex() {
		HeapEntry[] heapEntries = getHeapEntries();

		testClass = new GenericHeap(heapEntries, 200);

		HeapEntry[] entries = testClass.getAllElements();
		printHeapEntries(entries);
		printIndexOfElement(40);

		HeapEntry entry;
		entry = new HeapEntry(11,110);
		testClass.insert(entry);
		entry = new HeapEntry(12,120);
		testClass.insert(entry);

		printHeapEntries(testClass.getAllElements());
		printIndexOfElement(20);
		testClass.extractMin();
		printHeapEntries(testClass.getAllElements());
		printIndexOfElement(20);

		testClass.delete(3);
		printHeapEntries(testClass.getAllElements());
		printIndexOfElement(90);

	}

	private void printIndexOfElement(int element) {
		System.out.println("index of element " + element + " is " + testClass.getIndexOf(element));
	}


	private void printHeapEntries(HeapEntry[] a) {
		int[] nums = new int[a.length];

		for (int i=0;i<nums.length;i++) {
			nums[i] = a[i].value;
		}

		UtilitiesClass.printArray(nums);
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
