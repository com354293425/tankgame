package com.test0;

import java.util.Vector;

//class Mouse {
//	private int mouseNumber;
//	Mouse(int i) {
//		mouseNumber = i;
//	}
//	
//	public String toString() {
//		return "this is a mouse #" + mouseNumber;
//	}
//	
//	void print(String meg) {
//		if(meg != null) System.out.println(meg);
//		System.out.println("Mouse Number " + mouseNumber);
//	}
//}
//
//class MouseTrap {
//	static void caughtYa(Object m) {
//		Mouse mouse = (Mouse)m;
//		mouse.print("Caught One");
//	}
//}

class Gopher {
	private int gopherNumber;
	Gopher(int i) {
		gopherNumber = i;
	}
	void print(String s) {
		if(s != null) System.out.println(s);
		System.out.println("Gopher Number " + gopherNumber);
	}
}

class GopherTrap {
	static void caughtYa(Gopher g) {
		g.print("caught one");
	}
}

public class Demo10 {
	private Vector v = new Vector();
	public void addElement(Gopher g) {
		v.addElement(g);
	}
	public Gopher elementAt(int index) {
		return (Gopher)v.elementAt(index);
	}
	public int size() {
		return v.size();
	}
	
	public static void main(String[] args) {
//		Vector mice = new Vector();
//		for(int i = 0 ; i < 3 ; i++)
//			mice.addElement(new Mouse(i));
//		for(int i = 0; i < mice.size(); i++){
//			System.out.println("free mouse: " + mice.elementAt(i));
//			MouseTrap.caughtYa(mice.elementAt(i));
//		}
		Demo10 demo10 = new Demo10();
		for(int i = 0; i < 3; i++)
			demo10.addElement(new Gopher(i));
		for(int i = 0; i < demo10.size(); i++)
			GopherTrap.caughtYa(demo10.elementAt(i));
	}
}
