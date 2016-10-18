package com.test0;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class PrintData {
	static void print(Enumeration e) {
		while(e.hasMoreElements())
			System.out.println(e.nextElement().toString());
	}
}

class Mouse {
	int mouseNumber;
	Mouse(int i) { mouseNumber = i; }
	public String toString() {return "" + mouseNumber; }
}

class Hamster {
	int hamsterNum;
	Hamster(int i) { hamsterNum = i; }
	public String toString() {return "" + hamsterNum; }
}

public class Demo13 {
	public static void main(String[] args) {
		Vector v = new Vector();
		for(int i = 0; i < 10; i++) 
			v.addElement(new Mouse(i));
		
		Hashtable ht = new Hashtable();
		for(int i = 0; i < 10; i++)
			ht.put(new Integer(i), new Hamster(i));
		
		System.out.println("vector");
		PrintData.print(v.elements());
		System.out.println("hashtable");
		PrintData.print(ht.elements());
	}
		
}
