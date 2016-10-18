package com.test0;

import java.util.Enumeration;
import java.util.Vector;

class Cat {
	private int catNumber;
	Cat(int i) {
		catNumber = i;
	}
	void print() {
		System.out.println("Cat #" + catNumber);
	}
}
class Dog {
	private int dogNumber;
	Dog(int i) {
		dogNumber = i;
	}
	void print() {
		System.out.println("Dog #" + dogNumber);
	}
}

public class Demo7 {
	public static void main(String[] args) {
		Vector cats = new Vector();
		for(int i = 0; i < 7; i++)
			cats.addElement(new Cat(i));
		Enumeration e = cats.elements();
		while(e.hasMoreElements()) 
			((Cat)e.nextElement()).print();
	}
//	private void internalTracking(boolean b) {
//		if(b) {
//			class TrackingSlip {
//				private String id;
//				TrackingSlip(String id) { this.id = id; }
//				String getSlip() { return id; }
//			}
//			TrackingSlip tSlip = new TrackingSlip("slip");
//			String string = tSlip.getSlip();
//		}
//	}
//	
//	public void track() { internalTracking(true); }
//	public static void main(String[] args) {
//		Demo7 demo7 = new Demo7();
//		demo7.track();
//	}
}
