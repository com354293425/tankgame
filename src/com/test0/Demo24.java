package com.test0;

class Run1 implements Runnable {
	private Thread thread;
	Run2 r;
	Do do1;
	Run1() {	
		do1 = new Do();
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		while(r == null) {
			synchronized (do1) {
				r = new Run2(do1);
				try {
					do1.wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(r != null) {
				System.out.println(r.getClass().getName());
				return;
			}
		}
	}
}

class Run2 implements Runnable {
	private Thread t;
	Run1 r;
	Do do1;
	public Run2(Do do1) {
		this.do1 = do1;
		r = new Run1();
		t = new Thread(this);
	}
	public void run() {
		synchronized (do1) {
			do1.notifyAll();
		}
	}
}

class Do {
}

public class Demo24 {
	public static void main(String[] args) {
		Run1 run1 = new Run1();
	}
}
