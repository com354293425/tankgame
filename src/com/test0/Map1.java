package com.test0;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Map1 {
	public final static String[][] testData1 = {
		{ "Happy", "Cheerful disposition" },
		{ "Sleepy", "Prefers dark, quiet places" },
		{ "Grumpy", "Needs to work on attitude" },
		{ "Doc", "Fantasizes about advanced degree"},
		{ "Dopey", "'A' for effort" },
		{ "Sneezy", "Struggles with allergies" },
		{ "Bashful", "Needs self-esteem workshop"},
	};
	
	public final static String[][] testData2 = {
		{ "Belligerent", "Disruptive influence" },
		{ "Lazy", "Motivational problems" },
		{ "Comatose", "Excellent behavior" }
	};
	
	public static Map fill(Map m, Object[][] o) {
		for(int i = 0; i < o.length; i++) 
			m.put(o[i][0], o[i][1]);
		return m;
	}
	
	public static void printKey(Map m) {
		System.out.println("m.size(): " + m.size());
		System.out.print("m.key(): ");
		Demo15.print(m.keySet());
	}
	
	public static void printValue(Map m) {
		System.out.print("m.value(): ");
		Demo15.print(m.values());
	}
	
	public static void print(Map m) {
		Collection entries = m.entrySet();
		Iterator it = entries.iterator();
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("Key(): " + e.getKey() + " , Value(): " + e.getValue());
		}
	}
	
	public static void test(Map m) {
		fill(m, testData1);
		fill(m, testData1);
		
		printKey(m);
		printValue(m);
		print(m);
		
		String key = testData1[4][0];
		//String value = testData1[4][1];
		System.out.println("m.containsKey(" + key + ") = " + m.containsKey(key));
		System.out.println("m.get(" + key + ") = " + m.get(key));
		
		Map m2 = fill(new TreeMap(), testData2);
		m.putAll(m2);
		printKey(m);
		
		m.remove(testData2[0][0]);
		printKey(m);
		
		m.clear();
		System.out.println("m.isEmpty()" + m.isEmpty());
		
		fill(m, testData1);
		m.keySet().removeAll(m.keySet());
		printKey(m);
		System.out.println("m.isEmpty()" + m.isEmpty());
	}
	
	public static void main(String[] args) {
		test(new HashMap());
		System.out.println("---------------------------------------------");
		test(new TreeMap());
	}
}
