package coursera.algo1.week5.test;

import algorithm.UtilitiesClass;
import java.util.Random;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import coursera.algo1.week5.*;

/** 
* HeapAsArray Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 9, 2013</pre> 
* @version 1.0 
*/ 
public class HeapAsArrayTest { 
    
    HeapAsArray testClass;
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
	public void testHeapWorking() {
		int[] nums = new int[]{4,3,5,6,7,2};

		testClass = new HeapAsArray(nums);

		for (int i=0;i<nums.length;i++) {
			System.out.println((i+1) + " min element is " + testClass.extractMin());
		}
	}

	@Test
	public void testDeleteFunction() {
		int[] nums = new int[]{4,3,5,6,7,2};
		testClass = new HeapAsArray(nums);

		UtilitiesClass.printArray(testClass.getAllElements());

		testClass.insert(8);
		System.out.println("size is " + testClass.getSize());

		testClass.insert(9);
		testClass.insert(11);

		UtilitiesClass.printArray(testClass.getAllElements());
		System.out.println("size is " + testClass.getSize());

		testClass.delete(3);
		UtilitiesClass.printArray(testClass.getAllElements());
		System.out.println("size is " + testClass.getSize());

		testClass.delete(2);
		UtilitiesClass.printArray(testClass.getAllElements());
		System.out.println("size is " + testClass.getSize());
	}

	@Test
	public void testValueIndexMapping() {
		int[] nums = new int[]{4,3,5,6,7,2};
		testClass = new HeapAsArray(nums, 10);

		UtilitiesClass.printArray(testClass.getAllElements());
		System.out.println("index of element 4 is " + testClass.getIndexOf(4));

		testClass.extractMin();
		UtilitiesClass.printArray(testClass.getAllElements());

		System.out.println("index of element 4 is " + testClass.getIndexOf(4));
		System.out.println("index of element 1 is " + testClass.getIndexOf(1));

		testClass.insert(8);
		testClass.insert(1);
		UtilitiesClass.printArray(testClass.getAllElements());
		System.out.println("index of element 1 is " + testClass.getIndexOf(1));
		System.out.println("index of element 8 is " + testClass.getIndexOf(8));
	}
	@Test
	public void testHeapPerformance() {
		int[] array = getRandomArray(1000000);

		System.out.println("minimum element is " + findMin(array));

		testClass = new HeapAsArray(array);

		System.out.println("minimum element is " + testClass.extractMin());

	}

	private int findMin(int[] a) {
		int min = Integer.MAX_VALUE;

		for (int i : a) {
			min = Math.min(i, min);
		}
		return min;
	}

	private int[] getRandomArray(int size) {
		int[] array = new int[size];

		Random random = new Random(23242424);

		for (int i=0;i<size;i++) {
			array[i] = random.nextInt(size);
		}

		return array;
	}
} 
