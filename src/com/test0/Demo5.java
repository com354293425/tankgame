package com.test0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Demo5 {
//	class Contents {
//		private int i = 11;
//		public int value() {return i;} 
//	}
//	class Destination {
//		private String label;
//		Destination(String string) {
//			label = string;
//		}
//		String readLaber() {return label;}
//	}
//	
//	public void ship(String dest) {
//		Contents contents = new Contents();
//		Destination destination = new Destination(dest);
//	}
//	
//	public static void main(String[] args) {
//		Demo5 demo5 = new Demo5();
//		demo5.ship("demo5");
//	}
	
	public static Map fill(Map m, int size) {
		for(int i = 0; i < size; i++){
			String xString = Integer.toString(i);
			m.put(xString, xString);
		}
		return m;
	}
	public static void print(Map m) {
		Iterator keyit = m.keySet().iterator();
		System.out.println("keys:");
		while(keyit.hasNext())
			System.out.println(keyit.next());
		
		Iterator valueit = m.values().iterator();
		System.out.println("values:");
		while(valueit.hasNext())
			System.out.println(valueit.next());
		
		Iterator its = m.entrySet().iterator();
		while(its.hasNext()) {
			Map.Entry me = (Map.Entry)its.next();
			System.out.println("key: " + me.getKey() + "  value: " + me.getValue());
		}
	}
	
	public static void main(String[] args) {
		Map mm = fill(new HashMap(), 10);
		print(mm);
	}
}
