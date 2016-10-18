package com.test0;

import java.util.*;

//class Counter {
//	int i = 1;
//	public String toString() {
//		return Integer.toString(i);
//	}
//}

class Groundhog {
	int ghNumber;
	Groundhog(int i) {
		ghNumber = i;
	}
	public int hashCode() { return ghNumber; }
	public boolean equals(Object o) {
		return (o instanceof Groundhog) && (ghNumber == ((Groundhog)o).ghNumber);
	}
}

class Prediction {
	boolean shadow = Math.random() > 0.5;
	public String toString() {
		if(shadow)
			return "Six more weeks of Winter!";
		else
			return "Early Spring!";
	}
}

public class Demo12{
//	private Vector keys = new Vector();
//	private Vector values = new Vector();
//	
//	public int size() {
//		return keys.size();
//	}
//
//	public boolean isEmpty() {
//		return keys.isEmpty();
//	}
//
//	public Enumeration keys() {
//		return keys.elements();
//	}
//
//	public Enumeration elements() {
//		return values.elements();
//	}
//
//	public Object get(Object key) {
//		int index = keys.indexOf(key);
//		if(index == -1) return null;
//		return values.elementAt(index);
//	}
//
//	public Object put(Object key, Object value) {
//		keys.addElement(key);
//		values.addElement(value);
//		return key;
//	}
//
//	public Object remove(Object key) {
//		int index = keys.indexOf(key);
//		if(index == -1) return null;
//		keys.removeElementAt(index);
//		Object objectval = values.elementAt(index);
//		values.removeElementAt(index);
//		return objectval;
//	}
	
	public static void main(String[] args) {
//		Demo12 demo12 = new Demo12();
//		for(char c = 'a'; c <= 'z'; c++)
//			demo12.put(String.valueOf(c), String.valueOf(c).toUpperCase());
//		char[] ca = { 'a','e','i','o','u' };
//		for(int i = 0; i < ca.length; i++)
//			System.out.println("upperCase" + demo12.get(String.valueOf(ca[i])));
	
//		Hashtable ht = new Hashtable();
//		for(int i = 0; i < 100; i++) {
//			Integer integer = new Integer((int)(Math.random() * 20));
//			if(ht.containsKey(integer))
//				((Counter)ht.get(integer)).i++;
//			else
//				ht.put(integer, new Counter());
//		}
//		System.out.println(ht);
		
		Hashtable ht = new Hashtable();
		for(int i = 0; i < 10; i++) 
			ht.put(new Groundhog(i), new Prediction());
		System.out.println("ht = " + ht + "\n");
		System.out.println("Looking up prediction for groundhog #3: ");
		Groundhog gr = new Groundhog(3);
		if(ht.containsKey(gr))
			System.out.println((Prediction)ht.get(gr));
	}
}
