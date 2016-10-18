package com.test0;

class NewThread extends Thread{
	Thread t;
	NewThread() {
		//创建第二个线程
		t = new Thread(this, "Demo Thread");
		System.out.println("Child thread: " + t);
		t.start();
	}
	
	public void run() {
		try {
			for(int i = 5; i > 0; i--)
				System.out.println("Child1 thread" + i);
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Existing child thread");
	}
}

public class ThreadDemo {
	public static void main(String[] args) {
		new NewThread();
		try {
			for(int i = 5; i > 0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}
}
