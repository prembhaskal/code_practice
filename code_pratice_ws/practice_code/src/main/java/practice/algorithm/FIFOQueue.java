package practice.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class FIFOQueue {

	public void testArrayDeque(Queue<Integer> fifoQueue) {
		fifoQueue.add(4);
		fifoQueue.add(3);
		fifoQueue.add(2);

		System.out.println(fifoQueue);
		Integer num;
		while ((num = fifoQueue.poll())!= null) {
			System.out.println(num);
		}
	}

}
