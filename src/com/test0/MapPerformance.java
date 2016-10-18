package com.test0;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapPerformance {
	private static final int REPS = 200;
	public static Map fill(Map m, int size) {
		for(int i = 0; i < size; i++){
			String xString = Integer.toString(i);
			m.put(xString, xString);
		}
		return m;
	}
	private abstract static class Tester{
		String name;
		Tester(String string) { name = string; }
		abstract void test(Map m, int size);
	}
	private static Tester[] tests = {
		new Tester("put") {
			void test(Map m, int size) {
				for(int i = 0; i < REPS; i++)
					m.clear();
				fill(m, size);
			}
		},
		new Tester("get") {
			void test(Map m, int size) {
				for(int i = 0; i < REPS; i++) 
					for(int j = 0; j < size; j++)
						m.get(Integer.toString(j));
			}
			
		},
		new Tester("iteration") {
			void test(Map m, int size) {
				for(int i = 0; i < REPS * 10; i++){
					Iterator it = m.entrySet().iterator();
					while(it.hasNext())
						it.next();
				}
			}
		},
	};
	public static void test(Map m, int size) {
		System.out.println("testing" + m.getClass().getName() + " size " + size);
		fill(m, size);
		for(int i = 0; i < tests.length; i++) {
			System.out.println(tests[i].name);
			long l1 = System.currentTimeMillis();
			tests[i].test(m, size);
			long l2 = System.currentTimeMillis();
			System.out.println(": " + (double)(l2 - l1)/(double)size);
		}
	}
	
	public static void main(String[] args) {
//		test(new HashMap(), 10);
//		test(new TreeMap(), 10);
//		
//		test(new HashMap(), 100);
//		test(new TreeMap(), 100);
//		
//		test(new HashMap(), 1000);
//		test(new TreeMap(), 1000);
		
		final long REPS = 100000000;
		long t1 = System.currentTimeMillis();
		System.out.print("hashtable ");
		for(int i = 0; i < REPS; i++)
			new Hashtable();
		long t2 = System.currentTimeMillis();
		System.out.println("time: " + (t2 - t1));
		t1 = System.currentTimeMillis();
		System.out.print("hashMap ");
		for(int i = 0; i < REPS; i++)
			new HashMap();
		t2 = System.currentTimeMillis();
		System.out.println("time: " + (t2 - t1));
		t1 = System.currentTimeMillis();
		System.out.print("treeMap ");
		for(int i = 0; i < REPS; i++)
			new TreeMap();
		t2 = System.currentTimeMillis();
		System.out.println("time: " + (t2 - t1));
	}
}
