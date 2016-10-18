package com.test0;

import java.util.Enumeration;

public class StrSortVector {
	private Demo14 demo14 = new Demo14(
		new Compare() {
			public boolean lessThanOrEqual(Object lhs, Object rhs) {
				return ((String)lhs).toLowerCase().compareTo(((String)rhs).toLowerCase()) <= 0;
			}
			public boolean lessThan(Object lhs, Object rhs) {
				return ((String)lhs).toLowerCase().compareTo(
						((String)rhs).toLowerCase()) < 0;
			}
		}
	);
	
	private boolean sorted = false;
	public void addElement(String s) {
		demo14.addElement(s);
		sorted = false;
	}
	public String elementAt(int index) {
		if(!sorted) {
			demo14.sort();
			sorted = true;
		}
		return (String)demo14.elementAt(index);
	}
	public Enumeration elements() {
		if(!sorted){
			demo14.sort();
			sorted = true;
		}
		return demo14.elements();
	}
	
	public static void main(String[] args) {
		StrSortVector sv = new StrSortVector();
		sv.addElement("d");
		sv.addElement("A");
		sv.addElement("C");
		sv.addElement("c");
		sv.addElement("b");
		sv.addElement("B");
		sv.addElement("D");
		sv.addElement("a");
		Enumeration e = sv.elements();
		while(e.hasMoreElements())
		System.out.println(e.nextElement());
	}
}
