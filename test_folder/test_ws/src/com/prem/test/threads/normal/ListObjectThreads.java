package com.prem.test.threads.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListObjectThreads {

	public static void main(String[] s) throws InterruptedException {

		List<Integer> nos = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			nos.add(i);
		}

		ModifyList modifyList1 = new ModifyList(); // change object.
		ModifyList modifyList2 = new ModifyList(); // iterate over list.

		Thread t1 = new Thread(modifyList1);
		Thread t2 = new Thread(modifyList2);

		t1.start();
		Thread.sleep(100);
		t2.start();
		Thread.sleep(100);

		t1.join();
		t2.join();
	}
}


class ModifyList implements Runnable {

	public ModifyList() {
	}

	@Override
	public void run() {

		for (Integer num : UserSession.getUserSession().getPartitionIds()) {
			System.out.println("reading array " + num);
			try {
				Random random = new Random(351059);
				int sleep = random.nextInt(100) + 1;
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class UserSession {
	private static UserSession userSession = new UserSession();

	private UserSession(){};

	public static UserSession getUserSession() {
		return userSession;
	}

	private List<Integer> nos;

	public List<Integer> getPartitionIds() {
		nos = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			nos.add(i);
			Random random = new Random(3510559);
			int sleep = random.nextInt(100) + 1;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return nos;
	}
}
