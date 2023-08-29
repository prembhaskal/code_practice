package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class P225StackWithQueue {


}

class MyStack {
    Queue<Integer> inqueue = new ArrayDeque<>();
    Queue<Integer> revQueue = new ArrayDeque<>();


    public MyStack() {

    }

    public void push(int x) {
        // write to inqueue, which is empty always.
        inqueue.offer(x);
        // empty revqueue
        while(!revQueue.isEmpty()) {
            inqueue.offer(revQueue.poll());
        }

        // swap queues
        Queue<Integer> temp = inqueue;
        inqueue = revQueue;
        revQueue = temp;
    }

    public int pop() {
        return revQueue.poll();
    }

    public int top() {
        return revQueue.peek();
    }

    public boolean empty() {
        return revQueue.isEmpty();
    }
}
