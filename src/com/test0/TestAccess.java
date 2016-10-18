package com.test0;

public class TestAccess {
	public static void main(String[] args) {
//		ThreadGroup 
//			x = new ThreadGroup("x"),
//			y = new ThreadGroup(x, "y"),
//			z = new ThreadGroup(y, "z");
//		Thread
//			one = new TestThread1(x, "one"),
//			two = new TestThread2(z, "two");
		
		//get the system thread & print its info
		ThreadGroup sys = Thread.currentThread().getThreadGroup();
		sys.list();		//(1)
		//reduce the system thread group priority
		sys.setMaxPriority(Thread.MAX_PRIORITY - 1);
		//increase the main thread priority
		Thread curr = Thread.currentThread();
		curr.setPriority(curr.getPriority() + 1);
		sys.list();    //(2)
		//attempt to set a new group to the max
		ThreadGroup g1 = new ThreadGroup("g1");
		g1.setMaxPriority(Thread.MAX_PRIORITY);
		//attempt to set a new thread to the max
		Thread t = new Thread(g1, "A");
		t.setPriority(Thread.MAX_PRIORITY);
		g1.list();     //(3)
		//reduce g1's max priority, then attempt to increase it
		g1.setMaxPriority(Thread.MAX_PRIORITY - 2);
		g1.setMaxPriority(Thread.MAX_PRIORITY);
		g1.list();     //(4) 
		//attempt to set a new thread to the max
		t = new Thread(g1, "B");
		t.setPriority(Thread.MAX_PRIORITY);
		g1.list();		//(5)
		//lower the max priority below the default thread priority
		g1.setMaxPriority(Thread.MAX_PRIORITY + 2);
		//look at a new thread 's priority before and after changing it
		t = new Thread(g1, "C");
		g1.list();      //(6)
		t.setPriority(t.getPriority() - 1);
		g1.list();      //(7)
		//make g2 a child threadgroup of g1 and try to increase its priority
		ThreadGroup g2 = new ThreadGroup(g1, "g2");
		g2.list();      //(8)
		g2.setMaxPriority(Thread.MAX_PRIORITY);
		g2.list();      //(9)
		//add a bunch of new threads to g2
		for(int i = 0; i < 5; i++)
			new ThreadGroup(g2, Integer.toString(i));
		//shwo information about all threadgroups and threads
		sys.list();		//(10)
		System.out.println("starting all threads");
		Thread[] all = new Thread[sys.activeCount()];
		sys.enumerate(all);
		for(int i = 0; i < all.length; i++)
			if(!all[i].isAlive())
				all[i].start();
		//suspends & stops all threads in this group and its subgroups
		System.out.println("All threads started");
		sys.suspend();		// Deprecated in Java 1.2
		System.out.println("All threads suspended");
		sys.stop();			// Deprecated in Java 1.2
		System.out.println("All threads stopped");
	}
}

class TestThread1 extends Thread {
	private int i;
	TestThread1(ThreadGroup g, String name) {
		super(g, name);
	}
	void f() {
		i++;	//modify this thread
		System.out.println(getName() + " f()");
	}
}

class TestThread2 extends TestThread1 {
	TestThread2(ThreadGroup g, String name) {
		super(g, name);
		start();
	}
	public void run() {
		ThreadGroup g = getThreadGroup().getParent().getParent();
		g.list();
		Thread[] aAll = new Thread[g.activeCount()];
		g.enumerate(aAll);
		for(int i = 0; i < aAll.length; i++) {
			aAll[i].setPriority(Thread.MIN_PRIORITY);
			((TestThread1)aAll[i]).f();
		}
		g.list();
	}
}
