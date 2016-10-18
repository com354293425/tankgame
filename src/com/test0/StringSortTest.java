package com.test0;

import java.util.Enumeration;

public class StringSortTest {
	static class StringCompare implements Compare {

		public boolean lessThan(Object lhs, Object rhs) {
			return ((String)lhs).toLowerCase().compareTo(((String)rhs).toLowerCase()) < 0;
		}

		public boolean lessThanOrEqual(Object lhs, Object rhs) {
			return ((String)lhs).toLowerCase().compareTo(((String)rhs).toLowerCase()) <= 0;
		}
		
	}
	
	public static void main(String[] args) {
		Demo14 demo14 = new Demo14(new StringCompare());
		demo14.addElement("a");
		demo14.addElement("d");
		demo14.addElement("A");
		demo14.addElement("C");
		demo14.addElement("c");
		demo14.addElement("b");
		demo14.addElement("B");
		demo14.addElement("D");
		demo14.sort();
		Enumeration enumeration = demo14.elements();
		while(enumeration.hasMoreElements())
			System.out.println(enumeration.nextElement());
	}
}
