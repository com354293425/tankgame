package com.test0;

public class Demo22{
	public static void main(String[] args) {
		final Demo22 demo22 = new Demo22();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				demo22.m1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				demo22.m2();
			}
		});
		t1.start();
		t2.start();
	}

	public synchronized void m1() {
		for(int i = 0; i < 3; i++)
			System.out.println(Thread.currentThread().getName() + "." + i);
	}
	
	public synchronized void m2() {
		for(int i = 0; i < 3; i++)
			System.out.println(Thread.currentThread().getName() + "." + i);
	}
}
